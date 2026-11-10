package com.alfonso.my_applicationdam_nativa.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.my_applicationdam_nativa.databinding.ItemProductBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.List;

/**
 * ProductAdapter conecta una lista de objetos Product con un RecyclerView.
 * Crea celdas a partir de item_product.xml y las rellena con los datos de cada producto.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    // ¿Para qué sirve?: guardar la lista de productos a dibujar.
    // ¿Qué hace?: mantiene la referencia a los datos que el adapter va a mostrar.
    // ¿Cuál es su función en el código?: fuente de información para onBindViewHolder
    //             y para contar cuántas celdas debe crear el RecyclerView.
    private final List<Product> products;

    // ¿Para qué sirve?: notificar a la pantalla cuando el usuario pulsa un producto.
    // ¿Qué hace?: guarda una referencia a un listener opcional (interfaz OnProductClick).
    // ¿Cuál es su función en el código?: permite que CatalogFragment navegue al detalle
    //             al tocar una tarjeta; si es null, el click solo muestra un Toast.
    private final OnProductClick onProductClick;

    /**
     * Interfaz para manejar el click sobre un producto.
     */
    public interface OnProductClick {
        void onClick(Product product);
    }

    /**
     * Constructor sin listener (uso simple).
     */
    public ProductAdapter(List<Product> products) {
        this(products, null);
    }

    /**
     * Constructor con listener (uso en catálogo con navegación).
     */
    public ProductAdapter(List<Product> products, OnProductClick onProductClick) {
        this.products = products;
        this.onProductClick = onProductClick;
    }

    /**
     * ProductViewHolder contiene las referencias a las vistas de item_product.xml.
     */
    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        // ¿Para qué sirve?: exponer las vistas del ítem para que el adapter las rellene.
        // ¿Qué hace?: recibe el binding del item y lo guarda como atributo.
        // ¿Cuál es su función en el código?: contenedor reutilizable por el RecyclerView.
        final ItemProductBinding binding;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // ¿Para qué sirve?: crear la vista de una celda cuando el RecyclerView la necesita.
        // ¿Qué hace?: infla item_product.xml con el LayoutInflater del padre
        //             y lo envuelve en un ProductViewHolder.
        // ¿Cuál es su función en el código?: fabrica las celdas que el RecyclerView reutilizará.
        ItemProductBinding binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        // ¿Para qué sirve?: rellenar las vistas de una celda con los datos del producto.
        // ¿Qué hace?: obtiene el producto en la posición indicada y asigna nombre,
        //             categoría, precio y acción del botón "Agregar al carrito".
        // ¿Cuál es su función en el código?: conecta el modelo Product con la UI del ítem.
        Product product = products.get(position);
        holder.binding.tvName.setText(product.getName());
        holder.binding.tvCategory.setText(product.getCategory());
        holder.binding.tvPrice.setText("$" + String.format("%,.0f", product.getPrice()));

        // ¿Para qué sirve?: manejar el click del botón "Agregar al carrito".
        // ¿Qué hace?: por ahora solo muestra un Toast confirmando la acción.
        // ¿Cuál es su función en el código?: gancho de interacción para el usuario;
        //             más adelante podría añadir el producto a una lista compartida del carrito.
        holder.binding.btnAdd.setOnClickListener(v ->
                Toast.makeText(v.getContext(),
                        product.getName() + " agregado al carrito",
                        Toast.LENGTH_SHORT).show());

        // ¿Para qué sirve?: manejar el click sobre toda la tarjeta del producto.
        // ¿Qué hace?: si existe un listener (OnProductClick), lo invoca con el producto;
        //             si no, no hace nada especial.
        // ¿Cuál es su función en el código?: permite al catálogo navegar al detalle del producto.
        holder.binding.getRoot().setOnClickListener(v -> {
            if (onProductClick != null) {
                onProductClick.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {

        // ¿Para qué sirve?: informar al RecyclerView cuántas celdas debe dibujar.
        // ¿Qué hace?: devuelve el tamaño de la lista de productos.
        // ¿Cuál es su función en el código?: controla cuántas veces se llaman
        //             onCreateViewHolder y onBindViewHolder.
        return products.size();
    }
}