import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            //Dependencies for ktor android

            implementation(libs.ktor.client.okhttp)

            //Dependencies for coroutines

            implementation(libs.kotlinx.coroutines.android)

            //Dependencies for koin

            implementation(libs.koin.android)

            //Dependencies for decompose

            implementation(libs.decompose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            //Dependencies for ktor common

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization)

            //Dependencies for coroutines

            implementation(libs.kotlinx.coroutines.core)

            //Dependencies for serialization

            implementation(libs.kotlin.json.serialization)

            //Dependencies for kamel

            implementation(libs.kamel.image.loader)

            //Dependencies for decompose

            implementation(libs.decompose)
            implementation(libs.decompose.jetbrains)

            //Dependencies for moko

            implementation(libs.moko.mvvm)

            //Dependencies for koin

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.compose)
            api(libs.koin.core)
        }
        iosMain.dependencies {

            //Dependencies for ktor ios

            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.isaacdelosreyes.valorantmultiplatform"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.isaacdelosreyes.valorantmultiplatform"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
