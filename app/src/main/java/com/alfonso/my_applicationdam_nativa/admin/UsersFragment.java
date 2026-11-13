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

import com.alfonso.my_applicationdam_nativa.MainActivity;
import com.alfonso.my_applicationdam_nativa.R;
import com.alfonso.my_applicationdam_nativa.adapters.UserAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentUsersBinding;
import com.alfonso.my_applicationdam_nativa.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * UsersFragment muestra la lista de usuarios y permite crear, editar y eliminar.
 */
public class UsersFragment extends Fragment {

    private FragmentUsersBinding binding;
    private final List<User> users = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: llenar la lista con usuarios de ejemplo.
        users.clear();
        users.add(new User(1, "María Gómez", "maria@test.com", "Comprador", true));
        users.add(new User(2, "Carlos Ruiz", "carlos@test.com", "Vendedor", true));
        users.add(new User(3, "Ana Torres", "ana@test.com", "Vendedor", true));
        users.add(new User(4, "Pedro López", "pedro@test.com", "Comprador", false));

        // ¿Para qué sirve?: configurar el RecyclerView con UserAdapter.
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvUsers.setAdapter(new UserAdapter(users, this::showUserOptions));

        // ¿Para qué sirve?: ir al formulario de usuario nuevo.
        binding.fabAddUser.setOnClickListener(v -> {
            if (requireActivity() instanceof MainActivity) {
                ((MainActivity) requireActivity()).navigateToTab(R.id.userFormFragment);
            }
        });
    }

    private void showUserOptions(User user) {

        // ¿Para qué sirve?: mostrar opciones de gestión (editar rol, activar/desactivar).
        // ¿Qué hace?: construye un diálogo con opciones y actualiza el usuario según la elegida.
        new AlertDialog.Builder(requireContext())
                .setTitle(user.getName())
                .setItems(new CharSequence[]{"Editar rol", user.isActive() ? "Desactivar" : "Activar"}, (dialog, which) -> {
                    if (which == 0) {
                        new AlertDialog.Builder(requireContext())
                                .setTitle("Nuevo rol")
                                .setItems(new CharSequence[]{"Comprador", "Vendedor", "Administrador"},
                                        (d, r) -> {
                                            String[] roles = {"Comprador", "Vendedor", "Administrador"};
                                            user.setRole(roles[r]);
                                            binding.rvUsers.getAdapter().notifyDataSetChanged();
                                            Toast.makeText(requireContext(),
                                                    "Rol actualizado a " + user.getRole(),
                                                    Toast.LENGTH_SHORT).show();
                                        })
                                .show();
                    } else {
                        user.setActive(!user.isActive());
                        binding.rvUsers.getAdapter().notifyDataSetChanged();
                        Toast.makeText(requireContext(),
                                user.isActive() ? "Usuario activado" : "Usuario desactivado",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
