package com.example.combatepokemon;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class movimientosFragment extends Fragment {

    private CombateModelView pokemonViewModel;

    // Variables para almacenar los valores del progreso
    private int progresoP1;
    private int progresoP2;

    public movimientosFragment() {
        // Constructor vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragment
        View view = inflater.inflate(R.layout.fragment_movimientos, container, false);

        // Obtener la instancia del ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(CombateModelView.class);

        // Obtener referencias a los botones y ProgressBars
        Button cascadaButton = view.findViewById(R.id.cascada);
        ProgressBar vidaP1 = getActivity().findViewById(R.id.VidaP1);
        ProgressBar vidaP2 = getActivity().findViewById(R.id.VidaP2);

        // Restaurar el progreso de las barras de vida al iniciar el fragmento

        vidaP1.setProgress(pokemonViewModel.getProgreso1().getValue());
        vidaP2.setProgress(pokemonViewModel.getProgreso2().getValue());

        // Configurar el botón de Cascada
        cascadaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemonViewModel.usarCascada();

            }
        });
                pokemonViewModel.getAcabado().observe(getViewLifecycleOwner(),acabado -> {
                        if (acabado){
                            Toast.makeText(getActivity(), "¡Combate terminado!", Toast.LENGTH_SHORT).show();
                        }
});
                pokemonViewModel.getVidaP1().observe(getViewLifecycleOwner(), vidaP1::setProgress);
                pokemonViewModel.getVidaP2().observe(getViewLifecycleOwner(), vidaP2::setProgress);
                return view;
    }

    // Método para guardar el progreso en las ProgressBars
    private void saveProgress(int progresoP1, int progresoP2) {
        this.progresoP1 = progresoP1;
        this.progresoP2 = progresoP2;
    }




}


