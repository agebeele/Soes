package com.example.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;

public class Activity_Registro extends AppCompatActivity {
    String crud;
    WebService obj = new WebService();

    private Button Btn_Registro;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextEmail = (EditText) findViewById(R.id.correo_ET);
        editTextPassword = (EditText) findViewById(R.id.contrasena_ET);
    }
    public void regresarLogin(View view){
        Intent regresoLogin = new Intent(Activity_Registro.this, Activity_Login.class);
        startActivity(regresoLogin);
    }
    public void registrarUsuario (View view){
        if (editTextEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Datos Faltantes", Toast.LENGTH_LONG).show();
        } else {
            crud = "registro";
            new MiAsyncTask().execute(crud, editTextEmail.getText().toString(), editTextPassword.getText().toString());
        }
    }

    class MiAsyncTask extends AsyncTask<String, String, Void> {
        @Override
        protected Void doInBackground(String... parameter) {
            String msj = null;
            switch (parameter[0]) {
                case "registro":
                    msj = obj.registarUsuario(parameter[1], parameter[2]);
                    publishProgress(msj);
                    break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            try {
                JSONArray jArray = new JSONArray(progress[0]);
                JSONObject json_data = null;
                for (int i = 0; i < jArray.length(); i++) {
                    json_data = jArray.getJSONObject(i);
                }
                editTextEmail.setText(json_data.getString("correo"));
                editTextPassword.setText(json_data.getString("contra"));
                Toast.makeText(Activity_Registro.this, "Usuario registrado.", Toast.LENGTH_SHORT).show();

                // Inicio de sesión exitoso, cambiar a la siguiente actividad
                Intent intent = new Intent(Activity_Registro.this, Activity_Login.class); // Cambia HomeActivity por el nombre de tu actividad de inicio
                startActivity(intent);
                finish(); // Opcional, si deseas finalizar la actividad actual

            } catch (JSONException e) {
                editTextEmail.setText("");
                editTextPassword.setText("");

                Toast.makeText(getApplicationContext(), progress[0], Toast.LENGTH_LONG).show();
            }
        }
    }
}