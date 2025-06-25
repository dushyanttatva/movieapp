plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "GOOGLE_API_KEY", "\"${project.findProperty("google_api_key") ?: ""}\"")
        buildConfigField("String", "WEATHER_API_KEY", "\"${project.findProperty("weather_api_key") ?: ""}\"")
        manifestPlaceholders["google_api_key"] = project.findProperty("google_api_key") ?: ""
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01")
    implementation("androidx.preference:preference-ktx:1.2.1")
    kapt("com.github.bumptech.glide:compiler:5.0.0-rc01")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")
    implementation("com.google.code.gson:gson:2.9.1")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    // Navigation
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    // Paging
    val pagingVersion = "3.3.6"
    implementation ("androidx.paging:paging-common-android:$pagingVersion")
    implementation ("androidx.paging:paging-runtime-ktx:$pagingVersion")
    // Work Manager
    val workVersion = "2.4.0"
    implementation ("androidx.work:work-runtime-ktx:$workVersion")
    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //kapt("androidx.hilt:hilt-compiler:1.0.0")
    // Groupie
    val groupieVersion = "2.9.0"
    implementation ("com.github.lisawray.groupie:groupie:$groupieVersion")
    implementation ("com.github.lisawray.groupie:groupie-kotlin-android-extensions:$groupieVersion")
    implementation ("com.github.lisawray.groupie:groupie-databinding:$groupieVersion")
    // Life Cycle Arch
    val lifecycleVersion = "2.6.2"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
}