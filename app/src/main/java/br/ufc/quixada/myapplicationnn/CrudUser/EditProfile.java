package br.ufc.quixada.myapplicationnn.CrudUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufc.quixada.myapplicationnn.R;

public class EditProfile extends AppCompatActivity{
    EditText nomeText, emailText;
    String nome, email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nomeText = findViewById(R.id.changeName);
        button = findViewById(R.id.btnEdt);

        Intent intent = getIntent();
        //Recebe da intent para preencher os campos

        nome = intent.getStringExtra("nome");

        onDataReceived();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeModificado = nomeText.getText().toString();

                openFragment(nomeModificado);

            }
        });
    }

    public void onDataReceived() {

        nomeText.setText(nome);

    }
    public void openFragment(String nomeM){
        Intent intent = new Intent();
        intent.putExtra("nomeModificado",nomeM);

        setResult(RESULT_OK,intent);
        finish();

    }
}