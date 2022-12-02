package br.ufc.quixada.myapplicationnn.Entidades;

import java.io.Serializable;

public class Conta implements Serializable {

    float saldo;

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
