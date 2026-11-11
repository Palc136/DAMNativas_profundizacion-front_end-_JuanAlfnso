package com.alfonso.my_applicationdam_nativa.seller;
import com.alfonso.my_applicationdam_nativa.R;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.my_applicationdam_nativa.adapters.ProductAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentSellerProductsBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * SellerProductsFragment muestra los productos del vendedor y permite
 * agregar uno nuevo con el FAB o editar/eliminar con long-press.
 */
public class SellerProductsFragment extends Fragment {

    // ¿Para qué sirve?: mantener la referencia al binding del layout.
    // ¿Qué hace?: expone rvSellerProducts y fabAdd.
    private FragmentSellerProductsBinding binding;

    // ¿Para qué sirve?: mantener la lista de productos del vendedor.
    // ¿Qué hace?: es la fuente de datos del RecyclerView.
    private final List<Product> products = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar fragment_seller_products.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        binding = FragmentSellerProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar los productos del vendedor.
        // ¿Qué hace?: llena la lista con datos mock y configura el RecyclerView.
        loadProducts();

        // ¿Para qué sirve?: navegar al formulario de producto nuevo.
        // ¿Qué hace?: al pulsar el FAB, navega al destino productFormFragment.
        binding.fabAdd.setOnClickListener(v -> {
            NavController nav = Navigation.findNavController(requireView());
            nav.navigate(R.id.productFormFragment);
        });
    }

    /**
     * Carga los productos mock, configura el RecyclerView y activa el long-press.
     */
    private void loadProducts() {

        // ¿Para qué sirve?: llenar la lista con productos de ejemplo.
        products.clear();
        products.add(new Product(1, "Mochila wayuu", 120000.0, "Tejida a mano", "Artesanías"));
        products.add(new Product(2, "Café artesanal", 25000.0, "Tostado local", "Alimentos"));
        products.add(new Product(3, "Joyería en plata", 85000.0, "Hecha a mano", "Joyería"));

        // ¿Para qué sirve?: configurar el RecyclerView con adapter y layoutManager.
        // ¿Qué hace?: asigna LinearLayoutManager y ProductAdapter con la lista.
        binding.rvSellerProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSellerProducts.setAdapter(new ProductAdapter(products));

        // ¿Para qué sirve?: detectar el long-press (pulsación larga) sobre un producto.
        // ¿Qué hace?: crea un GestureDetector con un SimpleOnGestureListener que, al
        //             detectar long-press, calcula la posición tocada y abre el diálogo
        //             con las opciones Editar / Eliminar.
        // ¿Cuál es su función en el código?: añade la acción CRUD de edición/eliminación
        //             al listado sin saturar cada celda con botones adicionales.
        final GestureDetector detector = new GestureDetector(requireContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(@NonNull MotionEvent e) {
                        View child = binding.rvSellerProducts.findChildViewUnder(e.getX(), e.getY());
                        if (child != null) {
                            int pos = binding.rvSellerProducts.getChildAdapterPosition(child);
                            if (pos >= 0 && pos < products.size()) {
                                showProductOptions(products.get(pos));
                            }
                        }
                    }
                });

        // ¿Para qué sirve?: enganchar el GestureDetector al RecyclerView.
        // ¿Qué hace?: registra un SimpleOnItemTouchListener que reenvía cada evento táctil
        //             al detector, que a su vez dispara onLongPress cuando corresponde.
        // ¿Cuál es su función en el código?: conecta los toques del usuario con la acción
        //             de editar/eliminar el producto seleccionado.
        binding.rvSellerProducts.addOnItemTouchListener(
                new RecyclerView.SimpleOnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv,
                                                         @NonNull MotionEvent e) {
                        return detector.onTouchEvent(e);
                    }
                });
    }

    /**
     * Muestra un diálogo con opciones Editar / Eliminar para el producto.
     */
    private void showProductOptions(Product product) {

        // ¿Para qué sirve?: ofrecer al vendedor elegir entre editar o eliminar.
        // ¿Qué hace?: construye un AlertDialog con dos opciones; según la elegida,
        //             navega al formulario o elimina el producto de la lista.
        new AlertDialog.Builder(requireContext())
                .setTitle(product.getName())
                .setItems(new CharSequence[]{"Editar", "Eliminar"}, (dialog, which) -> {
                    if (which == 0) {
                        NavController nav = Navigation.findNavController(requireView());
                        nav.navigate(R.id.productFormFragment);
                    } else {
                        products.remove(product);
                        binding.rvSellerProducts.getAdapter().notifyDataSetChanged();
                        Toast.makeText(requireContext(),
                                "Producto eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar la referencia al binding.
        // ¿Qué hace?: pone binding en null cuando la vista se destruye.
        binding = null;
    }
}