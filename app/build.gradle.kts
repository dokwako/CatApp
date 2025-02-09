plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.catapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.catapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        compose = true
    }
}

dependencies {

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

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //kapt("androidx.hilt:hilt-compiler:1.0.0") // For ViewModel
    //implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")// For ViewModel
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")


    //Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material3:material3:1.0.1")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:1.5.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.4")

    //Retrofit & Gson for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")


    // Coroutine for background tasks
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Coil dependency
    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.30.1") // Use the latest version


    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.3") // Replace with latest stable version

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}