package br.ufc.quixada.myapplicationnn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.quixada.myapplicationnn.Entidades.Usuario;

public class LoginST extends AppCompatActivity {

    ArrayList<Usuario> listUsers = new ArrayList<>();

    String email,senha,nome;//cria user
    EditText edtEmail, edtSenha; //loga
    Button button;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_st);

        //Recebe de cadastro
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            email = extras.getString("email");
            senha = extras.getString("senha");
            nome = extras.getString("nome");

            Usuario user = new Usuario(nome,email,senha);
            listUsers.add(user);//add no array

        }
        login = findViewById(R.id.textLogin);
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

                for(Usuario user : listUsers){
                    if(emailLog.equals(user.getEmail()) && senhaLog.equals(user.getSenha())){
                        Intent intent = new Intent(LoginST.this, MainActivityHome.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginST.this,"Campo email e/ou senha incorretos",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}