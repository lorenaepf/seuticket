package br.ufc.quixada.myapplicationnn.CrudEvento;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;

public class CadEvento extends AppCompatActivity implements Serializable {

    EditText nomeText,dataText,horaText,cidadeText,estadoText,valorText,tipoText;
    Button button;
    ArrayList<Evento> lau = new ArrayList<>();
    ImageView volta;
    String id;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CadEvento(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_evento);

        nomeText = findViewById(R.id.EdtNomeEvento);
        dataText = findViewById(R.id.CadData);
        horaText = findViewById(R.id.CadHora);
        cidadeText = findViewById(R.id.Edtcidade);
        estadoText = findViewById(R.id.Edtestado);
        valorText = findViewById(R.id.EdtvalorTicket);
        tipoText = findViewById(R.id.Edttipo);
        button = findViewById(R.id.btnCadEvento);
        volta = findViewById(R.id.btnVoltaPerfil);

        Intent intent = getIntent();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionaEvento();
            }
        });

        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void adicionaEvento(){
        String nomeOrg = nomeText.getText().toString();
        String data = dataText.getText().toString();
        String hora = horaText.getText().toString();
        String cidade = cidadeText.getText().toString();
        String estado = estadoText.getText().toString();
        String valor = valorText.getText().toString();
        String tipo = tipoText.getText().toString();
        id = UUID.randomUUID().toString();

        nomeText.setText("");
        dataText.setText("");
        horaText.setText("");
        cidadeText.setText("");
        estadoText.setText("");
        valorText.setText("");
        tipoText.setText("");

        Evento evento = new Evento(nomeOrg,data,hora,cidade,estado,valor,tipo);
        criaEvento(evento);

        Intent intent = new Intent(CadEvento.this, TelaEventos.class);

        startActivityForResult(intent,401);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 401){
            if(resultCode == CadEvento.RESULT_OK){
                lau = (ArrayList<Evento>) data.getSerializableExtra("DAO");

            }
        }
    }
    public void criaEvento(Evento ev) {
        Map<String, Object> eventosDb = new HashMap<>();

        eventosDb.put("nome",ev.getNomeEvento());
        eventosDb.put("data",ev.getData());
        eventosDb.put("hora",ev.getHora());
        eventosDb.put("cidade",ev.getCidade());
        eventosDb.put("estado",ev.getEstado());
        eventosDb.put("tipo",ev.getTipo());
        eventosDb.put("valor",ev.getValor());

        db.collection("eventos").document(id).set(eventosDb).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                System.out.println("Amongus cadastrado com sucesso");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Cadastro com falha");
            }
        });
    }
}