plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.git.version)
    // id("io.github.kansalmohit19.ktext") version "0.0.5"
}

/*ktext {
    sourceFile.setFrom(project.rootProject.file("resources/translations.en.json"))
    targetFiles.setFrom(
        project.rootProject.file("resources/translations.es.json")
    )
    enableValidation.set(true)
    generateTranslations.set(true)
}*/

gitVersion {
    versionName.set("2.0.0")
}

android {
    namespace = "com.indemand.fotd.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.indemand.fotd"
        minSdk = 24
        targetSdk = 35
        versionCode = gitVersion.code.get()
        versionName = gitVersion.name.get()
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    /*applicationVariants.all {
        outputs.all {
            val output = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            val appName = "MyApp"
            //val variantName = name // e.g. debug, release
            val vCode = versionCode
            val vName = versionName

            output.outputFileName = "${appName}-v${vName}(${vCode}).apk"
            // Example: MyApp-release-v1.0.0(120).apk
        }
    }*/
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.navigation.compose)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    debugImplementation(libs.compose.ui.tooling)
}

tasks.whenTaskAdded {
    if (name.startsWith("assemble") || name.startsWith("bundle")) {
        dependsOn("generateGitVersion")
    }
}
