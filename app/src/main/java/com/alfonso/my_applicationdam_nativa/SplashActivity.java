package com.alfonso.my_applicationdam_nativa;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SplashActivity es la primera pantalla que ve el usuario al abrir la app.
 * Su única responsabilidad es mostrar la marca y, tras un retraso,
 * navegar hacia la pantalla de Login.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ¿Para qué sirve?: asociar esta Activity con su layout XML.
        // ¿Qué hace?: le indica a Android qué archivo de recursos debe dibujar en pantalla
        //             cuando esta Activity esté visible.
        // ¿Cuál es su función en el código?: establece la vista raíz sobre la que se monta
        //             toda la interfaz de la pantalla de bienvenida.
        setContentView(R.layout.activity_splash);

        // ¿Para qué sirve?: crear un temporizador que ejecute código después de un tiempo.
        // ¿Qué hace?: instancia un Handler asociado al hilo principal (Main Thread) para
        //             garantizar que la acción posterior se ejecute en el hilo de UI.
        // ¿Cuál es su función en el código?: será el encargado de programar el salto al Login.
        Handler handler = new Handler(Looper.getMainLooper());

        // ¿Para qué sirve?: programar una acción diferida en el tiempo.
        // ¿Qué hace?: ejecuta el bloque Runnable después de 2000 milisegundos (2 segundos);
        //             dentro del bloque se crea un Intent hacia LoginActivity y se finaliza
        //             esta Activity para que no quede en el back stack.
        // ¿Cuál es su función en el código?: simula la carga inicial y redirige al usuario
        //             al flujo de autenticación.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // ¿Para qué sirve?: iniciar otra Activity desde la actual.
                // ¿Qué hace?: construye un Intent explícito que apunta a LoginActivity,
                //             lo lanza con startActivity y cierra SplashActivity con finish().
                // ¿Cuál es su función en el código?: es el puente de navegación entre
                //             la pantalla de bienvenida y la de inicio de sesión.
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }
}