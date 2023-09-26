package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Activity_Login extends AppCompatActivity {
    String crud;
    WebService obj = new WebService();
    Button BtnLogin;
    EditText editTextTextEmailAddress, editTextTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BtnLogin = findViewById(R.id.BtnLogin);
        editTextTextEmailAddress = findViewById(R.id.correo_ET);
        editTextTextPassword = findViewById(R.id.contrasena_ET);
    }
    public void login(View view) {
        if (editTextTextEmailAddress.getText().toString().isEmpty() || editTextTextPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Datos Faltantes", Toast.LENGTH_LONG).show();
        } else {
            crud = "log";
            new MiAsyncTask().execute(crud, editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString());
        }
    }
    class MiAsyncTask extends AsyncTask<String, String, Void> {
        @Override
        protected Void doInBackground(String... parameter) {
            String msj = null;
            switch (parameter[0]) {
                case "log":
                    msj = obj.login(parameter[1], parameter[2]);
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

                editTextTextEmailAddress.setText(json_data.getString("correo"));
                editTextTextPassword.setText(json_data.getString("contra"));
                Toast.makeText(Activity_Login.this, "Bienvenido...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity_Login.this, Activity_Inicio.class);
                startActivity(intent);
                finish();
            } catch (JSONException e) {
                editTextTextEmailAddress.setText("");
                editTextTextPassword.setText("");
                Toast.makeText(getApplicationContext(), progress[0], Toast.LENGTH_LONG).show();
            }
        }
    }
    public void registraUsuario(View view) {
        Intent registrar = new Intent(Activity_Login.this, Activity_Registro.class);
        startActivity(registrar);
    }
    private void saveLoggedInUserID(String userID) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("correo", userID);
        editor.apply();
    }
}