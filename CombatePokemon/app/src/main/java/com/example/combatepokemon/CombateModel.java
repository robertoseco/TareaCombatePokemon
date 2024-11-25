package com.example.combatepokemon;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class CombateModel {
    public static class CombatePokemon{
        String P1ataque;
        String P2ataque;
        String P1defensa;
        String P2defensa;
        Integer P1vida;
        Integer P2vida;

        public CombatePokemon(String p1ataque, String p2ataque, String p1defensa, String p2defensa, Integer p1vida, Integer p2vida) {
            P1ataque = p1ataque;
            P2ataque = p2ataque;
            P1defensa = p1defensa;
            P2defensa = p2defensa;
            P1vida = p1vida;
            P2vida = p2vida;
        }
    }
    public interface Callback {
        void progessChangeP1(Double P1vida);
        void progessChangeP2(Double P2vida);
        void sefini();
    }
    public void usarCascada(CombatePokemon combat, Callback callback) {

        // Asegurarse de que los valores no sean nulos
        if (combat.P1ataque != null && combat.P2ataque != null && combat.P1defensa != null && combat.P2defensa != null) {

                // Convertir los valores a enteros
                int p1Ataque = Integer.parseInt(combat.P1ataque);
                int p2Defensa = Integer.parseInt(combat.P2defensa);
                int p2Ataque = Integer.parseInt(combat.P2ataque);
                int p1Defensa = Integer.parseInt(combat.P1defensa);

                double resultadoP1 = 50 + (p1Ataque / (double) p2Defensa) * 10;
                resultadoP1 = Math.min(Math.max(resultadoP1, 50), 60);
                callback.progessChangeP1(resultadoP1);


            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Aplicar la fórmula para calcular el daño en P2
                    double resultadoP2 = 50 + (p2Ataque / (double) p1Defensa) * 10;
                    resultadoP2 = Math.min(Math.max(resultadoP2, 50), 60);

                    // Asegurándote de que callback se ejecute en el hilo principal
                    callback.progessChangeP2(resultadoP2);
                    // Verificar si el combate ha terminado
                    if (combat.P1vida<= 0 || combat.P2vida<= 0) {
                        callback.sefini();
                    }

                }
            }, 1000);


        }
    }
}
