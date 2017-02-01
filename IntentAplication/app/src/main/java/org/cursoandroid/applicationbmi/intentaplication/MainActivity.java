package org.cursoandroid.applicationbmi.intentaplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contacto> contactos;
    private ListView listViewNombres;

    public MainActivity(){
        contactos = new ArrayList<>();
        contactos.add(new Contacto("Google", 318000001, "http://www.google.com"));
        contactos.add(new Contacto("yahoo", 318000002, "http://www.yanoo.com"));
        contactos.add(new Contacto("facebook", 318000003, "http://www.facebook.com"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNombres = (ListView) findViewById(R.id.listaContactosId);

        CustomArrayAdapter listAdapter = new CustomArrayAdapter(this, contactos);
        listViewNombres.setAdapter(listAdapter);
        listViewNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDetail = new Intent(MainActivity.this, DetailsActivity.class);
                Contacto contacto = contactos.get(position);
                intentDetail.putExtra(DetailsActivity.KEY_OBJ, contacto);
                startActivityForResult(intentDetail, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        switch (resultCode){
            case DetailsActivity.TELEFONO:
                int telefono = extras.getInt(DetailsActivity.OBJ_KEY_SIMPLE );
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + telefono)));
                break;
            case DetailsActivity.WEB:
                String url = extras.getString(DetailsActivity.OBJ_KEY_SIMPLE);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            default: break;
        }
    }

    public void launchNewApp(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.test.LEARN");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }
}
