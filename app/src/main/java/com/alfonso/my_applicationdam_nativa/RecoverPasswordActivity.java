package com.alfonso.my_applicationdam_nativa;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.alfonso.my_applicationdam_nativa.databinding.ActivityRecoverPasswordBinding;

/**
 * RecoverPasswordActivity permite solicitar el restablecimiento de contraseña
 * a través del correo electrónico del usuario.
 */
public class RecoverPasswordActivity extends AppCompatActivity {

    // ¿Para qué sirve?: mantener la referencia al binding del layout de recuperación.
    // ¿Qué hace?: guarda el objeto generado por ViewBinding con las vistas
    //             (etEmail, btnSend).
    // ¿Cuál es su función en el código?: acceso tipado a las vistas de la pantalla.
    @SuppressWarnings("FieldCanBeLocal")
    private ActivityRecoverPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ¿Para qué sirve?: inflar el layout activity_recover_password.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding asociado.
        // ¿Cuál es su función en el código?: inicializa la interfaz de recuperación.
        binding = ActivityRecoverPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ¿Para qué sirve?: registrar la acción del botón "Enviar enlace".
        // ¿Qué hace?: valida el correo, muestra un Toast de confirmación
        //             y cierra la Activity volviendo a Login.
        // ¿Cuál es su función en el código?: simula el envío de correo de recuperación.
        binding.btnSend.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Ingresa tu correo", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Enlace enviado a " + email,
                    Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
