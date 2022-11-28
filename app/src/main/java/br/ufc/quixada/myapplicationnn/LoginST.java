package br.ufc.quixada.myapplicationnn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.CrudUser.Cadastro;
import br.ufc.quixada.myapplicationnn.CrudUser.EditarSenha;
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;

public class LoginST extends AppCompatActivity {

    EditText edtEmail, edtSenha; //loga
    ImageView volta;
    TextView login,esqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_st);

        login = findViewById(R.id.textLogin);
        esqueceuSenha = findViewById(R.id.escSenha);

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginST.this, EditarSenha.class);
                startActivity(intent);
            }
        });

        volta = findViewById(R.id.retorna);

        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginST.this, Cadastro.class);
                startActivity(intent);
            }
        });
        Logar();

    }
    public void Logar(){
        edtEmail = findViewById(R.id.logEmail);
        edtSenha = findViewById(R.id.logSenha);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailLog = edtEmail.getText().toString();
                String senhaLog = edtSenha.getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailLog,senhaLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(LoginST.this, MainActivityHome.class);
                            startActivity(intent);

                                    Toast.makeText(LoginST.this,"Deu bom",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginST.this,"Não deu",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }

}