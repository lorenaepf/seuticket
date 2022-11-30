package br.ufc.quixada.myapplicationnn.Entidades;

import java.io.Serializable;

import br.ufc.quixada.myapplicationnn.Interfaces.TransfereSaldo;

public class Usuario implements Serializable, TransfereSaldo {

    String nome, email,uId;
    Conta conta = new Conta();

    public Usuario(){

    }

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Override
    public void addSaldo(float saldo) {
        this.conta.setSaldo(saldo);
    }
    public Conta getConta(){
        return conta;
    }
}
