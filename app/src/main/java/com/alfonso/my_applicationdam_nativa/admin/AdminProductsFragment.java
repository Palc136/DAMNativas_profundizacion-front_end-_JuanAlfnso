package com.alfonso.my_applicationdam_nativa.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.my_applicationdam_nativa.adapters.ProductAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentAdminProductsBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * AdminProductsFragment permite al admin supervisar todos los productos del sistema.
 * Con long-press puede "desactivar" un producto (mock).
 */
public class AdminProductsFragment extends Fragment {

    private FragmentAdminProductsBinding binding;
    private final List<Product> products = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar productos mock de todos los vendedores.
        products.clear();
        products.add(new Product(1, "Mochila wayuu", 120000.0, "Artesanías del Sur", "Artesanías"));
        products.add(new Product(2, "Café artesanal", 25000.0, "Café del Huila", "Alimentos"));
        products.add(new Product(3, "Joyería en plata", 85000.0, "Orfebres locales", "Joyería"));
        products.add(new Product(4, "Textil bordado", 60000.0, "Bordados del Atlántico", "Textiles"));
        products.add(new Product(5, "Jabón natural", 18000.0, "Naturaleza viva", "Cuidado personal"));

        binding.rvAdminProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvAdminProducts.setAdapter(new ProductAdapter(products));

        // ¿Para qué sirve?: permitir "desactivar" un producto con long-press.
        // ¿Qué hace?: detecta long-press y muestra un diálogo con la acción "Desactivar".
        final android.view.GestureDetector detector = new android.view.GestureDetector(
                requireContext(),
                new android.view.GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(@NonNull android.view.MotionEvent e) {
                        View child = binding.rvAdminProducts.findChildViewUnder(e.getX(), e.getY());
                        if (child != null) {
                            int pos = binding.rvAdminProducts.getChildAdapterPosition(child);
                            if (pos >= 0 && pos < products.size()) {
                                Product p = products.get(pos);
                                new AlertDialog.Builder(requireContext())
                                        .setTitle(p.getName())
                                        .setItems(new CharSequence[]{"Desactivar producto"}, (d, w) -> {
                                            Toast.makeText(requireContext(),
                                                    "Producto desactivado (mock)",
                                                    Toast.LENGTH_SHORT).show();
                                        })
                                        .show();
                            }
                        }
                    }
                });

        binding.rvAdminProducts.addOnItemTouchListener(
                new RecyclerView.SimpleOnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv,
                                                         @NonNull android.view.MotionEvent e) {
                        return detector.onTouchEvent(e);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
