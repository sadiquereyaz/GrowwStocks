
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serialization)
}
android {
    namespace = "com.reyaz.core.network"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "ALPHA_VINTAGE_API_KEY",  "\"${project.findProperty("ALPHA_VINTAGE_API_KEY")}\"")
        buildConfigField("String", "ALPHA_VINTAGE_BASE_URL",  "\"${project.findProperty("ALPHA_VINTAGE_BASE_URL")}\"")
 buildConfigField("String", "OVERVIEW_API_KEY",  "\"${project.findProperty("OVERVIEW_API_KEY")}\"")
        buildConfigField("String", "OVERVIEW_BASE_URL",  "\"${project.findProperty("OVERVIEW_BASE_URL")}\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
        buildConfig = true
    }
}
dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:database"))
    // okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // retrofit
    implementation(libs.retrofit)
    // convertors
    implementation(libs.retrofit.converter.gson)
    implementation(libs.converter.moshi)

    // koin
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)


    // serialization
    implementation(libs.kotlinx.serialization.json)

    // room
    implementation(libs.androidx.room.runtime)
//    implementation(libs.androidx.room.ktx)
//    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)


}