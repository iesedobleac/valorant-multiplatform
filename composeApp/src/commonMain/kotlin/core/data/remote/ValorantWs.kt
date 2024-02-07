package core.data.remote

import core.data.remote.model.agent.AgentsDto
import core.data.remote.model.agent.DataAgentDto
import core.data.remote.model.map.DataMapDto
import core.data.remote.model.map.MapsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import utils.AGENTS_ENDPOINT
import utils.MAPS_ENDPOINT
import utils.TIMEOUT

interface ValorantWs {

    suspend fun getAgentDetails(agentId: String): DataAgentDto

    suspend fun getAgents(): AgentsDto

    suspend fun getMapDetails(mapId: String): DataMapDto

    suspend fun getMaps(): MapsDto
}

@OptIn(ExperimentalSerializationApi::class)
class ValorantWsImpl : ValorantWs {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
    }

    override suspend fun getAgentDetails(agentId: String): DataAgentDto {
        return client.get(
            urlString = "$AGENTS_ENDPOINT/$agentId"
        ).body()
    }

    override suspend fun getAgents(): AgentsDto {
        return client.get(urlString = AGENTS_ENDPOINT).body()
    }

    override suspend fun getMapDetails(mapId: String): DataMapDto {
        return client.get(urlString = "$MAPS_ENDPOINT/$mapId").body()
    }

    override suspend fun getMaps(): MapsDto {
        return client.get(urlString = MAPS_ENDPOINT).body()
    }
}