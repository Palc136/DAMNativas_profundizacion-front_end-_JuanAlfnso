package com.alfonso.my_applicationdam_nativa.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alfonso.my_applicationdam_nativa.MainActivity;
import com.alfonso.my_applicationdam_nativa.R;
import com.alfonso.my_applicationdam_nativa.databinding.FragmentAdminDashboardBinding;

/**
 * AdminDashboardFragment muestra los KPIs globales de PacoShop y accesos
 * rápidos a las demás pantallas del rol administrador.
 */
public class AdminDashboardFragment extends Fragment {

    private FragmentAdminDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar KPIs simulados.
        // ¿Qué hace?: asigna valores de ejemplo a los TextViews del dashboard.
        binding.tvTotalUsers.setText("248");
        binding.tvSellers.setText("37");
        binding.tvTotalProducts.setText("512");
        binding.tvCommissions.setText("$3.850.000");

        // ¿Para qué sirve?: navegar a Usuarios sin romper el BottomNav.
        // ¿Qué hace?: delega la navegación al BottomNavigationView vía navigateToTab().
        // ¿Cuál es su función en el código?: mantiene sincronizado el menú inferior.
        binding.btnGoUsers.setOnClickListener(v -> {
            if (requireActivity() instanceof MainActivity) {
                ((MainActivity) requireActivity()).navigateToTab(R.id.usersFragment);
            }
        });

        // ¿Para qué sirve?: navegar a Productos.
        binding.btnGoProducts.setOnClickListener(v -> {
            if (requireActivity() instanceof MainActivity) {
                ((MainActivity) requireActivity()).navigateToTab(R.id.adminProductsFragment);
            }
        });

        // ¿Para qué sirve?: navegar a Reportes.
        binding.btnGoReports.setOnClickListener(v -> {
            if (requireActivity() instanceof MainActivity) {
                ((MainActivity) requireActivity()).navigateToTab(R.id.salesReportFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
