package br.ufc.quixada.myapplicationnn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.ufc.quixada.myapplicationnn.Entidades.Evento;

public class Compra extends AppCompatActivity {
    Evento evento;
    //    EditText ingressoText;
    TextView qtd,ingressoText,valorTxt;
    Float valorIngresso;
    ImageView volta;

    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button btn_Compra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        ingressoText = findViewById(R.id.ingresso);
        qtd = findViewById(R.id.qtdIngresso);
        btn_Compra = findViewById(R.id.btnCompra);
        valorTxt = findViewById(R.id.valorIngresso);
        volta = findViewById(R.id.btnVoltaTela);

        btn_Compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compraIngresso();
            }
        });

        Intent intent = getIntent();

        if(intent != null){
            evento = (Evento)intent.getSerializableExtra("eventoCompra");
        }
        preencheCampo();

    }


    public void preencheCampo(){
        ingressoText.setText(evento.getNomeEvento()+"\n"+ "Data: " + evento.getData());
        valorTxt.setText("R$ " + evento.getValor());
    }

    public void compraIngresso(){
        valorIngresso = Float.parseFloat(evento.getValor());
        valorIngresso *= Float.parseFloat(qtd.getText().toString());

        db.collection("usuarios").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String saldo = documentSnapshot.getString("saldo");
                if(saldo != null){
                    Float saldoAtual = Float.parseFloat(saldo);
                    if(saldoAtual > 0 && saldoAtual >= valorIngresso) {
                        saldoAtual -= valorIngresso;
                        String novoSaldo = String.valueOf(saldoAtual);
                        atualizaSaldo(novoSaldo);

                        Toast.makeText(Compra.this,"Ingresso comprado!",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Compra.this,"Saldo insuficiente",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private void atualizaSaldo(String novoSaldo) {
        db.collection("usuarios").document(id).update("saldo",novoSaldo);
    }
}
