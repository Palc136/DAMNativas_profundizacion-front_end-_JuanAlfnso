package com.alfonso.my_applicationdam_nativa.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alfonso.my_applicationdam_nativa.databinding.FragmentSalesReportBinding;

/**
 * SalesReportFragment muestra los reportes globales de ventas (mock).
 */
public class SalesReportFragment extends Fragment {

    private FragmentSalesReportBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSalesReportBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: cargar cifras simuladas del reporte.
        binding.tvTotalRevenue.setText("$12.450.000");
        binding.tvTotalOrders.setText("87");
        binding.tvTopSeller.setText("Artesanías del Sur");

        // ¿Para qué sirve?: simular exportación del reporte.
        binding.btnExport.setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        "Reporte exportado (mock)", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
