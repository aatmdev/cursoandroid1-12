package org.cursoandroid.applicationbmi.viewsapplication;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListaActivity extends ListActivity {

    private String[] ciudades = {"Bogotá", "Medellin", "Cali", "Pereira", "Bucaramanga", "Baranquilla",
                                    "Pasto", "Manizales", "Cucuta", "Leticia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ciudades);
        setListAdapter(arrayAdapter);
    }
}
