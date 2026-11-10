plugins {
    alias(libs.plugins.android.application)
}

android {

    // ¿Para qué sirve?: habilitar la generación automática de clases Binding para cada layout XML.
// ¿Qué hace?: le dice al plugin de Android que genere una clase Java por cada archivo de layout
//             (por ejemplo, ActivityLoginBinding para activity_login.xml).
// ¿Cuál es su función en el código?: permite acceder a las vistas del layout desde Java
//             sin usar findViewById y sin riesgo de NullPointerException por IDs mal escritos.
    buildFeatures {
        viewBinding = true
    }
    namespace = "com.alfonso.my_applicationdam_nativa"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.alfonso.my_applicationdam_nativa"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // ¿Para qué sirve?: dependencias base del proyecto (ya venían en la plantilla).
    // ¿Qué hace?: aportan AppCompat, Material, Activity y ConstraintLayout.
    // ¿Cuál es su función en el código?: soporte de Activities, temas Material 3 y layouts.
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // ¿Para qué sirve?: Navigation Component (Fragments y rutas).
    // ¿Qué hace?: permite navegar entre pantallas usando nav_graph y BottomNav/Drawer.
    // ¿Cuál es su función en el código?: sistema de navegación entre Fragments.
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // ¿Para qué sirve?: autenticación biométrica.
    // ¿Qué hace?: expone BiometricPrompt para login con huella.
    // ¿Cuál es su función en el código?: ofrece login biométrico en LoginActivity.
    implementation(libs.androidx.biometric)

    // ¿Para qué sirve?: FragmentContainerView y utilidades de Fragments.
    // ¿Qué hace?: base para NavHostFragment y Fragments con ViewBinding.
    // ¿Cuál es su función en el código?: infraestructura de Fragments del proyecto.
    implementation(libs.androidx.fragment)

    // ¿Para qué sirve?: RecyclerView para listados.
    // ¿Qué hace?: dibuja listas eficientes de productos, usuarios, pedidos, etc.
    // ¿Cuál es su función en el código?: componente de listas usado en todas las pantallas de catálogo.
    implementation(libs.androidx.recyclerview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

}