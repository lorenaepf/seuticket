package br.ufc.quixada.myapplicationnn.Entidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    String nome, email;
    public Usuario(){

    }

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
//        this.senha = senha;

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

//    public String getSenha() {
//        return senha;
//    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public void setSenha(String senha) {
//        this.senha = senha;
//    }
}
