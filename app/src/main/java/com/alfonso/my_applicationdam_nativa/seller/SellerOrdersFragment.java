package com.alfonso.my_applicationdam_nativa.seller;

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

import com.alfonso.my_applicationdam_nativa.adapters.OrderAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentSellerOrdersBinding;
import com.alfonso.my_applicationdam_nativa.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * SellerOrdersFragment lista los pedidos recibidos por el vendedor
 * y permite cambiar el estado de cada uno.
 */
public class SellerOrdersFragment extends Fragment {

    private FragmentSellerOrdersBinding binding;

    private final List<Order> orders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSellerOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: llenar la lista con pedidos de ejemplo.
        // ¿Qué hace?: añade 3 pedidos mock con estados variados.
        orders.clear();
        orders.add(new Order(1, "María Gómez", "Mochila wayuu x1", 120000.0, "Pendiente"));
        orders.add(new Order(2, "Carlos Ruiz", "Café artesanal x2", 50000.0, "Enviado"));
        orders.add(new Order(3, "Ana Torres", "Joyería en plata x1", 85000.0, "Entregado"));

        // ¿Para qué sirve?: configurar el RecyclerView con OrderAdapter.
        // ¿Qué hace?: asigna layoutManager y adapter con listener de cambio de estado.
        binding.rvOrders.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvOrders.setAdapter(new OrderAdapter(orders, this::showStatusDialog));
    }

    private void showStatusDialog(Order order) {

        // ¿Para qué sirve?: permitir cambiar el estado del pedido.
        // ¿Qué hace?: muestra un diálogo con los estados disponibles y actualiza el pedido.
        new AlertDialog.Builder(requireContext())
                .setTitle("Cambiar estado")
                .setItems(new CharSequence[]{"Pendiente", "Enviado", "Entregado", "Cancelado"},
                        (dialog, which) -> {
                            String[] estados = {"Pendiente", "Enviado", "Entregado", "Cancelado"};
                            order.setStatus(estados[which]);
                            binding.rvOrders.getAdapter().notifyDataSetChanged();
                            Toast.makeText(requireContext(),
                                    "Estado actualizado: " + order.getStatus(),
                                    Toast.LENGTH_SHORT).show();
                        })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}