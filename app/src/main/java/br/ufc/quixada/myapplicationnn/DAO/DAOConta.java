package br.ufc.quixada.myapplicationnn.DAO;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.Entidades.Conta;

public class DAOConta {
    ArrayList<Conta> contas;

    public DAOConta(){
        contas = new ArrayList<>();
    }
    public void addConta(Conta Conta){
        contas.add(Conta);
    }
    public ArrayList<Conta> getContas(){
        return contas;
    }
    public void setContas(ArrayList<Conta> novo){
        this.contas = novo;
    }
    public void update(int id, Conta novoConta){
        contas.set(id,novoConta);
    }
    public void deleteConta(int id){
        contas.remove(id);
    }
}
