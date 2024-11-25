package com.example.combatepokemon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CombateModelView extends ViewModel {

    Executor executor;
    CombateModel combate;

    public CombateModelView() {
        this.executor = Executors.newSingleThreadExecutor();
        combate = new CombateModel();
    }

    // Declaramos MutableLiveData para cada propiedad
    private final MutableLiveData<String> p1Nombre = new MutableLiveData<>();
    private final MutableLiveData<String> p1PS = new MutableLiveData<>();
    private final MutableLiveData<String> p1Ataque = new MutableLiveData<>();
    private final MutableLiveData<String> p1AtaqueEspecial = new MutableLiveData<>();
    private final MutableLiveData<String> p1Defensa = new MutableLiveData<>();
    private final MutableLiveData<String> p1DefensaEspecial = new MutableLiveData<>();
    public MutableLiveData<Integer> vidaP1 = new MutableLiveData<>();

    public MutableLiveData<Integer> vidaP2 = new MutableLiveData<>();
    private final MutableLiveData<String> p2Nombre = new MutableLiveData<>();
    private final MutableLiveData<String> p2PS = new MutableLiveData<>();
    private final MutableLiveData<String> p2Ataque = new MutableLiveData<>();
    private final MutableLiveData<String> p2AtaqueEspecial = new MutableLiveData<>();
    private final MutableLiveData<String> p2Defensa = new MutableLiveData<>();
    private final MutableLiveData<String> p2DefensaEspecial = new MutableLiveData<>();

    private final MutableLiveData<Boolean> acabado = new MutableLiveData<>();

    private MutableLiveData<Integer> progreso1 = new MutableLiveData<>();
    private MutableLiveData<Integer> progreso2 = new MutableLiveData<>();

    // Getters para LiveData (para observar en los fragmentos)
    public LiveData<String> getP1Nombre() {
        return p1Nombre;
    }

    public LiveData<String> getP1PS() {
        return p1PS;
    }

    public LiveData<String> getP1Ataque() {
        return p1Ataque;
    }

    public LiveData<String> getP1AtaqueEspecial() {
        return p1AtaqueEspecial;
    }

    public LiveData<String> getP1Defensa() {
        return p1Defensa;
    }

    public LiveData<String> getP1DefensaEspecial() {
        return p1DefensaEspecial;
    }

    public LiveData<String> getP2Nombre() {
        return p2Nombre;
    }

    public LiveData<String> getP2PS() {
        return p2PS;
    }

    public LiveData<String> getP2Ataque() {
        return p2Ataque;
    }

    public LiveData<String> getP2AtaqueEspecial() {
        return p2AtaqueEspecial;
    }

    public LiveData<String> getP2Defensa() {
        return p2Defensa;
    }

    public LiveData<String> getP2DefensaEspecial() {
        return p2DefensaEspecial;
    }

    // Setters para MutableLiveData
    public void setP1Nombre(String nombre) {
        p1Nombre.setValue(nombre);
    }

    public void setP1PS(String ps) {
        p1PS.setValue(ps);
    }

    public void setP1Ataque(String ataque) {
        p1Ataque.setValue(ataque);
    }

    public void setP1AtaqueEspecial(String ataqueEspecial) {
        p1AtaqueEspecial.setValue(ataqueEspecial);
    }

    public void setP1Defensa(String defensa) {
        p1Defensa.setValue(defensa);
    }

    public void setP1DefensaEspecial(String defensaEspecial) {
        p1DefensaEspecial.setValue(defensaEspecial);
    }

    public void setP2Nombre(String nombre) {
        p2Nombre.setValue(nombre);
    }

    public void setP2PS(String ps) {
        p2PS.setValue(ps);
    }

    public void setP2Ataque(String ataque) {
        p2Ataque.setValue(ataque);
    }

    public void setP2AtaqueEspecial(String ataqueEspecial) {
        p2AtaqueEspecial.setValue(ataqueEspecial);
    }

    public void setP2Defensa(String defensa) {
        p2Defensa.setValue(defensa);
    }

    public void setP2DefensaEspecial(String defensaEspecial) {
        p2DefensaEspecial.setValue(defensaEspecial);
    }

    public LiveData<Integer> getVidaP1() {
        return vidaP1;
    }

    public LiveData<Integer> getVidaP2() {
        return vidaP2;
    }

    public MutableLiveData<Boolean> getAcabado() {
        return acabado;
    }

    public MutableLiveData<Integer> getProgreso1() {
        return progreso1;
    }

    public void setProgreso1(Integer Pprogreso1) {
        this.progreso1.postValue(Pprogreso1);
    }

    public MutableLiveData<Integer> getProgreso2() {
        return progreso2;
    }

    public void setProgreso2(Integer Pprogreso2) {
        this.progreso2.postValue(Pprogreso2);
    }

    public void usarCascada() {
        final CombateModel.CombatePokemon combatePokemon = new CombateModel.CombatePokemon(
                p1Ataque.getValue(), p2Ataque.getValue(), p1Defensa.getValue(), p2Defensa.getValue(), vidaP1.getValue(), vidaP2.getValue()
        );
        executor.execute(new Runnable() {
            @Override
            public void run() {
                combate.usarCascada( combatePokemon, new  CombateModel.Callback(){


                    @Override
                    public void progessChangeP1(Double P1vida) {
                        vidaP1.postValue(P1vida.intValue());
                    }

                    @Override
                    public void progessChangeP2(Double P2vida) {
                        vidaP2.postValue(P2vida.intValue());
                    }

                    @Override
                    public void sefini() {
                        acabado.postValue(true);
                    }
                });
            }
        });
    }
}



