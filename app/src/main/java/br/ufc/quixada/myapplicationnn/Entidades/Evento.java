package br.ufc.quixada.myapplicationnn.Entidades;

import java.io.Serializable;

public class Evento implements Serializable {
    String nomeEvento,data,hora,cidade,estado,valor,tipo;


    public Evento(){

    }

    public Evento(String nomeEvento, String data,String hora, String cidade, String estado, String valor,String tipo){
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.hora = hora;
        this.cidade = cidade;
        this.estado = estado;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String toString(){
        String obj = nomeEvento+"\n"+data+" "+hora+"\nCidade: "+cidade+"-"+estado+"  Valor: "+valor;
        return obj;
    }
}
