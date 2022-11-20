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
import br.ufc.quixada.myapplicationnn.Entidades.Usuario;

public class LoginST extends AppCompatActivity {

    ArrayList<Usuario> listUsers = new ArrayList<>();

    String email,senha,nome;//cria user
    EditText edtEmail, edtSenha; //loga
    ImageView volta;
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

                for(Usuario user : listUsers){
                    if(emailLog.equals(user.getEmail()) && senhaLog.equals(user.getSenha())){

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginST.this, MainActivityHome.class);
                                    intent.putExtra("user",user);
                                    startActivity(intent);

                                    Toast.makeText(LoginST.this,"Deu bom",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginST.this,"Não deu",Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                    }else{
                        Toast.makeText(LoginST.this,"Campo email e/ou senha incorretos",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}