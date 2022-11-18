package br.ufc.quixada.myapplicationnn.DAO;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;

public class DAOFavoritos {
    ArrayList<Evento> favoritos;

    public DAOFavoritos(){
        favoritos = new ArrayList<>();
    }
    public void addFavoritos(Evento Evento){
        favoritos.add(Evento);
    }
    public ArrayList<Evento> getFavoritos(){
        return favoritos;
    }
    public void setFavoritos(ArrayList<Evento> novo){
        this.favoritos = novo;
    }
    public void update(int id, Evento novoEvento){
        favoritos.set(id,novoEvento);
    }
    public void deleteFavoritos(int id){
        favoritos.remove(id);
    }
}
