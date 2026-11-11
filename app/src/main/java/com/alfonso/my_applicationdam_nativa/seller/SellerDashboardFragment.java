package com.alfonso.my_applicationdam_nativa.seller;
import com.alfonso.my_applicationdam_nativa.R;
import com.alfonso.my_applicationdam_nativa.MainActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alfonso.my_applicationdam_nativa.databinding.FragmentSellerDashboardBinding;

/**
 * SellerDashboardFragment muestra un resumen del negocio del vendedor
 * con KPIs simulados y accesos rápidos a Productos y Pedidos.
 */
public class SellerDashboardFragment extends Fragment {

    private FragmentSellerDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar fragment_seller_dashboard.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        binding = FragmentSellerDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar los KPIs mock del vendedor.
        // ¿Qué hace?: asigna valores de ejemplo a los TextViews.
        // ¿Cuál es su función en el código?: da contenido realista a la pantalla sin backend.
        binding.tvSalesToday.setText("$1.250.000");
        binding.tvPendingOrders.setText("3");
        binding.tvTotalProducts.setText("12");
        binding.tvLowStock.setText("2");

        // ¿Para qué sirve?: navegar a Productos desde el botón del Dashboard.
// ¿Qué hace?: en vez de usar nav.navigate() directo, delega la navegación al
//             BottomNavigationView a través del método navigateToTab() de MainActivity.
// ¿Cuál es su función en el código?: mantiene sincronizado el BottomNav con la pantalla
//             actual y evita que el menú inferior se quede bloqueado.
        binding.btnGoProducts.setOnClickListener(v -> {
            if (requireActivity() instanceof MainActivity) {
                ((MainActivity) requireActivity()).navigateToTab(R.id.sellerProductsFragment);
            }
        });

// ¿Para qué sirve?: navegar a Pedidos desde el botón del Dashboard.
// ¿Qué hace?: mismo mecanismo que el botón anterior, pero hacia sellerOrdersFragment.
// ¿Cuál es su función en el código?: misma sincronización garantizada.
        binding.btnGoOrders.setOnClickListener(v -> {
            if (requireActivity() instanceof MainActivity) {
                ((MainActivity) requireActivity()).navigateToTab(R.id.sellerOrdersFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar el binding al destruirse la vista.
        binding = null;
    }
}