package br.ufc.quixada.myapplicationnn.CrudEvento;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.DAOEvento;
import br.ufc.quixada.myapplicationnn.Entidades.Evento;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.TelaEventos;

public class CadEvento extends AppCompatActivity {

    EditText nomeText,dataHoraText,cidadeText,estadoText,valorText,tipoText;
    Button button;
    DAOEvento daoEvento = new DAOEvento();
    ArrayList<Evento> lau = new ArrayList<>();
    TextView volta;
    Usuario adm = new Usuario();


    public CadEvento(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_evento);

        nomeText = findViewById(R.id.EdtNomeEvento);
        dataHoraText = findViewById(R.id.EdtdataHora);
        cidadeText = findViewById(R.id.Edtcidade);
        estadoText = findViewById(R.id.Edtestado);
        valorText = findViewById(R.id.EdtvalorTicket);
        tipoText = findViewById(R.id.Edttipo);
        button = findViewById(R.id.btnEdtEvento);
        volta = findViewById(R.id.voltaCad);

        Intent intent = getIntent();
        if(intent != null){
            adm = (Usuario) intent.getSerializableExtra("adm");

            if(daoEvento != null){
                daoEvento = (DAOEvento) intent.getSerializableExtra("original");
            }

        }
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
                intent.putExtra("DAO",daoEvento.getEventos());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void adicionaEvento(){
        String nomeOrg = nomeText.getText().toString();
        String dataHora = dataHoraText.getText().toString();
        String cidade = cidadeText.getText().toString();
        String estado = estadoText.getText().toString();
        String valor = valorText.getText().toString();
        String tipo = tipoText.getText().toString();

        nomeText.setText("");
        dataHoraText.setText("");
        cidadeText.setText("");
        estadoText.setText("");
        valorText.setText("");
        tipoText.setText("");

        Evento evento = new Evento(nomeOrg,dataHora,cidade,estado,valor,tipo);
        daoEvento.addEvento(evento);

        Intent intent = new Intent(CadEvento.this, TelaEventos.class);
        intent.putExtra("DAOEvento",daoEvento);
        intent.putExtra("adm",adm);

        startActivityForResult(intent,401);
    }
    public void mudaDAO(DAOEvento daoNovo){
        System.out.println("daoNovo"+daoNovo);
        daoEvento.setEventos(daoNovo.getEventos());

   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 401){
            if(resultCode == CadEvento.RESULT_OK){
                lau = (ArrayList<Evento>) data.getSerializableExtra("DAO");
                daoEvento.setEventos(lau);

            }
        }
    }
}