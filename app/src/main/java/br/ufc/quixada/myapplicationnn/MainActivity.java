package br.ufc.quixada.myapplicationnn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ufc.quixada.myapplicationnn.Entidades.Usuario;
import br.ufc.quixada.myapplicationnn.fragments.Carteira;
import br.ufc.quixada.myapplicationnn.fragments.Favoritos;
import br.ufc.quixada.myapplicationnn.fragments.Home;
import br.ufc.quixada.myapplicationnn.fragments.Perfil;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    String email,senha,nome;
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            usuario = (Usuario) extras.getSerializable("user");
        }


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, Home.newInstance(email,senha,nome)).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.page_1:
                        fragment = new Home();
                        break;
                    case R.id.page_2:
                        fragment = new Carteira();
                        break;
//                    case R.id.shop_car:
//                        fragment = new ShoppingCar();
//                        break;
                    case R.id.page_4:
                        fragment = new Favoritos();
                        break;

//                    case R.id.profile:
//                        fragment = Perfil.newInstance(usuario);
//                        break;
                    default:
                        fragment = new Home();

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, fragment).commit();
                return true;
            }
        });
    }


}