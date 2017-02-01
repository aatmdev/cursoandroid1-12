package org.cursoandroid.applicationbmi.intentaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_OBJ = "obj";
    public static final String OBJ_KEY_SIMPLE = "value";
    public static final int WEB = 1;
    public static final int TELEFONO = 0;
    private TextView textViewWeb;
    private TextView telefonoTextView;
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            contacto = (Contacto) extras.getSerializable(KEY_OBJ);

            ((TextView) findViewById(R.id.nombreContacto)).setText(contacto.getNombre());
            textViewWeb = (TextView) findViewById(R.id.web);
            textViewWeb.setText(contacto.getWeb());
            textViewWeb.setOnClickListener(this);

            telefonoTextView = (TextView) findViewById(R.id.telefonoId);
            telefonoTextView.setText(String.valueOf(contacto.getTelefono()));
            telefonoTextView.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.telefonoId:
                intent.putExtra(OBJ_KEY_SIMPLE, contacto.getTelefono());
                setResult(TELEFONO, intent);
                break;
            case R.id.web:
                intent.putExtra(OBJ_KEY_SIMPLE, contacto.getWeb());
                setResult(WEB, intent);
                break;
        }
        finish();
    }
}
