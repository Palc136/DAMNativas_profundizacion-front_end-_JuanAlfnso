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

import com.alfonso.my_applicationdam_nativa.databinding.FragmentCheckoutBinding;

/**
 * CheckoutFragment gestiona la confirmación de pago.
 * Captura la dirección y el método de pago elegido por el usuario.
 */
public class CheckoutFragment extends Fragment {

    private FragmentCheckoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ¿Para qué sirve?: inflar el layout fragment_checkout.xml.
        // ¿Qué hace?: construye la jerarquía de vistas y devuelve el binding.
        // ¿Cuál es su función en el código?: vista raíz del checkout.
        binding = FragmentCheckoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ¿Para qué sirve?: manejar el click de "Confirmar pago".
        // ¿Qué hace?: valida la dirección y muestra el método de pago elegido
        //             mediante un Toast, luego regresa al inicio.
        // ¿Cuál es su función en el código?: simula el proceso de pago.
        binding.btnConfirm.setOnClickListener(v -> {
            String address = binding.etAddress.getText().toString().trim();
            if (address.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Ingresa una dirección de envío", Toast.LENGTH_SHORT).show();
                return;
            }

            String method;
            int selected = binding.rgPayment.getCheckedRadioButtonId();
            if (selected == R.id.rbPse) {
                method = "PSE";
            } else if (selected == R.id.rbCash) {
                method = "Contra entrega";
            } else {
                method = "Tarjeta";
            }

            Toast.makeText(requireContext(),
                    "Pago confirmado con " + method, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ¿Para qué sirve?: liberar el binding al destruirse la vista.
        binding = null;
    }
}
