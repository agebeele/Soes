package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_Perfil extends Fragment {

    TextView clave, nombre, nombre1, paterno, materno, correo, contra;
    WebService obj = new WebService();
    private String loggedInUserEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView txtEditar = v.findViewById(R.id.txtEditarPerfil);
        txtEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_EditarPerfil.class);
                startActivity(intent);
            }
        });

        clave = v.findViewById(R.id.txt_clave);
        nombre = v.findViewById(R.id.txt_nombre);
        nombre1 = v.findViewById(R.id.txt_nombre1);
        paterno = v.findViewById(R.id.txt_paterno);
        materno = v.findViewById(R.id.txt_materno);
        correo = v.findViewById(R.id.txt_correo1);
        contra = v.findViewById(R.id.txt_contra);

        String userEmail = getLoggedInUserEmail();
        MiAsyncTask miAsyncTask = new MiAsyncTask();
        miAsyncTask.execute("buscarUser", userEmail);

        return v;
    }

    private class MiAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... parameter) {
            String msj = null;
            switch (parameter[0]) {
                case "buscarUser":
                    msj = obj.datosUsuarioCorreo(parameter[1]);
                    publishProgress(msj);
                    break;
                default:
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

                clave.setText(json_data.getString("id"));
                nombre.setText(json_data.getString("nombre"));
                nombre1.setText(json_data.getString("nombre"));
                paterno.setText(json_data.getString("apellido_paterno"));
                materno.setText(json_data.getString("apellido_materno"));
                correo.setText(json_data.getString("correo"));
                contra.setText(json_data.getString("contra"));

            } catch (JSONException e) {
                clave.setText("");
                nombre.setText("");
                nombre1.setText("");
                paterno.setText("");
                materno.setText("");
                correo.setText("");
                contra.setText("");
                Toast.makeText(getActivity(), "Error al obtener los datos del usuario", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getLoggedInUserEmail() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString("correo", "");
    }
}