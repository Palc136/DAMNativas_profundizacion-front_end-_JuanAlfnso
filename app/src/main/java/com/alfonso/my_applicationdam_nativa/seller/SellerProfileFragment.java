package com.alfonso.my_applicationdam_nativa.seller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alfonso.my_applicationdam_nativa.databinding.FragmentSellerProfileBinding;

/**
 * SellerProfileFragment muestra los datos públicos de la tienda del vendedor.
 */
public class SellerProfileFragment extends Fragment {

    private FragmentSellerProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSellerProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: manejar el click de "Editar datos".
        // ¿Qué hace?: muestra un Toast informativo (mock).
        binding.btnEditShop.setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        "Editar tienda (próximamente)", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}