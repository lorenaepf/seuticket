package br.ufc.quixada.myapplicationnn.CrudUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufc.quixada.myapplicationnn.R;

public class EditProfile extends AppCompatActivity{
    EditText nomeText, emailText;
    TextView nometxt, emailtxt;
    String nome, email;
    Button button;
    ImageView volta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nomeText = findViewById(R.id.changeName);
        nometxt = findViewById(R.id.nomePerfil);
        emailtxt = findViewById(R.id.emailPerfil);
        button = findViewById(R.id.btnEdt);
        volta = findViewById(R.id.btnVoltaPerfil);

        voltaPerfil();

        Intent intent = getIntent();
        //Recebe da intent para preencher os campos

        nome = intent.getStringExtra("nome");
        email = intent.getStringExtra("email");

        onDataReceived();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeModificado = nomeText.getText().toString();

                openFragment(nomeModificado);

            }
        });
    }

    private void voltaPerfil() {
        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onDataReceived() {
        nomeText.setText(nome);
        nometxt.setText(nome);
        emailtxt.setText(email);

    }
    public void openFragment(String nomeM){
        Intent intent = new Intent();
        intent.putExtra("nomeModificado",nomeM);

        setResult(RESULT_OK,intent);
        finish();

    }
}