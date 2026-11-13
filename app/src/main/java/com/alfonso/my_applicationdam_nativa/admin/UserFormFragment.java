package com.alfonso.my_applicationdam_nativa.admin;

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

import com.alfonso.my_applicationdam_nativa.R;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentUserFormBinding;

/**
 * UserFormFragment permite crear un usuario nuevo (mock).
 */
public class UserFormFragment extends Fragment {

    private FragmentUserFormBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUserFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: manejar el guardado del usuario.
        // ¿Qué hace?: valida nombre y correo, muestra Toast y regresa al listado.
        binding.btnSave.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Nombre y correo son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(requireContext(),
                    "Usuario creado (mock)", Toast.LENGTH_SHORT).show();

            // ¿Para qué sirve?: volver al listado de usuarios tras crear.
            // ¿Qué hace?: usa popBackStack para regresar al Fragment anterior.
            NavController nav = Navigation.findNavController(requireView());
            nav.popBackStack(R.id.usersFragment, false);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}