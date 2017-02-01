package org.cursoandroid.applicationbmi.intentaplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by aatovarma on 31/01/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<Contacto> {

    private List<Contacto> contactos;
    private Context context;

    public CustomArrayAdapter(Context context, List<Contacto> objects) {
        super(context, 0, objects);
        contactos = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contacto contacto = contactos.get(position);
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list_item_2, parent, false);
        }
        ((TextView)view.findViewById(R.id.text1)).setText(contacto.getNombre());
        ((TextView)view.findViewById(R.id.text2)).setText(contacto.getWeb());

        return view;
    }
}
