package br.ufc.quixada.myapplicationnn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_st);
    }

    public void backStroke(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void telaHome(View view){
        Intent intent = new Intent(LoginST.this, MainActivityHome.class);
        startActivity(intent);

    }

}