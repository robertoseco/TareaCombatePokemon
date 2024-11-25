package com.example.combatepokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DatosFragment extends Fragment {

    private EditText p1Nombre, p1PS, p1Ataque, p1AtaqueEspecial, p1Defensa, p1DefensaEspecial;
    private EditText p2Nombre, p2PS, p2Ataque, p2AtaqueEspecial, p2Defensa, p2DefensaEspecial;
    private Button btnConfirmar;
    private CombateModelView pokemonViewModel;  // ViewModel

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos, container, false);

        // Inicializar EditTexts y botón
        p1Nombre = view.findViewById(R.id.P1nombre);
        p1PS = view.findViewById(R.id.P1PS);
        p1Ataque = view.findViewById(R.id.P1ataque);
        p1AtaqueEspecial = view.findViewById(R.id.P1ataqueEspecial);
        p1Defensa = view.findViewById(R.id.P1defensa);
        p1DefensaEspecial = view.findViewById(R.id.P1defensaEspecial);
        p2Nombre = view.findViewById(R.id.P2nombre);
        p2PS = view.findViewById(R.id.P2PS);
        p2Ataque = view.findViewById(R.id.P2ataque);
        p2AtaqueEspecial = view.findViewById(R.id.P2ataqueEspecial);
        p2Defensa = view.findViewById(R.id.P2defensa);
        p2DefensaEspecial = view.findViewById(R.id.P2defensaEspecial);

        btnConfirmar = view.findViewById(R.id.confirmar);

        // Obtener el ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(CombateModelView.class);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    // Actualizar el ViewModel con los datos
                    pokemonViewModel.setP1Nombre(p1Nombre.getText().toString());
                    pokemonViewModel.setP1PS(p1PS.getText().toString());
                    pokemonViewModel.vidaP1.postValue(Integer.parseInt(p1PS.getText().toString()));
                    pokemonViewModel.setP1Ataque(p1Ataque.getText().toString());
                    pokemonViewModel.setP1AtaqueEspecial(p1AtaqueEspecial.getText().toString());
                    pokemonViewModel.setP1Defensa(p1Defensa.getText().toString());
                    pokemonViewModel.setP1DefensaEspecial(p1DefensaEspecial.getText().toString());

                    pokemonViewModel.setP2Nombre(p2Nombre.getText().toString());
                    pokemonViewModel.setP2PS(p2PS.getText().toString());
                    pokemonViewModel.setP2Ataque(p2Ataque.getText().toString());
                    pokemonViewModel.setP2AtaqueEspecial(p2AtaqueEspecial.getText().toString());
                    pokemonViewModel.setP2Defensa(p2Defensa.getText().toString());
                    pokemonViewModel.setP2DefensaEspecial(p2DefensaEspecial.getText().toString());
                    pokemonViewModel.vidaP2.postValue(Integer.parseInt(p2PS.getText().toString()));
                    pokemonViewModel.setProgreso1(100);
                    pokemonViewModel.setProgreso2(100);
                    // Cambiar al fragmento de combate
                    ((MainActivity) getActivity()).cambiarACombateFragment();
                } else {
                    Toast.makeText(getActivity(), "Por favor, completa todos los campos correctamente.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private boolean validarCampos() {
        // Validar campos numéricos
        if (!esNumeroValido(p1PS) || !esNumeroValido(p1Ataque) || !esNumeroValido(p1AtaqueEspecial) || !esNumeroValido(p1Defensa) || !esNumeroValido(p1DefensaEspecial)) {
            return false;
        }

        if (!esNumeroValido(p2PS) || !esNumeroValido(p2Ataque) || !esNumeroValido(p2AtaqueEspecial) || !esNumeroValido(p2Defensa) || !esNumeroValido(p2DefensaEspecial)) {
            return false;
        }

        // Validar que los nombres no estén vacíos
        return !p1Nombre.getText().toString().isEmpty() && !p2Nombre.getText().toString().isEmpty();
    }

    // Método para verificar si un campo contiene un número válido
    private boolean esNumeroValido(EditText editText) {
        String text = editText.getText().toString();
        try {
            Integer.parseInt(text); // Intenta convertir a número
            return true;
        } catch (NumberFormatException e) {
            editText.setError("Por favor, ingrese un número válido");
            return false;
        }
    }
}



