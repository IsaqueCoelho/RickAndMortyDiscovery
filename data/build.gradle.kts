plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 27
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":domain"))

    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")

    implementation("androidx.core:core-ktx:1.3.2")

    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")


    implementation("com.squareup.retrofit2:converter-scalars:2.1.0")


    implementation("io.mockk:mockk:1.9.3")
    implementation("org.mockito:mockito-core:3.1.0")
    implementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    implementation("org.powermock:powermock-module-junit4:2.0.4")
    implementation("org.powermock:powermock-module-junit4-rule:2.0.4")
    implementation("org.powermock:powermock-api-mockito2:2.0.4")


    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
}