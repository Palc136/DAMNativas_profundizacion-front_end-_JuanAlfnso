package com.alfonso.my_applicationdam_nativa.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alfonso.my_applicationdam_nativa.adapters.ProductAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentBuyerHomeBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeFragment es la pantalla de inicio del comprador.
 * Muestra un banner de bienvenida y una lista de productos destacados (mock).
 */
public class HomeFragment extends Fragment {

    // ¿Para qué sirve?: mantener la referencia al binding del layout fragment_buyer_home.xml.
    // ¿Qué hace?: expone rvProducts para asignarle adapter y layoutManager.
    // ¿Cuál es su función en el código?: acceso tipado a las vistas del Fragment.
    private FragmentBuyerHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar el layout del Fragment y obtener el binding.
        // ¿Qué hace?: construye la jerarquía de vistas desde fragment_buyer_home.xml.
        // ¿Cuál es su función en el código?: es la vista raíz que el Fragment mostrará
        //             cuando el NavController lo active.
        binding = FragmentBuyerHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar los productos destacados al mostrarse la pantalla.
        // ¿Qué hace?: invoca loadMockProducts() que prepara datos y configura el RecyclerView.
        // ¿Cuál es su función en el código?: separa la inicialización del ciclo de vida de la UI.
        loadMockProducts();
    }

    /**
     * Prepara datos mock y configura el RecyclerView.
     */
    private void loadMockProducts() {

        // ¿Para qué sirve?: crear productos de ejemplo para la pantalla de inicio.
        // ¿Qué hace?: instancia objetos Product con datos representativos del nicho
        //             (artesanías, café, joyería, textiles).
        // ¿Cuál es su función en el código?: poblar el RecyclerView sin necesidad de backend.
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Mochila wayuu", 120000.0,
                "Tejida a mano por artesanas guajiras", "Artesanías"));
        products.add(new Product(2, "Café artesanal", 25000.0,
                "Café de origen colombiano tostado en pequeña escala", "Alimentos"));
        products.add(new Product(3, "Joyería en plata", 85000.0,
                "Dijes y aretes hechos a mano por orfebres locales", "Joyería"));
        products.add(new Product(4, "Textil bordado", 60000.0,
                "Bordados tradicionales con hilos de colores", "Textiles"));

        // ¿Para qué sirve?: indicar cómo se posicionan los ítems del RecyclerView.
        // ¿Qué hace?: asigna un LinearLayoutManager vertical.
        // ¿Cuál es su función en el código?: define la disposición visual del listado.
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(requireContext()));

        // ¿Para qué sirve?: enlazar los datos con el RecyclerView.
        // ¿Qué hace?: crea un ProductAdapter con la lista mock y lo asigna.
        // ¿Cuál es su función en el código?: conecta los datos con las celdas del listado.
        binding.rvProducts.setAdapter(new ProductAdapter(products));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar la referencia al binding.
        // ¿Qué hace?: pone binding en null cuando la vista del Fragment se destruye.
        // ¿Cuál es su función en el código?: evita fugas de memoria al mantener referencias
        //             a vistas que ya no existen.
        binding = null;
    }
}