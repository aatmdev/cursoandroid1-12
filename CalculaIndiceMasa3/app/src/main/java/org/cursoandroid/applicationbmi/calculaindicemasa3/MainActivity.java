package org.cursoandroid.applicationbmi.calculaindicemasa3;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editTextPeso;
    private EditText editTextAltura;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPeso = (EditText) findViewById(R.id.idTextoPeso);
        editTextAltura = (EditText) findViewById(R.id.inputAltura);
        textResultado = (TextView) findViewById(R.id.textResultado);
    }

    public void calculaBMI(View view) {
        String peso = editTextPeso.getText().toString();
        String altura = editTextAltura.getText().toString();
        String mensajeResultado = null;

        if(mensajeResultado == null) {
            if (peso == null || peso.trim().isEmpty() || altura == null || altura.trim().isEmpty()) {
                mensajeResultado = getString(R.string.error_campo_vacio);
            }
        }

        if(mensajeResultado == null) {
            String regexEntero = "\\d{1,3}";
            if (!peso.matches(regexEntero) || !altura.matches(regexEntero)) {
                mensajeResultado = getString(R.string.errorNoNumerico);
            }
        }

        if(mensajeResultado == null) {
            mensajeResultado = "El resultado es : " + Integer.valueOf(altura) / Integer.valueOf(peso);
        }

        textResultado.setText(mensajeResultado);
    }
}
