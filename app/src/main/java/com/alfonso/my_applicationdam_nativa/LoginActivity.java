package com.alfonso.my_applicationdam_nativa;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import com.alfonso.my_applicationdam_nativa.databinding.ActivityLoginBinding;
import java.util.concurrent.Executor;

/**
 * LoginActivity gestiona el inicio de sesión del usuario.
 * Ofrece tres caminos: login tradicional, autenticación biométrica
 * y navegación hacia registro o recuperación de contraseña.
 */
public class LoginActivity extends AppCompatActivity {

    // ¿Para qué sirve?: declarar el objeto de binding del layout activity_login.
    // ¿Qué hace?: guarda la referencia a la clase generada automáticamente por ViewBinding
    //             que expone todas las vistas del layout como atributos Java.
    // ¿Cuál es su función en el código?: permite acceder a etEmail, etPassword, btnLogin, etc.
    //             sin usar findViewById.
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ¿Para qué sirve?: inflar el layout y obtener la clase Binding asociada.
        // ¿Qué hace?: ActivityLoginBinding.inflate(getLayoutInflater()) construye la jerarquía
        //             de vistas desde activity_login.xml y devuelve un objeto con referencias
        //             directas a cada vista con android:id.
        // ¿Cuál es su función en el código?: inicializa la interfaz y deja disponible
        //             el objeto binding que se usará en toda la Activity.
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        // ¿Para qué sirve?: establecer la vista raíz de la Activity.
        // ¿Qué hace?: getRoot() devuelve la vista superior del layout ya inflado;
        //             setContentView la monta en pantalla.
        // ¿Cuál es su función en el código?: hace visible la pantalla de Login.
        setContentView(binding.getRoot());

        // ¿Para qué sirve?: permitir elegir el rol al iniciar sesión (para pruebas y demo).
        // ¿Qué hace?: al pulsar "Iniciar sesión", muestra un diálogo con los 3 roles disponibles;
//             según el elegido, lanza MainActivity con el extra ROLE correspondiente.
        // ¿Cuál es su función en el código?: en un entorno real aquí irían validaciones de
//             correo/contraseña y una llamada al backend; mientras el backend no exista,
//             este selector permite probar los 3 roles sin recompilar la app.
        binding.btnLogin.setOnClickListener(v -> {
            String[] roles = {"Comprador", "Vendedor", "Administrador"};
            String[] roleKeys = {"buyer", "seller", "admin"};

            new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Ingresar como")
                    .setItems(roles, (dialog, which) -> {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("ROLE", roleKeys[which]);
                        startActivity(intent);
                    })
                    .show();
        });

        // ¿Para qué sirve?: registrar la acción del botón biométrico.
        // ¿Qué hace?: delega en el método showBiometricPrompt() la apertura del diálogo
        //             nativo de huella dactilar.
        // ¿Cuál es su función en el código?: ofrece una segunda vía de autenticación
        //             adicional al login tradicional.
        binding.btnBiometric.setOnClickListener(v -> showBiometricPrompt());

        // ¿Para qué sirve?: navegar a la pantalla de registro.
        // ¿Qué hace?: lanza RegisterActivity con un Intent explícito.
        // ¿Cuál es su función en el código?: permite al usuario crear una cuenta nueva.
        binding.btnRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // ¿Para qué sirve?: navegar a la recuperación de contraseña.
        // ¿Qué hace?: lanza RecoverPasswordActivity con un Intent explícito.
        // ¿Cuál es su función en el código?: atiende el caso de usuarios que olvidaron su clave.
        binding.btnRecover.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RecoverPasswordActivity.class)));
    }

    /**
     * Muestra el diálogo nativo de autenticación biométrica.
     */
    private void showBiometricPrompt() {

        // ¿Para qué sirve?: obtener un Executor asociado al hilo principal.
        // ¿Qué hace?: ContextCompat.getMainExecutor(this) devuelve un Executor que ejecuta
        //             los callbacks del BiometricPrompt en el Main Thread.
        // ¿Cuál es su función en el código?: garantiza que la respuesta del sistema biométrico
        //             se procese en el hilo correcto para poder actualizar la UI.
        Executor executor = ContextCompat.getMainExecutor(this);

        // ¿Para qué sirve?: crear el objeto que controla el diálogo biométrico.
        // ¿Qué hace?: instancia BiometricPrompt pasándole la Activity, el Executor y un
        //             AuthenticationCallback que define qué ocurre al autenticarse con éxito
        //             o al producirse un error.
        // ¿Cuál es su función en el código?: actúa como intermediario entre la app y el
        //             hardware biométrico del dispositivo.
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {

                    // ¿Para qué sirve?: manejar el caso de autenticación exitosa.
                    // ¿Qué hace?: cuando el sistema confirma la huella, navega a MainActivity
                    //             como "buyer".
                    // ¿Cuál es su función en el código?: completa el flujo de login biométrico.
                    @Override
                    public void onAuthenticationSucceeded(
                            @NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("ROLE", "seller");
                        startActivity(intent);
                    }
                });

        // ¿Para qué sirve?: configurar el texto y comportamiento del diálogo biométrico.
        // ¿Qué hace?: construye un PromptInfo con título, subtítulo y botón negativo.
        // ¿Cuál es su función en el código?: define la presentación que verá el usuario
        //             antes de colocar su huella.
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometric_title))
                .setSubtitle(getString(R.string.biometric_subtitle))
                .setNegativeButtonText(getString(R.string.cancel))
                .build();

        // ¿Para qué sirve?: lanzar el diálogo biométrico.
        // ¿Qué hace?: pasa el PromptInfo al sistema para que muestre la UI nativa de huella.
        // ¿Cuál es su función en el código?: inicia la interacción con el sensor.
        biometricPrompt.authenticate(promptInfo);
    }
}