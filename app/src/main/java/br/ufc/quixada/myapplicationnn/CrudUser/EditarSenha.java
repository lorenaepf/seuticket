package br.ufc.quixada.myapplicationnn.CrudUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufc.quixada.myapplicationnn.R;


public class EditarSenha extends AppCompatActivity {
    EditText senhaText;
    String senha;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_senha);

        senhaText = findViewById(R.id.changeSenha);
        button = findViewById(R.id.btnChangeSenha);

        Intent intent = getIntent();
        //Recebe da intent para preencher os campos

        senha = intent.getStringExtra("senha");

        onDataReceived();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senhaModificada = senhaText.getText().toString();

                openFragment(senhaModificada);

            }
        });

    }
    public void onDataReceived(){
        senhaText.setText(senha);
    }
    public void openFragment(String senhaM){
        Intent intent = new Intent();
        intent.putExtra("senhaModificada",senhaM);

        Toast.makeText(EditarSenha.this,"Senha Alterada Com Sucesso!", Toast.LENGTH_LONG).show();

        setResult(RESULT_OK,intent);
        finish();
    }
}