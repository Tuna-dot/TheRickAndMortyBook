plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.1.10"
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.therickandmortybook"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.therickandmortybook"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField(
                "String",
                "THE_RICK_AND_MORTY_API",
                "\"https://rickandmortyapi.com/api/\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField(
                "String",
                "THE_RICK_AND_MORTY_API",
                "\"https://rickandmortyapi.com/api/\""
            )
         }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    //Room
    implementation(libs.androidx.room.runtime)
       ksp(libs.androidx.room.compiler) // Вместо kapt используйте ksp
    // KTX для Room
    implementation(libs.androidx.room.ktx)
    // Paging 3
    implementation (libs.androidx.paging.runtime)
    // Compose Paging
    implementation (libs.androidx.paging.compose)
    //Coil
    implementation(libs.coil.compose)
    //Navigation
    implementation(libs.androidx.navigation.compose)
    //Koin
    implementation (libs.koin.androidx.compose)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    //Gson
    implementation(libs.gson)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}