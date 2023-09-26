package  com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_RecyclerView extends AppCompatActivity {
    public TextView id;
    RecyclerView recyclerView;
    String msj;
    WebService obj = new WebService();
    private List<String> idList = new ArrayList<>();
    private  List<String> companiaList = new ArrayList<>();
    private  List<String> tubosList = new ArrayList<>();
    private  List<String> personasList = new ArrayList<>();
    private  List<String> garantialist = new ArrayList<>();
    private  List<String> telefonoList = new ArrayList<>();
    private  List<String> paginaList = new ArrayList<>();
    private  List<String> correoList = new ArrayList<>();
    private  List<String> direccionList = new ArrayList<>();
    private  List<String> imageList = new ArrayList<>();
    Activity_MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Activity_MyAdapter(idList, companiaList, telefonoList, imageList);
        adapter.setOnItemClickListener(new Activity_MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(),"presionaste item :"+position,Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), Activity_ItemList.class);
                i.putExtra("id", idList.get(position));
                i.putExtra("compania", companiaList.get(position));
                i.putExtra("cantidad_tubos", tubosList.get(position));
                i.putExtra("cantidad_personas", personasList.get(position));
                i.putExtra("tiempo_garantia", garantialist.get(position));
                i.putExtra("telefono", telefonoList.get(position));
                i.putExtra("pagina_web", paginaList.get(position));
                i.putExtra("correo_e", correoList.get(position));
                i.putExtra("direccion", direccionList.get(position));
                i.putExtra("imagen", imageList.get(position));
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapter);

        MiAsyncTask miAsyncTask = new MiAsyncTask();
        miAsyncTask.execute();
    }
    class MiAsyncTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... parameters) {
            msj = obj.buscarUno();
            publishProgress(msj);
            return null;
        }
        @SuppressLint({"NotifyDataSetChanged", "DiscouragedApi"})
        @Override
        protected void onProgressUpdate(String... progress) {
            try {
                JSONArray jsonArray = new JSONArray(progress[0]);
                JSONObject json_data;
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    json_data = jsonArray.getJSONObject(i);

                    idList.add(json_data.getString("id"));
                    companiaList.add(json_data.getString("compania"));
                    tubosList.add(json_data.getString("cantidad_tubos"));
                    personasList.add(json_data.getString("cantidad_personas"));
                    garantialist.add(json_data.getString("tiempo_garantia"));
                    telefonoList.add(json_data.getString("telefono"));
                    paginaList.add(json_data.getString("pagina_web"));
                    correoList.add(json_data.getString("correo_e"));
                    direccionList.add(json_data.getString("direccion"));
                }
                imageList.add(String.valueOf(R.drawable.solaris1));
                imageList.add(String.valueOf(R.drawable.freecon1));
                imageList.add(String.valueOf(R.drawable.sunnergy1));
                imageList.add(String.valueOf(R.drawable.era1));
                imageList.add(String.valueOf(R.drawable.sunshine1));
                imageList.add(String.valueOf(R.drawable.optimus1));
                imageList.add(String.valueOf(R.drawable.iconcaustic));
                imageList.add(String.valueOf(R.drawable.iconbangalore));
                imageList.add(String.valueOf(R.drawable.iconcaustic));
                imageList.add(String.valueOf(R.drawable.icongibraltar));
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), progress[0], Toast.LENGTH_LONG).show();
            }
            adapter.notifyDataSetChanged();
        }
    }
    public void regresar (View view){
        Intent back = new Intent(Activity_RecyclerView.this, Activity_Inicio.class);
        startActivity(back);
    }
}