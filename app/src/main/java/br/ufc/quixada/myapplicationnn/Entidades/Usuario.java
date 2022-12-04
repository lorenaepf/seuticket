package br.ufc.quixada.myapplicationnn.Entidades;
import java.io.Serializable;

public class Usuario implements Serializable{

    String nome, email,uId;
    String latitude, longitude;
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

    public void addSaldo(float saldo) {
        this.conta.setSaldo(saldo);
    }
    public Conta getConta(){
        return conta;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
