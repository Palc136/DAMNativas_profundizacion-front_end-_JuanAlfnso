package com.alfonso.my_applicationdam_nativa.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alfonso.my_applicationdam_nativa.databinding.FragmentProductDetailBinding;

/**
 * ProductDetailFragment muestra el detalle completo de un producto.
 * Recibe los datos del producto por Bundle desde CatalogFragment.
 */
public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar el layout fragment_product_detail.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        // ¿Cuál es su función en el código?: vista raíz de la pantalla de detalle.
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: leer los datos del producto enviados por CatalogFragment.
        // ¿Qué hace?: obtiene el Bundle de argumentos y extrae nombre, categoría,
        //             precio y descripción con valores por defecto.
        // ¿Cuál es su función en el código?: alimenta la interfaz del detalle con los
        //             datos correctos del producto seleccionado.
        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("productName", "Producto");
            String category = args.getString("productCategory", "Sin categoría");
            double price = args.getDouble("productPrice", 0.0);
            String description = args.getString("productDescription", "Sin descripción");

            binding.tvName.setText(name);
            binding.tvCategory.setText(category);
            binding.tvPrice.setText("$" + String.format("%,.0f", price));
            binding.tvDescription.setText(description);
        }

        // ¿Para qué sirve?: manejar el click del botón "Agregar al carrito".
        // ¿Qué hace?: muestra un Toast confirmando la acción.
        // ¿Cuál es su función en el código?: gancho de interacción del usuario.
        binding.btnAddToCart.setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        "Producto agregado al carrito",
                        Toast.LENGTH_SHORT).show());
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
