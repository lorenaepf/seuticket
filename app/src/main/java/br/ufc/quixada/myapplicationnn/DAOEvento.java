package br.ufc.quixada.myapplicationnn;


import java.io.Serializable;
import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.Entidades.Evento;

public class DAOEvento implements Serializable {
    ArrayList<Evento> eventos;

    public DAOEvento(){
        eventos = new ArrayList<>();
    }
    public void addEvento(Evento evento){
        eventos.add(evento);
    }
    public ArrayList<Evento> getEventos(){
        return eventos;
    }
    public void setEventos(ArrayList<Evento> novo){
        this.eventos = novo;
    }
    public void update(int id, Evento novoEvento){
        eventos.set(id,novoEvento);
    }
    public void deleteEvento(int id){
        eventos.remove(id);
    }
}
