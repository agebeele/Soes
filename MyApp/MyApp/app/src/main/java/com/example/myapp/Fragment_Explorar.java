package com.example.myapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_Explorar extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button BtnFecha, BtnHora;
    EditText editTextFecha, editTextHora;
    Spinner spinner;
    private int dia, mes, año, hora, minutos;

    private static final String TAG= "Grafica";
    private LineChart mChart, lChart;
    ArrayList<Entry> yValues;
    ArrayList<Entry> xValues;
    LineData lineData;
    private String[] tablaUrls = {
            "http://192.168.0.20/Conexion_SOES/tubo_uno.php",
            "http://192.168.0.20/Conexion_SOES/tubo_dos.php",
            "http://192.168.0.20/Conexion_SOES/tubo_tres.php",
            "http://192.168.0.20/Conexion_SOES/tubo_cuatro.php"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explorar, container, false);

        mChart = (LineChart) v.findViewById(R.id.linechart1);
        lChart = (LineChart) v.findViewById(R.id.linechart1);

        //Calendario y Reloj
        BtnFecha = (Button) v.findViewById(R.id.BtnFecha);
        BtnHora = (Button) v.findViewById(R.id.BtnHora);
        editTextHora = (EditText) v.findViewById(R.id.editTextFecha);
        editTextFecha = (EditText) v.findViewById(R.id.editTextHora);

        BtnFecha.setOnClickListener(this);
        BtnHora.setOnClickListener(this);
        editTextFecha.setOnClickListener(this);
        editTextHora.setOnClickListener(this);

        //Lista desplegable
        Spinner spinner = v.findViewById(R.id.spinner_material);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Datos Grafica
        yValues = new ArrayList<>();
        yValues.add(new Entry(10, (float) 28f));
        yValues.add(new Entry(11, (float) 31f));
        yValues.add(new Entry(12, (float) 32f));
        yValues.add(new Entry(13, (float) 26f));
        yValues.add(new Entry(14, (float) 27f));
        yValues.add(new Entry(15, (float) 33f));
        yValues.add(new Entry(16, (float) 27f));

        xValues = new ArrayList<>();
        xValues.add(new Entry(10, (float) 25f));
        xValues.add(new Entry(11, (float) 33f));
        xValues.add(new Entry(12, (float) 27f));
        xValues.add(new Entry(13, (float) 30f));
        xValues.add(new Entry(14, (float) 27f));
        xValues.add(new Entry(15, (float) 31f));
        xValues.add(new Entry(16, (float) 29f));

        mChart.setData(lineData);
        lChart.setData(lineData);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v==BtnFecha){
            final Calendar c = Calendar.getInstance();

            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            año = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    editTextFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,dia, mes, año);
            datePickerDialog.show();
        }
        if (v==BtnHora){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    editTextHora.setText(hourOfDay+":"+minute);
                }
            },hora, minutos, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedUrl = tablaUrls[i];

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, selectedUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Entry> entries = new ArrayList<>();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                float xValue = (float) obj.getDouble("x_value");
                                float yValue = (float) obj.getDouble("y_value");
                                String fechaRegistro = obj.getString("fecha_registro");
                                entries.add(new Entry(xValue, yValue));

                                LineDataSet dataSet = new LineDataSet(entries, "Datos de la gráfica");
                                dataSet.setColor(Color.GREEN);
                                dataSet.setLabel("Temperatura del día: " + fechaRegistro);

                                LineData lineData = new LineData(dataSet);
                                mChart.setData(lineData);
                                mChart.invalidate();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(jsonArrayRequest);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
