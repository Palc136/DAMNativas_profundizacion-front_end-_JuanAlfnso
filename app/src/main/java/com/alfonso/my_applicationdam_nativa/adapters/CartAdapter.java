package com.alfonso.my_applicationdam_nativa.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.my_applicationdam_nativa.databinding.ItemCartBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.List;

/**
 * CartAdapter conecta una lista de productos con el RecyclerView del carrito.
 * Muestra cada producto con nombre, precio y un botón para eliminarlo.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    // ¿Para qué sirve?: notificar al Fragment cuando se elimina un producto.
    // ¿Qué hace?: guarda un listener opcional OnRemoveClick.
    // ¿Cuál es su función en el código?: permite al carrito actualizar el total
    //             cuando el usuario elimina un producto.
    public interface OnRemoveClick {
        void onRemove(Product product);
    }

    private final List<Product> products;
    private final OnRemoveClick onRemoveClick;

    public CartAdapter(List<Product> products, OnRemoveClick onRemoveClick) {
        this.products = products;
        this.onRemoveClick = onRemoveClick;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        // ¿Para qué sirve?: exponer las vistas de item_cart.xml.
        // ¿Qué hace?: guarda el binding del ítem.
        // ¿Cuál es su función en el código?: permite al adapter rellenar nombre, precio y botón.
        final ItemCartBinding binding;

        public CartViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // ¿Para qué sirve?: inflar la celda del carrito.
        // ¿Qué hace?: construye item_cart.xml y lo envuelve en un CartViewHolder.
        // ¿Cuál es su función en el código?: fabrica las celdas del RecyclerView del carrito.
        ItemCartBinding binding = ItemCartBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        // ¿Para qué sirve?: rellenar los datos de una celda.
        // ¿Qué hace?: asigna nombre, precio y cantidad simulada.
        // ¿Cuál es su función en el código?: refleja el producto en la fila del carrito.
        Product product = products.get(position);
        holder.binding.tvName.setText(product.getName());
        holder.binding.tvPrice.setText("$" + String.format("%,.0f", product.getPrice()));
        holder.binding.tvQuantity.setText("Cantidad: 1");

        // ¿Para qué sirve?: conectar el botón eliminar.
        // ¿Qué hace?: invoca onRemove del listener con el producto a eliminar.
        // ¿Cuál es su función en el código?: permite al Fragment actualizar la lista y el total.
        holder.binding.btnRemove.setOnClickListener(v -> {
            if (onRemoveClick != null) {
                onRemoveClick.onRemove(product);
            }
        });
    }

    @Override
    public int getItemCount() {

        // ¿Para qué sirve?: informar cuántas filas debe dibujar el RecyclerView.
        return products.size();
    }
}
