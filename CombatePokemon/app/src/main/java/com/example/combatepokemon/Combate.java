package com.example.combatepokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class Combate extends Fragment {

    private CombateModelView viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_combate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mantener el código para imágenes
        ImageView imageViewGif = view.findViewById(R.id.gyarados);
        Glide.with(this).asGif().load(R.drawable.gyarados).into(imageViewGif);

        ImageView imageViewGif1 = view.findViewById(R.id.seviper);
        Glide.with(this).asGif().load(R.drawable.seviper).into(imageViewGif1);

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(CombateModelView.class);

        // Inicializar TextViews
        TextView p1NombreTextView = view.findViewById(R.id.p1NombreTextView);
        TextView p1PSTextView = view.findViewById(R.id.p1PSTextView);
        TextView p1AtaqueTextView = view.findViewById(R.id.p1AtaqueTextView);
        TextView p1AtaqueEspecialTextView = view.findViewById(R.id.p1AtaqueEspecialTextView);
        TextView p1DefensaTextView = view.findViewById(R.id.p1DefensaTextView);
        TextView p1DefensaEspecialTextView = view.findViewById(R.id.p1DefensaEspecialTextView);
        ProgressBar vidaP1 = view.findViewById(R.id.VidaP1);

        TextView p2NombreTextView = view.findViewById(R.id.p2NombreTextView);
        TextView p2PSTextView = view.findViewById(R.id.p2PSTextView);
        TextView p2AtaqueTextView = view.findViewById(R.id.p2AtaqueTextView);
        TextView p2AtaqueEspecialTextView = view.findViewById(R.id.p2AtaqueEspecialTextView);
        TextView p2DefensaTextView = view.findViewById(R.id.p2DefensaTextView);
        TextView p2DefensaEspecialTextView = view.findViewById(R.id.p2DefensaEspecialTextView);
        ProgressBar vidaP2 = view.findViewById(R.id.VidaP2);

        // Observar y actualizar TextViews
        viewModel.getP1Nombre().observe(getViewLifecycleOwner(), nombre -> p1NombreTextView.setText("Nombre P1: " + nombre));
        viewModel.getP1PS().observe(getViewLifecycleOwner(), ps -> p1PSTextView.setText("PS P1: " + ps));
        viewModel.getP1Ataque().observe(getViewLifecycleOwner(), ataque -> p1AtaqueTextView.setText("Ataque P1: " + ataque));
        viewModel.getP1AtaqueEspecial().observe(getViewLifecycleOwner(), ataqueEspecial -> p1AtaqueEspecialTextView.setText("Ataque Especial P1: " + ataqueEspecial));
        viewModel.getP1Defensa().observe(getViewLifecycleOwner(), defensa -> p1DefensaTextView.setText("Defensa P1: " + defensa));
        viewModel.getP1DefensaEspecial().observe(getViewLifecycleOwner(), defensaEspecial -> p1DefensaEspecialTextView.setText("Defensa Especial P1: " + defensaEspecial));
        viewModel.getVidaP1().observe(getViewLifecycleOwner(), vidaP1::setProgress);

        viewModel.getP2Nombre().observe(getViewLifecycleOwner(), nombre -> p2NombreTextView.setText("Nombre P2: " + nombre));
        viewModel.getP2PS().observe(getViewLifecycleOwner(), ps -> p2PSTextView.setText("PS P2: " + ps));
        viewModel.getP2Ataque().observe(getViewLifecycleOwner(), ataque -> p2AtaqueTextView.setText("Ataque P2: " + ataque));
        viewModel.getP2AtaqueEspecial().observe(getViewLifecycleOwner(), ataqueEspecial -> p2AtaqueEspecialTextView.setText("Ataque Especial P2: " + ataqueEspecial));
        viewModel.getP2Defensa().observe(getViewLifecycleOwner(), defensa -> p2DefensaTextView.setText("Defensa P2: " + defensa));
        viewModel.getP2DefensaEspecial().observe(getViewLifecycleOwner(), defensaEspecial -> p2DefensaEspecialTextView.setText("Defensa Especial P2: " + defensaEspecial));
        viewModel.getVidaP2().observe(getViewLifecycleOwner(), vidaP2::setProgress);
    }
}


