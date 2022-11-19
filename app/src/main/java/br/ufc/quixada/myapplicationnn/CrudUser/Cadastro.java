package br.ufc.quixada.myapplicationnn.CrudUser;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.ufc.quixada.myapplicationnn.LoginST;
import br.ufc.quixada.myapplicationnn.R;

public class Cadastro extends AppCompatActivity {
    EditText email, senha,nome;
    Button button;
    TextView Cad;
    String nomeText,emailTxt,senhaTxt;

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


                emailTxt = email.getText().toString();
                senhaTxt = senha.getText().toString();
                nomeText = nome.getText().toString();

                teste(emailTxt,senhaTxt);


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

    public void teste(String email, String senha){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Cadastro.this, "Deu bom",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Cadastro.this, "Não deu "+emailTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}