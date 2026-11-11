package com.alfonso.my_applicationdam_nativa.buyer;
import com.alfonso.my_applicationdam_nativa.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alfonso.my_applicationdam_nativa.adapters.ProductAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentCatalogBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * CatalogFragment muestra el catálogo completo de productos.
 * Incluye un SearchView que filtra en tiempo real y un RecyclerView
 * con todos los productos. Al tocar un producto, navega al detalle.
 */
public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding binding;

    // ¿Para qué sirve?: guardar la lista completa de productos.
    // ¿Qué hace?: se mantiene como fuente para aplicar filtros de búsqueda.
    // ¿Cuál es su función en el código?: el SearchView trabaja sobre esta lista
    //             para generar una sublista filtrada.
    private final List<Product> allProducts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar el layout fragment_catalog.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        // ¿Cuál es su función en el código?: es la vista raíz del catálogo.
        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar los productos iniciales del catálogo.
        // ¿Qué hace?: llena allProducts con datos mock y asigna el adapter.
        // ¿Cuál es su función en el código?: prepara el contenido inicial visible.
        loadProducts();

        // ¿Para qué sirve?: escuchar el cambio de texto en la barra de búsqueda.
        // ¿Qué hace?: cada vez que el usuario escribe, filtra allProducts por coincidencia
        //             en el nombre y actualiza el RecyclerView.
        // ¿Cuál es su función en el código?: implementa búsqueda en tiempo real.
        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });
    }

    /**
     * Carga los productos mock y configura el RecyclerView.
     */
    private void loadProducts() {

        // ¿Para qué sirve?: llenar la lista con productos representativos.
        // ¿Qué hace?: añade 8 productos mock con categorías variadas.
        // ¿Cuál es su función en el código?: alimenta el catálogo inicial.
        allProducts.clear();
        allProducts.add(new Product(1, "Mochila wayuu", 120000.0, "Tejida a mano", "Artesanías"));
        allProducts.add(new Product(2, "Café artesanal", 25000.0, "Tostado local", "Alimentos"));
        allProducts.add(new Product(3, "Joyería en plata", 85000.0, "Hecha a mano", "Joyería"));
        allProducts.add(new Product(4, "Textil bordado", 60000.0, "Bordado tradicional", "Textiles"));
        allProducts.add(new Product(5, "Jabón natural", 18000.0, "Con aceites esenciales", "Cuidado personal"));
        allProducts.add(new Product(6, "Bolso tejido", 95000.0, "Fibra natural", "Artesanías"));
        allProducts.add(new Product(7, "Chocolate artesanal", 22000.0, "Cacao colombiano", "Alimentos"));
        allProducts.add(new Product(8, "Aretes de mostacilla", 30000.0, "Diseño artesanal", "Joyería"));

        // ¿Para qué sirve?: configurar el RecyclerView y asignar el adapter.
        // ¿Qué hace?: asigna LinearLayoutManager y un ProductAdapter con listener
        //             que navega al detalle al tocar una tarjeta.
        // ¿Cuál es su función en el código?: conecta los datos mock con la UI del catálogo
        //             y habilita la navegación al detalle.
        binding.rvCatalog.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCatalog.setAdapter(new ProductAdapter(allProducts, product -> navigateToDetail(product)));
    }

    /**
     * Filtra los productos por nombre y actualiza el adapter.
     */
    private void filterProducts(String query) {

        // ¿Para qué sirve?: construir una lista filtrada a partir de la original.
        // ¿Qué hace?: recorre allProducts y agrega solo los productos cuyo nombre
        //             contenga la cadena buscada (ignorando mayúsculas/minúsculas).
        // ¿Cuál es su función en el código?: responde al texto escrito en el SearchView.
        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(p);
            }
        }

        // ¿Para qué sirve?: actualizar el RecyclerView con la lista filtrada.
        // ¿Qué hace?: asigna un nuevo ProductAdapter con filtered y el mismo listener.
        // ¿Cuál es su función en el código?: refleja en pantalla el resultado de la búsqueda.
        binding.rvCatalog.setAdapter(new ProductAdapter(filtered, product -> navigateToDetail(product)));
    }

    /**
     * Navega a la pantalla de detalle del producto seleccionado.
     */
    private void navigateToDetail(Product product) {

        // ¿Para qué sirve?: enviar el producto seleccionado al Fragment de detalle.
        // ¿Qué hace?: construye un Bundle con los datos del producto y navega
        //             usando el NavController al destino productDetailFragment.
        // ¿Cuál es su función en el código?: conecta la lista con la pantalla de detalle.
        Bundle args = new Bundle();
        args.putInt("productId", product.getId());
        args.putString("productName", product.getName());
        args.putDouble("productPrice", product.getPrice());
        args.putString("productDescription", product.getDescription());
        args.putString("productCategory", product.getCategory());

        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.productDetailFragment, args);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar la referencia al binding.
        // ¿Qué hace?: pone binding en null cuando la vista se destruye.
        // ¿Cuál es su función en el código?: evita fugas de memoria.
        binding = null;
    }
}
