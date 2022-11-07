package br.ufc.quixada.myapplicationnn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void telaCadastro(View v){
        Intent intent = new Intent(MainActivity.this, CadastrarST.class);
        startActivity(intent);

    }

    public void telaLogin(View v){
        Intent intent1 = new Intent(MainActivity.this, LoginST.class);
        startActivity(intent1);

    }



}