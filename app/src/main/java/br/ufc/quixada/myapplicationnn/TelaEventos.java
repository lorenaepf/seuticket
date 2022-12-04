package br.ufc.quixada.myapplicationnn;

import androidx.annotation.NonNull;
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
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudEvento.EditEvento;
import br.ufc.quixada.myapplicationnn.DAO.DAOEvento;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.fragments.Favoritos;

public class TelaEventos extends AppCompatActivity {

    ListView listEventos;
    ArrayAdapter adapter;
    EditText procurar;

    ArrayList<Evento> eventos = new ArrayList<>();
    ArrayList<Evento> favoritos = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String adm = FirebaseAuth.getInstance().getCurrentUser().getEmail();

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
        listEventos.setAdapter(adapter
        );

        verificaAdm();
        busca();
        recuperaDados();

            btnVolta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    volta();
                }
            });


    }

    private void verificaAdm() {
        if(adm.equals("a@gmail.com")){
            //o adm edita com click e deleta com long click
            editaEvento();
            deleteEvento();
        }else{
            //o usuario comum compra com click e favorita com long click
            compra();
            favoritar();
            btnVolta.setVisibility(View.INVISIBLE);
        }
    }

    private void favoritar() {
        listEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                favoritos.add(eventos.get(i));
                db.collection("usuarios").document(idUser).update("favoritos",favoritos).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(TelaEventos.this,"Favoritado!",Toast.LENGTH_SHORT).show();
                    }
                });

                return false;
            }
        });
    }

    private void recuperaDados() {
        db.collection("eventos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot data : task.getResult()){
                        Evento ev = new Evento();
                        ev.setCidade(data.getString("cidade"));
                        ev.setNomeEvento(data.getString("nome"));
                        ev.setData(data.getString("data"));
                        ev.setHora(data.getString("hora"));
                        ev.setValor(data.getString("valor"));
                        ev.setEstado(data.getString("estado"));
                        ev.setTipo(data.getString("tipo"));
                        ev.setId(data.getId());

                        eventos.add(ev);
                        atualizaAdapter();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 301){
            if(resultCode == TelaEventos.RESULT_OK){
                Toast.makeText(TelaEventos.this, "Evento atualizado!", Toast.LENGTH_SHORT).show();
                eventos.clear();
                recuperaDados();
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
                    db.collection("eventos").document(eventos.get(i).getId()).delete();
                    eventos.remove(i);
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

            listEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(TelaEventos.this, Compra.class);
                    intent.putExtra("eventoCompra",eventos.get(i));
                    startActivity(intent);

                }
            });

    }
    public void atualizaAdapter(){
        adapter = new ArrayAdapter(TelaEventos.this, android.R.layout.simple_list_item_1, eventos);
        listEventos.setAdapter(adapter);
    }
    public void volta(){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

}