package com.example.myapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class Fragment_Inicio extends Fragment {
    @Nullable
    private TextView textViewTemperature;
    private Handler handler;
    private Runnable temperatureUpdateRunnable;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);

        textViewTemperature = v.findViewById(R.id.textViewTemperature);
        handler = new Handler(Looper.getMainLooper());

        temperatureUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                generateRandomTemperature();
                handler.postDelayed(this, 5000); // Actualizar cada 5 segundos
            }
        };

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        startTemperatureUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTemperatureUpdates();
    }

    private void startTemperatureUpdates() {
        handler.postDelayed(temperatureUpdateRunnable, 0);
    }

    private void stopTemperatureUpdates() {
        handler.removeCallbacks(temperatureUpdateRunnable);
    }

    private void generateRandomTemperature() {
        Random random = new Random();
        int temperature = random.nextInt(41) - 10; // Genera un número aleatorio entre -10 y 30 (grados centígrados)
        String temperatureString = String.valueOf(temperature) + " °C";
        textViewTemperature.setText(temperatureString);
    }
}