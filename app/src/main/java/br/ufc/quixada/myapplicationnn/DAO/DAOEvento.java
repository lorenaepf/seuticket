package br.ufc.quixada.myapplicationnn.DAO;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.ufc.quixada.myapplicationnn.Entidades.Evento;

public class DAOEvento implements Serializable{
    ArrayList<Evento> eventos;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
