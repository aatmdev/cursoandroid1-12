package org.cursoandroid.applicationbmi.styleapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.cursoandroid.applicationbmi.styleapplication.utilities.MyPreferences;

public class LoginActivoty extends AppCompatActivity {

    private EditText editTextUsername;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activoty);

        editTextUsername = (EditText) findViewById(R.id.usuario);

        myPreferences = new MyPreferences(this);

        if(myPreferences.isLogin()){
            startMainActivity();
        }
    }

    public void login(View v){
        String username = editTextUsername.getText().toString();
        if(username.trim().isEmpty()){
            editTextUsername.setError("Por favor ingresar el usuario");
            return;
        }
        myPreferences.setUserName(username);
        myPreferences.setStateLogin(true);
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
