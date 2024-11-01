plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "vn.edu.tlu.nhom7.calendar"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.edu.tlu.nhom7.calendar"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:deprecation")
}

dependencies {

//    implementation("com.google.firebase:firebase-auth")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.firebase:firebase-firestore:24.3.1")
    implementation ("com.google.firebase:firebase-auth:latest_version")
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation(platform("com.google.code.gson:gson:2.8.8"))
    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    implementation("com.prolificinteractive:material-calendarview:1.4.2")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("org.osmdroid:osmdroid-android:6.1.10")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.facebook.android:facebook-android-sdk:[8,9)")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation ("org.osmdroid:osmdroid-android:6.1.13")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
}