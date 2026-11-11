package com.alfonso.my_applicationdam_nativa.buyer;
import com.alfonso.my_applicationdam_nativa.R;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alfonso.my_applicationdam_nativa.adapters.CartAdapter;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentCartBinding;
import com.alfonso.my_applicationdam_nativa.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * CartFragment muestra los productos agregados al carrito, el total y
 * el botón para pasar al checkout.
 */
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    // ¿Para qué sirve?: guardar los productos que hay en el carrito.
    // ¿Qué hace?: mantiene la lista mutable que el adapter y el total leen.
    // ¿Cuál es su función en el código?: fuente de datos del carrito.
    private final List<Product> cartProducts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar el layout fragment_cart.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        // ¿Cuál es su función en el código?: vista raíz del carrito.
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar productos de ejemplo al carrito.
        // ¿Qué hace?: llena cartProducts con datos mock.
        // ¿Cuál es su función en el código?: permite ver la interfaz con datos realistas.
        loadMockCart();

        // ¿Para qué sirve?: navegar al checkout.
        // ¿Qué hace?: al pulsar "Ir a pagar", verifica que el carrito no esté vacío y navega.
        // ¿Cuál es su función en el código?: conecta el carrito con el flujo de pago.
        binding.btnCheckout.setOnClickListener(v -> {
            if (cartProducts.isEmpty()) {
                Toast.makeText(requireContext(),
                        "El carrito está vacío", Toast.LENGTH_SHORT).show();
                return;
            }
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.checkoutFragment);
        });
    }

    /**
     * Carga productos de ejemplo y configura el RecyclerView.
     */
    private void loadMockCart() {

        // ¿Para qué sirve?: llenar el carrito con productos de prueba.
        // ¿Qué hace?: añade 2 productos al carrito.
        // ¿Cuál es su función en el código?: permite visualizar la pantalla con datos.
        cartProducts.clear();
        cartProducts.add(new Product(1, "Mochila wayuu", 120000.0));
        cartProducts.add(new Product(3, "Joyería en plata", 85000.0));

        // ¿Para qué sirve?: configurar el RecyclerView del carrito.
        // ¿Qué hace?: asigna LayoutManager y CartAdapter con listener de eliminación.
        // ¿Cuál es su función en el código?: muestra los productos y responde al botón eliminar.
        binding.rvCart.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCart.setAdapter(new CartAdapter(cartProducts, this::removeProduct));

        updateTotal();
    }

    /**
     * Elimina un producto del carrito y actualiza la UI.
     */
    private void removeProduct(Product product) {

        // ¿Para qué sirve?: quitar el producto de la lista.
        // ¿Qué hace?: lo remueve de cartProducts y notifica al adapter.
        // ¿Cuál es su función en el código?: refleja la eliminación en pantalla.
        cartProducts.remove(product);
        binding.rvCart.getAdapter().notifyDataSetChanged();

        updateTotal();
    }

    /**
     * Calcula el total sumando los precios de los productos del carrito.
     */
    private void updateTotal() {

        // ¿Para qué sirve?: calcular la suma de precios.
        // ¿Qué hace?: recorre cartProducts y acumula precios.
        // ¿Cuál es su función en el código?: actualiza el TextView del total.
        double total = 0;
        for (Product p : cartProducts) {
            total += p.getPrice();
        }
        binding.tvTotal.setText("Total: $" + String.format("%,.0f", total));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar el binding al destruirse la vista.
        binding = null;
    }
}
