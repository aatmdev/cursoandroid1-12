package org.cursoandroid.applicationbmi.fragmentapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.cursoandroid.applicationbmi.fragmentapplication.MainActivity;
import org.cursoandroid.applicationbmi.fragmentapplication.R;

/**
 * Created by aatovarma on 26/01/2017.
 */

public class MainFragment extends Fragment{

    private String TAG = MainFragment.class.getSimpleName();

    private EditText editNumber1;
    private EditText editNumber2;
    private static final String NUMERO_REGEX = "\\d+";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.main_fragment, container, false);
        editNumber1 = (EditText) view.findViewById(R.id.editNumber1);
        editNumber2 = (EditText) view.findViewById(R.id.editNumber2);
        Button button = (Button) view.findViewById(R.id.botonSumar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = editNumber1.getText().toString();
                String num2 = editNumber2.getText().toString();
                sumar(num1, num2);
            }
        });
        return view;
    }

    private void sumar(String num1, String num2) {
        if(!validateNumber(num1) || !validateNumber(num2)){
            Log.d(TAG, "numero invalido");
            editNumber1.setError("Porfavor revise el contenido campos");
            editNumber1.requestFocus();
            return;
        }

        //remplazo el fragmento en la actividad principal
        MainActivity mainActivity = ((MainActivity)getActivity());
        mainActivity.replaceFragment(MainActivity.newInstance(Integer.valueOf(num1), Integer.valueOf(num2)));

    }

    private boolean validateNumber(String num){
        if(num == null || num.trim().isEmpty()){
            return false;
        }
        return num.matches(NUMERO_REGEX);
    }
}
