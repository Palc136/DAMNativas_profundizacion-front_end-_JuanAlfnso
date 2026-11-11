package com.alfonso.my_applicationdam_nativa.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alfonso.my_applicationdam_nativa.LoginActivity;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentBuyerProfileBinding;

/**
 * BuyerProfileFragment muestra el perfil del comprador y opciones de cuenta.
 */
public class BuyerProfileFragment extends Fragment {

    private FragmentBuyerProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar el layout fragment_buyer_profile.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        // ¿Cuál es su función en el código?: vista raíz del perfil del comprador.
        binding = FragmentBuyerProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: manejar el click de "Editar perfil".
        // ¿Qué hace?: muestra un Toast informativo (mock).
        // ¿Cuál es su función en el código?: gancho para futuro formulario de edición.
        binding.btnEditProfile.setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        "Editar perfil (próximamente)", Toast.LENGTH_SHORT).show());

        // ¿Para qué sirve?: manejar el click de "Mis direcciones".
        // ¿Qué hace?: muestra un Toast informativo (mock).
        // ¿Cuál es su función en el código?: gancho para futuro gestor de direcciones.
        binding.btnMyAddresses.setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        "Mis direcciones (próximamente)", Toast.LENGTH_SHORT).show());

        // ¿Para qué sirve?: cerrar sesión y volver al Login.
        // ¿Qué hace?: lanza LoginActivity limpiando la pila de Activities.
        // ¿Cuál es su función en el código?: completa el flujo de "Cerrar sesión".
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar el binding al destruirse la vista.
        binding = null;
    }
}
