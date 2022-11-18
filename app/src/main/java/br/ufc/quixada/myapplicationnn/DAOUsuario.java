package br.ufc.quixada.myapplicationnn;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;

public class DAOUsuario {

    ArrayList<Usuario> usuarios;

    public DAOUsuario(){
        usuarios = new ArrayList<>();
    }
    public void addUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public ArrayList<Usuario> getUsuario(){
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> novo){
        this.usuarios = novo;
    }
    public void update(int id, Usuario novoUsuario){
        usuarios.set(id,novoUsuario);
    }
}
