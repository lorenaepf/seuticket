package br.ufc.quixada.myapplicationnn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CadastrarST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_st);
    }
    public void backStroke(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}