package br.ufc.quixada.myapplicationnn.Entidades;

import java.io.Serializable;

public class Evento implements Serializable {
    String nomeEvento,dataHora,cidade,estado,valor,tipo;


    public Evento(){

    }

    public Evento(String nomeEvento, String dataHora, String cidade, String estado, String valor,String tipo){
        this.nomeEvento = nomeEvento;
        this.dataHora = dataHora;
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

    public String getdataHora() {
        return dataHora;
    }

    public void setdataHora(String dataHora) {
        this.dataHora = dataHora;
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

    public String toString(){
        String obj = nomeEvento+"\n"+dataHora+"\nCidade: "+cidade+"-"+estado+"  Valor: "+valor;
        return obj;
    }
}
