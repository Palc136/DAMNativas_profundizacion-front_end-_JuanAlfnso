package com.alfonso.my_applicationdam_nativa;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.alfonso.my_applicationdam_nativa.databinding.ActivityRegisterBinding;

/**
 * RegisterActivity permite crear una cuenta nueva.
 * El usuario elige su rol (Comprador o Vendedor) y, al registrarse,
 * vuelve al Login para iniciar sesión.
 */
public class RegisterActivity extends AppCompatActivity {

    // ¿Para qué sirve?: mantener la referencia al binding del layout activity_register.xml.
    // ¿Qué hace?: guarda el objeto generado por ViewBinding que expone las vistas
    //             (etName, etEmail, etPhone, etPassword, rgRole, btnRegister).
    // ¿Cuál es su función en el código?: acceso tipado a las vistas desde toda la Activity.
    @SuppressWarnings("FieldCanBeLocal")
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ¿Para qué sirve?: inflar el layout activity_register.xml y obtener la clase Binding.
        // ¿Qué hace?: construye la jerarquía de vistas desde el XML y devuelve un objeto
        //             con referencias directas a cada vista con android:id.
        // ¿Cuál es su función en el código?: inicializa la interfaz de registro.
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ¿Para qué sirve?: registrar la acción del botón "Registrarse".
        // ¿Qué hace?: al pulsar, valida que los campos no estén vacíos; si están llenos,
        //             muestra un Toast de éxito y regresa a LoginActivity.
        // ¿Cuál es su función en el código?: simula el registro y devuelve al flujo de login.
        binding.btnRegister.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}