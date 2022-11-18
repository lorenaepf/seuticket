package br.ufc.quixada.myapplicationnn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudEvento.EditEvento;
import br.ufc.quixada.myapplicationnn.DAO.DAOEvento;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;

public class TelaEventos extends AppCompatActivity {

    ListView listEventos;
    ArrayAdapter adapter;
    EditText procurar;

    ArrayList<Evento> eventos = new ArrayList<>();
    Usuario adm = new Usuario();
    DAOEvento daoEvento = new DAOEvento();

    Button btnVolta;

    int id;

    public TelaEventos(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_eventos);

        procurar = findViewById(R.id.search);
        btnVolta = findViewById(R.id.btnVoltaLista);

        adapter = new ArrayAdapter(TelaEventos.this, android.R.layout.simple_list_item_1, eventos);
        listEventos = findViewById(R.id.listEventos);
        listEventos.setAdapter(adapter);

        Intent intent = getIntent();
        if(intent != null){
            adm = (Usuario) intent.getSerializableExtra("adm");
            daoEvento = (DAOEvento) intent.getSerializableExtra("DAOEvento");

            if(daoEvento != null){
                adicionaEvento(daoEvento);

            }else{
                Evento teste = new Evento("festival","30/10","16Hrs","quixada","ce","10.00","festival");
                Evento teste2 = new Evento("palestra","30/10","16Hrs","quixada","ce","10.00","festival");
                Evento teste3 = new Evento("teatro","30/10","16Hrs","quixada","ce","10.00","festival");
                eventos.add(teste);
                eventos.add(teste2);
                eventos.add(teste3);
            }

            atualizaAdapter();
        }
        if(adm != null){
            editaEvento();
            deleteEvento();
        }else{
            compra();
            btnVolta.setVisibility(View.INVISIBLE);
        }

        busca();

            btnVolta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    volta();
                }
            });


    }
    public void adicionaEvento(DAOEvento daoEvento){
        Evento teste = new Evento("festival","30/10","16Hrs","quixada","ce","10.00","festival");
        Evento teste2 = new Evento("palestra","30/10","16Hrs","quixada","ce","10.00","festival");
        Evento teste3 = new Evento("teatro","30/10","16Hrs","quixada","ce","10.00","festival");

        eventos = daoEvento.getEventos();
        eventos.add(teste);
        eventos.add(teste2);
        eventos.add(teste3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 301){
            if(resultCode == TelaEventos.RESULT_OK){
                daoEvento.update(id,(Evento)data.getSerializableExtra("eventoMod"));
                atualizaAdapter();

            }

        }
    }
    public void editaEvento(){

            listEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(TelaEventos.this, EditEvento.class);
                    intent.putExtra("evento",eventos.get(i));
                    intent.putExtra("id",id);
                    id = i;
                    startActivityForResult(intent,301);
                }
            });


    }
    public void deleteEvento(){

            listEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    eventos.remove(i);
                    daoEvento.setEventos(eventos);
                    adapter.notifyDataSetChanged();

                    return false;
                }
            });

    }
    public void busca(){
        procurar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void compra(){
        if(adm == null){
            listEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(TelaEventos.this, Compra.class);
                    intent.putExtra("eventoCompra",eventos.get(i));
                    startActivity(intent);

                }
            });
        }

    }
    public void atualizaAdapter(){

        adapter = new ArrayAdapter(TelaEventos.this, android.R.layout.simple_list_item_1, eventos);
        listEventos.setAdapter(adapter);
    }
    public void volta(){
        Intent intent = new Intent();
        intent.putExtra("DAO",daoEvento.getEventos());
        setResult(RESULT_OK,intent);
        finish();
    }

}