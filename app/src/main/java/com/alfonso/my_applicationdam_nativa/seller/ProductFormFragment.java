package com.alfonso.my_applicationdam_nativa.seller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alfonso.my_applicationdam_nativa.databinding.FragmentProductFormBinding;

/**
 * ProductFormFragment permite crear o editar un producto del vendedor.
 * Simula el guardado mostrando un Toast y regresando al listado.
 */
public class ProductFormFragment extends Fragment {

    private FragmentProductFormBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProductFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: manejar el guardado del producto.
        // ¿Qué hace?: valida los campos obligatorios, muestra un Toast y regresa al listado.
        // ¿Cuál es su función en el código?: cierra el ciclo de creación de producto (mock).
        binding.btnSave.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            String price = binding.etPrice.getText().toString().trim();

            if (name.isEmpty() || price.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Nombre y precio son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(requireContext(),
                    "Producto guardado", Toast.LENGTH_SHORT).show();

            NavController nav = Navigation.findNavController(requireView());
            nav.popBackStack();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
