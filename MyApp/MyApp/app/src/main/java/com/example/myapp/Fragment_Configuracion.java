package com.example.myapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
public class Fragment_Configuracion extends Fragment {

    public Button BtnCerrarSesion, btnCambiarContraseña = null;
    public Button btnAcercaNosotros, btnAdminUsers = null;
    public Button btnAyudaTelefonica = null;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_configuracion, container, false);

        BtnCerrarSesion = (Button) v.findViewById(R.id.BtnCerrarSesion);
        BtnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarDialogoConfirmacion();
            }
        });

        btnCambiarContraseña = (Button) v.findViewById(R.id.btnCambio);
        btnCambiarContraseña.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambioContraseña();
            }
        });
        btnAcercaNosotros =  (Button) v.findViewById(R.id.btnNosotros);
        btnAcercaNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioNosotros();
            }
        });
        btnAyudaTelefonica =  (Button) v.findViewById(R.id.btnAyuda);
        btnAyudaTelefonica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLlamada();
            }
        });
        btnAdminUsers =  (Button) v.findViewById(R.id.btnAdmin);
        btnAdminUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioUsuarios();
            }
        });
        return v;
    }
    private void cambioUsuarios() {
        Intent cambio = new Intent (getActivity(), Activity_RecyclerView.class);
        startActivity(cambio);
    }

    private void realizarLlamada() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 313 706 5253"));
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},0001);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0001){
            if (grantResults.length == 1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 313 706 5253"));
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Sin permiso", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void cambioNosotros() {
        Intent cambio = new Intent (getActivity(), Activity_Nosotros.class);
        startActivity(cambio);
    }
    private void cambioContraseña() {
        Intent ingresar = new Intent (getActivity(), Activity_EditarPerfil.class);
        startActivity(ingresar);
    }

    private void mostrarDialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Seguro que quieres cerrar la sesión?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = new Intent(getActivity(), Activity_Login.class);
                startActivity(intent);

                Toast.makeText(getActivity(), "Sesion cerrada", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // El usuario hizo clic en "No", no hacer nada
            }
        });
        builder.show();
    }
}
