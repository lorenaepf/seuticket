package br.ufc.quixada.myapplicationnn.CrudEvento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.R;

public class EditEvento extends AppCompatActivity {
    Evento evento = new Evento();
    EditText nomeText,dataText,horaText,cidadeText,estadoText,valorText,tipoText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_evento);

        Intent intent = getIntent();

        evento = (Evento)intent.getSerializableExtra("evento");

        nomeText = findViewById(R.id.EdtNomeEvento);
        dataText = findViewById(R.id.EdtData);
        horaText = findViewById(R.id.EdtHora);
        cidadeText = findViewById(R.id.Edtcidade);
        estadoText = findViewById(R.id.Edtestado);
        valorText = findViewById(R.id.EdtvalorTicket);
        tipoText = findViewById(R.id.Edttipo);
        button = findViewById(R.id.btnEdtEvento);

        preencheCampos();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeModificado = nomeText.getText().toString();
                String dataModificada = dataText.getText().toString();
                String horaModificada = horaText.getText().toString();
                String cidadeModificada = cidadeText.getText().toString();
                String estadoModificado = estadoText.getText().toString();
                String valorModificado = valorText.getText().toString();
                String tipoModificado = tipoText.getText().toString();

                mudaEvento(nomeModificado,dataModificada,horaModificada,
                        cidadeModificada,estadoModificado,
                        valorModificado,tipoModificado);

            }
        });
    }
    public void preencheCampos(){
        nomeText.setText(evento.getNomeEvento());
        dataText.setText(evento.getData());
        horaText.setText(evento.getHora());
        cidadeText.setText(evento.getCidade());
        estadoText.setText(evento.getEstado());
        valorText.setText(evento.getValor());
        tipoText.setText(evento.getTipo());
    }
    public void mudaEvento(String nome,String data, String hora,String cidade, String estado, String valor,String tipo){
        Intent intent = new Intent();
        Evento eventoMod = new Evento(nome,data,hora,cidade,estado,valor,tipo);
        intent.putExtra("eventoMod",eventoMod);

        setResult(RESULT_OK,intent);
        finish();
    }

}