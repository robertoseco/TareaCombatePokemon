package com.example.combatepokemon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Button btnAtacar;  // Botón "Atacar"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAtacar = findViewById(R.id.atacar);
        btnAtacar.setVisibility(View.GONE); // Ocultar hasta que se muestre CombateFragment

        // Cargar DatosFragment si no hay estado guardado
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.combate1, new DatosFragment())
                    .commit();
        }

        // Configuración del botón "Atacar"
        btnAtacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMovimientosFragment();
                return;
            }
        });

        // Escucha cambios en la pila de retroceso para gestionar visibilidades
        getSupportFragmentManager().addOnBackStackChangedListener(new androidx.fragment.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                handleBackStackChanges();
            }
        });
    }

    public void cambiarACombateFragment() {
        Fragment combateFragment = new Combate();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.combate1, combateFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        btnAtacar.setVisibility(View.VISIBLE);  // Mostrar el botón al cargar CombateFragment
    }

    private void showMovimientosFragment() {
        // Mostrar MovimientosFragment
        findViewById(R.id.movimientosContainer).setVisibility(View.VISIBLE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.movimientosContainer, new movimientosFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        // Asegurarse de ocultar el botón después de la transacción
        btnAtacar.post(new Runnable() {
            @Override
            public void run() {
                btnAtacar.setVisibility(View.GONE);  // Ocultar botón "Atacar"
            }
        });
    }

    private void handleBackStackChanges() {
        // Verificar si el fragmento de movimientos está visible
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.movimientosContainer);

        if (currentFragment instanceof movimientosFragment) {
            btnAtacar.setVisibility(View.GONE);  // Ocultar si el fragmento de movimientos está activo
        } else if (getSupportFragmentManager().findFragmentById(R.id.combate1) instanceof Combate) {
            btnAtacar.setVisibility(View.VISIBLE);  // Mostrar solo en CombateFragment
        } else {
            btnAtacar.setVisibility(View.GONE);  // Ocultar en cualquier otro caso
        }
    }
}





