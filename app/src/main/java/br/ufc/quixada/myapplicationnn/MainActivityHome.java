package br.ufc.quixada.myapplicationnn;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import br.ufc.quixada.myapplicationnn.fragments.Carteira;
import br.ufc.quixada.myapplicationnn.fragments.Favoritos;
import br.ufc.quixada.myapplicationnn.fragments.Home;
import br.ufc.quixada.myapplicationnn.fragments.Perfil;

public class MainActivityHome extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, new Home()).commit();
        bottomNavigationView.setSelectedItemId(R.id.page_1);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.page_1:
                        fragment = new Home();
                        break;
                    case R.id.page_2:
                        fragment = new Carteira();
                        break;
                    case R.id.page_4:
                        fragment = new Favoritos();
                        break;
                    case R.id.page_5:
                        fragment = new Perfil();
                        break;
                    default: fragment = new Home();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, fragment).commit();
                return true;
            }

        });


    }
}