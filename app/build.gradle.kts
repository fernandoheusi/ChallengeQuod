plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "br.com.fiap.challengequod"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.fiap.challengequod"
        minSdk = 27
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
    // Dependências existentes
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

    // Dependências atualizadas para Jetpack Compose
    implementation("androidx.compose.ui:ui:1.6.0") // Última versão do Compose UI
    implementation("androidx.compose.material3:material3:1.2.0") // Compose Material 3
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation("androidx.navigation:navigation-compose:2.7.3") // Navegação para Compose

    // Para pré-visualização no Android Studio
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.0")

    // Dependências adicionais
    implementation("androidx.activity:activity-compose:1.8.0") // Integrar Compose em Activity

    //Dependencia biometria
    implementation("androidx.biometric:biometric:1.1.0")

    implementation("androidx.appcompat:appcompat:1.6.1")//  Permite que recursos modernos sejam usados em dispositivos com versões de API anteriores
}
