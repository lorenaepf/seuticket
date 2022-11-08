package br.ufc.quixada.myapplicationnn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.ufc.quixada.myapplicationnn.Entidades.Evento;

public class Compra extends AppCompatActivity {
    Evento evento;
    //    EditText ingressoText;
    TextView qtd,ingressoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        ingressoText = findViewById(R.id.ingresso);
        qtd = findViewById(R.id.qtdIngresso);

        Intent intent = getIntent();

        if(intent != null){
            evento = (Evento)intent.getSerializableExtra("eventoCompra");
        }
        preencheCampo();

    }

    public void preencheCampo(){
        ingressoText.setText(evento.getNomeEvento()+"\n"+evento.getdataHora()+"\nValor: "+evento.getValor());
    }
}
