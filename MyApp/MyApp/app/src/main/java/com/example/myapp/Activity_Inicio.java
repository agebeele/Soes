package com.example.myapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Activity_Inicio extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        nav = findViewById(R.id.nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Inicio())
                .commit();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.inicio:
                        selectedFragment = new Fragment_Inicio();
                        Toast.makeText(Activity_Inicio.this, "Inicio", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.explorar:
                        selectedFragment = new Fragment_Explorar();
                        Toast.makeText(Activity_Inicio.this, "Explorar", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.configuracion:
                        selectedFragment = new Fragment_Configuracion();
                        Toast.makeText(Activity_Inicio.this, "Configuracion", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.perfil:
                        selectedFragment = new Fragment_Perfil();
                        Toast.makeText(Activity_Inicio.this, "Perfil", Toast.LENGTH_SHORT).show();
                    default:
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
        });
    }
}