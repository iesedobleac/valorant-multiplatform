package core.data.remote

import core.data.remote.model.AgentsDto
import core.data.remote.model.DataDto
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
import utils.TIMEOUT

interface ValorantWs {

    suspend fun getAgentById(agentId: String): DataDto

    suspend fun getAgents(): AgentsDto
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

    override suspend fun getAgentById(agentId: String): DataDto {
        return client.get(
            urlString = "$AGENTS_ENDPOINT/$agentId"
        ).body()
    }

    override suspend fun getAgents(): AgentsDto {
        return client.get(AGENTS_ENDPOINT).body()
    }
}