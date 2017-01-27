package org.cursoandroid.applicationbmi.viewsapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.cursoandroid.applicationbmi.viewsapplication.models.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private List<Ciudad> ciudades;
    private List<String> ciudadesString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ciudades = new ArrayList<>();
        ciudadesString = new ArrayList<>(ciudades.size());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ciudades.add(new Ciudad("Pereira", "Risaralda", 100002, 1400));
                updateList();
            }
        });
        ciudades.add(new Ciudad("Bogotá", "Bogotá", 110111, 2600));
        ciudades.add(new Ciudad("Medellín", "Antioquia", 110112, 1500));
        ciudades.add(new Ciudad("Cáli", "Valle del cauca", 110113, 800));

        for(Ciudad c : ciudades){
            ciudadesString.add(c.getNombre());
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                ciudadesString);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ciudades.remove(position);
                updateList();
                return false;
            }
        });
    }

    private void updateList() {

        ciudadesString.clear();
        for(Ciudad c : ciudades){
            ciudadesString.add(c.getNombre());
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
