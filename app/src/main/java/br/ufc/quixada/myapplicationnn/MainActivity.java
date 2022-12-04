package br.ufc.quixada.myapplicationnn;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.ufc.quixada.myapplicationnn.CrudUser.Cadastro;
import br.ufc.quixada.myapplicationnn.R;


public class MainActivity extends AppCompatActivity {

    Button cad,login;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioLogado();

        cad = findViewById(R.id.botaoCadastro);
        login = findViewById(R.id.botaoEntrar);

        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginST.class);
                startActivity(intent);
            }
        });

    }

    private void usuarioLogado() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if(mUser != null){
            startActivity(new Intent(MainActivity.this, MainActivityHome.class));
        }
    }


}