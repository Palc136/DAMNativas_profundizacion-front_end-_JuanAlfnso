package com.alfonso.my_applicationdam_nativa.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.my_applicationdam_nativa.databinding.ItemOrderBinding;
import com.alfonso.my_applicationdam_nativa.model.Order;

import java.util.List;

/**
 * OrderAdapter conecta una lista de pedidos con el RecyclerView.
 * Pinta el badge de estado con el color correspondiente y permite cambiar el estado.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    public interface OnChangeStatusClick {
        void onChange(Order order);
    }

    private final List<Order> orders;
    private final OnChangeStatusClick listener;

    public OrderAdapter(List<Order> orders, OnChangeStatusClick listener) {
        this.orders = orders;
        this.listener = listener;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        // ¿Para qué sirve?: exponer las vistas del pedido al adapter.
        final ItemOrderBinding binding;

        public OrderViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // ¿Para qué sirve?: inflar la celda de pedido.
        // ¿Qué hace?: construye item_order.xml y lo envuelve en un ViewHolder.
        ItemOrderBinding binding = ItemOrderBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        // ¿Para qué sirve?: rellenar la celda con los datos del pedido.
        // ¿Qué hace?: asigna comprador, resumen, total, estado y color del badge.
        Order order = orders.get(position);
        holder.binding.tvBuyer.setText(order.getBuyerName());
        holder.binding.tvSummary.setText(order.getProductSummary());
        holder.binding.tvTotal.setText("$" + String.format("%,.0f", order.getTotal()));
        holder.binding.tvStatus.setText(order.getStatus());

        // ¿Para qué sirve?: asignar color al badge según el estado.
        // ¿Qué hace?: verde (Entregado), amarillo (Enviado), azul (Pendiente), rojo (Cancelado).
        int color;
        switch (order.getStatus()) {
            case "Entregado": color = Color.parseColor("#2E7D32"); break;
            case "Enviado":   color = Color.parseColor("#F9A825"); break;
            case "Cancelado": color = Color.parseColor("#C62828"); break;
            default:          color = Color.parseColor("#1565C0"); break;
        }
        holder.binding.tvStatus.setBackgroundColor(color);

        // ¿Para qué sirve?: conectar el botón "Cambiar estado".
        // ¿Qué hace?: invoca el listener con el pedido a modificar.
        holder.binding.btnChangeStatus.setOnClickListener(v -> {
            if (listener != null) listener.onChange(order);
        });
    }

    @Override
    public int getItemCount() {

        // ¿Para qué sirve?: informar cuántas celdas debe dibujar el RecyclerView.
        return orders.size();
    }
}
