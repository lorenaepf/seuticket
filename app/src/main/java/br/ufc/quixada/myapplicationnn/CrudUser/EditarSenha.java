package br.ufc.quixada.myapplicationnn.CrudUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import br.ufc.quixada.myapplicationnn.R;
import br.ufc.quixada.myapplicationnn.fragments.Perfil;


public class EditarSenha extends AppCompatActivity {
    EditText EmailText;
    Button button;
    ImageView voltar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_senha);

        voltar = findViewById(R.id.btnVoltaPerfil);
        EmailText = findViewById(R.id.changeSenha);
        button = findViewById(R.id.btnChangeSenha);

        firebaseAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailReset = EmailText.getText().toString();

                openFragment(EmailReset);

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarSenha.this, Perfil.class);
                startActivity(intent);
            }
        });

    }

    public void openFragment(String senhaM){
        Intent intent = new Intent();

        firebaseAuth.sendPasswordResetEmail(EmailText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EditarSenha.this,"Um e-mail de recupera????o foi enviado para sua conta",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditarSenha.this,"Falha no envio, tente novamente",Toast.LENGTH_SHORT).show();

                }
            }
        });

        setResult(RESULT_OK,intent);
        finish();
    }
}