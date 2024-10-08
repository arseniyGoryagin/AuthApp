plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.com.auth"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.com.auth"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")


    implementation ("androidx.datastore:datastore-preferences:1.0.0")


    // dagger hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // hilt navigation
    implementation(libs.androidx.navigation.compose)
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    implementation("androidx.compose.material:material:1.7.0")


    // Arrow
    implementation("io.arrow-kt:arrow-core:1.2.4")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.4")


    // Retro fit
    val retrofit_version = "2.11.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    //Serialization
    val kotlin_serialization = "1.6.1"
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")


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