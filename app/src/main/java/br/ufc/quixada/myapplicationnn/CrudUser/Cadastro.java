package br.ufc.quixada.myapplicationnn.CrudUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.quixada.myapplicationnn.LoginST;
import br.ufc.quixada.myapplicationnn.R;

public class Cadastro extends AppCompatActivity {
    EditText email, senha,nome;
    Button button;
    TextView Cad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponente();
    }

    public void iniciaComponente(){
        email = findViewById(R.id.logEmail);
        senha = findViewById(R.id.logSenha);
        nome = findViewById(R.id.edtCadNome);
        Cad = findViewById(R.id.textCad);

//        button = findViewById(R.id.btnCad);
        Cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailTxt = email.getText().toString();
                String senhaTxt = senha.getText().toString();
                String nomeText = nome.getText().toString();

                if(emailTxt.isEmpty() || senhaTxt.isEmpty() || nomeText.isEmpty()){
                    Toast.makeText(Cadastro.this, "Preencha todos os campos!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Cadastro.this, LoginST.class);

                    intent.putExtra("email",emailTxt);
                    intent.putExtra("senha", senhaTxt);
                    intent.putExtra("nome",nomeText);

                    startActivity(intent);
                }

            }
        });
    }
}