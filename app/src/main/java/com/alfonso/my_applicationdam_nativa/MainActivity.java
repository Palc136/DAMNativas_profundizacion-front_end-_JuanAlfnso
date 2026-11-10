package com.alfonso.my_applicationdam_nativa;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.alfonso.my_applicationdam_nativa.databinding.ActivityMainBinding;
import java.util.HashSet;
import java.util.Set;

/**
 * MainActivity es el contenedor principal de la aplicación.
 * Decide, según el rol recibido por Intent, qué gráfico de navegación
 * cargar y qué menú inferior mostrar, además de conectar el Drawer
 * y la Toolbar con el sistema de navegación.
 */
public class MainActivity extends AppCompatActivity {

    // ¿Para qué sirve?: mantener la referencia a las vistas de activity_main.xml.
    // ¿Qué hace?: guarda el binding generado por ViewBinding (drawerLayout, toolbar,
    //             navHostFragment, bottomNav, navView).
    // ¿Cuál es su función en el código?: acceso a las vistas desde cualquier método de la clase.
    @SuppressWarnings("FieldCanBeLocal")
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ¿Para qué sirve?: inflar el layout activity_main.xml y obtener el binding.
        // ¿Qué hace?: ActivityMainBinding.inflate construye la jerarquía de vistas
        //             y devuelve el objeto con todas las referencias por ID.
        // ¿Cuál es su función en el código?: inicializa la interfaz principal.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ¿Para qué sirve?: determinar qué rol debe mostrarse.
        // ¿Qué hace?: lee el extra "ROLE" enviado desde LoginActivity o RegisterActivity;
        //             si llega null, asume "buyer".
        // ¿Cuál es su función en el código?: controla qué nav_graph y qué menú cargar.
        String role = getIntent().getStringExtra("ROLE");
        if (role == null) role = "buyer";

        // ¿Para qué sirve?: obtener el NavHostFragment y su NavController.
        // ¿Qué hace?: busca por ID el FragmentContainerView configurado como NavHost
        //             y extrae el controlador de navegación.
        // ¿Cuál es su función en el código?: punto de acceso al sistema de navegación
        //             entre Fragments dentro de MainActivity.
        NavHostFragment navHost = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHost.getNavController();

        // ¿Para qué sirve?: cargar el gráfico de navegación y el menú inferior adecuados.
        // ¿Qué hace?: según el rol, asigna el nav_graph_XXX y el bottom_nav_XXX
        //             correspondientes al NavController y al BottomNavigationView.
        // ¿Cuál es su función en el código?: personaliza la experiencia de navegación
        //             según el rol del usuario autenticado.
        switch (role) {
            case "admin":
                navController.setGraph(R.navigation.nav_graph_admin);
                binding.bottomNav.inflateMenu(R.menu.bottom_nav_admin);
                break;
            case "seller":
                navController.setGraph(R.navigation.nav_graph_seller);
                binding.bottomNav.inflateMenu(R.menu.bottom_nav_seller);
                break;
            default:
                navController.setGraph(R.navigation.nav_graph_buyer);
                binding.bottomNav.inflateMenu(R.menu.bottom_nav_buyer);
                break;
        }

        // ¿Para qué sirve?: vincular el BottomNavigationView con el NavController.
        // ¿Qué hace?: NavigationUI sincroniza el ítem seleccionado del menú con el
        //             Fragment actual y viceversa.
        // ¿Cuál es su función en el código?: tocar un ítem del menú inferior navega
        //             al Fragment asociado.
        NavigationUI.setupWithNavController(binding.bottomNav, navController);

        // ¿Para qué sirve?: definir cuáles Fragments son de nivel superior.
        // ¿Qué hace?: crea un Set con los IDs de los Fragments principales (home, catálogo,
        //             carrito, perfil) y los asocia al DrawerLayout.
        // ¿Cuál es su función en el código?: en estos Fragments la toolbar muestra el ícono
        //             "hamburguesa" en lugar de la flecha atrás.
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.homeFragment);
        topLevelDestinations.add(R.id.catalogFragment);
        topLevelDestinations.add(R.id.cartFragment);
        topLevelDestinations.add(R.id.profileFragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                topLevelDestinations)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        // ¿Para qué sirve?: conectar la Toolbar con el NavController y el Drawer.
        // ¿Qué hace?: configura la barra superior para que muestre el título del Fragment
        //             actual y el ícono de menú / atrás según la jerarquía.
        // ¿Cuál es su función en el código?: mantiene la toolbar sincronizada con la navegación.
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // ¿Para qué sirve?: conectar el NavigationView (Drawer) con el NavController.
        // ¿Qué hace?: los ítems del menú lateral navegan a su destino y reflejan el estado.
        // ¿Cuál es su función en el código?: habilita la navegación por el cajón lateral.
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}