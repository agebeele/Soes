package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Activity_ItemList extends AppCompatActivity {

    TextView id, compania, cantidad_tubos, cantidad_personas, tiempo_garantia, telefono, pagina_web, correo_e, direccion;
    String llamar, correo, ubicacion, web;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        id = (TextView) findViewById(R.id.txt_id);
        compania = (TextView) findViewById(R.id.txt_compania);
        cantidad_tubos = (TextView) findViewById(R.id.txt_tubos);
        cantidad_personas = (TextView) findViewById(R.id.txt_personas);
        tiempo_garantia = (TextView) findViewById(R.id.txt_garantia);
        telefono = (TextView) findViewById(R.id.txt_tel);
        imageView = (ImageView) findViewById(R.id.imageView_icon0);


        Bundle recibirInfo = getIntent().getExtras();
        if (recibirInfo != null) {
            String info0 = recibirInfo.getString("id");
            String info1 = recibirInfo.getString("compania");
            String info2 = recibirInfo.getString("cantidad_tubos");
            String info3 = recibirInfo.getString("cantidad_personas");
            String info4 = recibirInfo.getString("tiempo_garantia");
            String info5 = recibirInfo.getString("telefono");
            String info6 = recibirInfo.getString("pagina_web");
            String info8 = recibirInfo.getString("correo_e");
            String info9 = recibirInfo.getString("direccion");
            String info10 = recibirInfo.getString("imagen");

            id.setText(info0);
            compania.setText(info1);
            cantidad_tubos.setText(info2);
            cantidad_personas.setText(info3);
            tiempo_garantia.setText(info4);
            telefono.setText(info5);

            imageView.setImageResource(Integer.valueOf(info10));

            llamar = info5;
            correo = info8;
            ubicacion = info9;
            web = info6;
        }
    }
    public void regresarVista(View view){
        Intent registrar = new Intent(Activity_ItemList.this, Activity_RecyclerView.class);

        startActivity(registrar);
    }
    public void llamar (View view){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(llamar));
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},0001);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0001){
            if (grantResults.length == 1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(llamar));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Sin permiso", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void Correo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Buenas tardes Ingeniero Brandon");
        intent.putExtra(Intent.EXTRA_TEXT, "Su calificacion es: E");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {correo});
        startActivity(intent);
    }
    public void registraUsuario(View view){
        Intent registrar = new Intent(Activity_ItemList.this, Activity_MyAdapter.class);

        startActivity(registrar);
    }
    public void streetview(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ubicacion));
        startActivity(intent);
    }
    public void Pagina(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(web));
        startActivity(intent);
    }
}