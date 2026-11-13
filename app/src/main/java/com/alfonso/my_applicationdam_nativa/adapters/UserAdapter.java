package com.alfonso.my_applicationdam_nativa.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.my_applicationdam_nativa.databinding.ItemUserBinding;
import com.alfonso.my_applicationdam_nativa.model.User;

import java.util.List;

/**
 * UserAdapter conecta una lista de usuarios con el RecyclerView del admin.
 * Pinta un badge de color según el rol y el estado del usuario.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public interface OnManageClick {
        void onManage(User user);
    }

    private final List<User> users;
    private final OnManageClick listener;

    public UserAdapter(List<User> users, OnManageClick listener) {
        this.users = users;
        this.listener = listener;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        // ¿Para qué sirve?: exponer las vistas del ítem al adapter.
        final ItemUserBinding binding;

        public UserViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // ¿Para qué sirve?: inflar la celda de usuario.
        ItemUserBinding binding = ItemUserBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        // ¿Para qué sirve?: rellenar la celda con los datos del usuario.
        User user = users.get(position);
        holder.binding.tvName.setText(user.getName());
        holder.binding.tvEmail.setText(user.getEmail());
        holder.binding.tvRole.setText(user.getRole());

        // ¿Para qué sirve?: asignar color al badge de rol según su tipo.
        int roleColor;
        switch (user.getRole()) {
            case "Administrador": roleColor = Color.parseColor("#C62828"); break;
            case "Vendedor":      roleColor = Color.parseColor("#F9A825"); break;
            default:              roleColor = Color.parseColor("#1565C0"); break;
        }
        holder.binding.tvRole.setBackgroundColor(roleColor);

        // ¿Para qué sirve?: mostrar estado activo / inactivo con color.
        holder.binding.tvStatus.setText(user.isActive() ? "Activo" : "Inactivo");
        holder.binding.tvStatus.setTextColor(
                user.isActive() ? Color.parseColor("#2E7D32") : Color.parseColor("#C62828"));

        // ¿Para qué sirve?: conectar el botón "Gestionar" al listener.
        holder.binding.btnManage.setOnClickListener(v -> {
            if (listener != null) listener.onManage(user);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
