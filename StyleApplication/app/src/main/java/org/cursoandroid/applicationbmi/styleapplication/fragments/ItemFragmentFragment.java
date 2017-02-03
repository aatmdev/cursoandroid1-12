package org.cursoandroid.applicationbmi.styleapplication.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.cursoandroid.applicationbmi.styleapplication.ConnectionDetector;
import org.cursoandroid.applicationbmi.styleapplication.R;
import org.cursoandroid.applicationbmi.styleapplication.databases.DBAdapter;
import org.cursoandroid.applicationbmi.styleapplication.models.PointDTO;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragmentFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String TAG = ItemFragmentFragment.class.getSimpleName();
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<PointDTO> dataSet;
    private ConnectionDetector connectionDetector;
    private MyItemFragmentRecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private DBAdapter dbAdapter ;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragmentFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragmentFragment newInstance(int columnCount) {
        ItemFragmentFragment fragment = new ItemFragmentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSet = new ArrayList<>();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        setHasOptionsMenu(true);

        //inicio el detector de conexión a internet
        connectionDetector = new ConnectionDetector(getActivity());
        //inicio el adaptator a la bd
        dbAdapter = new DBAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itemfragment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter = new MyItemFragmentRecyclerViewAdapter(dataSet, mListener);
            recyclerView.setAdapter(mAdapter);
            //se registra (muestra) el context menu en el reciclerview
            registerForContextMenu(recyclerView);
        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            Cursor cursor = dbAdapter.getAll();
            if(connectionDetector.isConnectingToInternet()){
                Toast.makeText(getActivity(), "Si hay conexión", Toast.LENGTH_SHORT).show();
                if(cursor.getCount() > 0){
                  updateMyList(cursor);
                }else {
                    getData();
                }
            }else{
                if(cursor.getCount() > 0){
                    updateMyList(cursor);
                }
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateMyList(Cursor cursor) {
        while(cursor.moveToNext()){
            dataSet.add(new PointDTO(cursor.getInt(1),
                                    cursor.getString(2),
                                    cursor.getString(3)));
        }
        mAdapter = new MyItemFragmentRecyclerViewAdapter(dataSet, mListener);
        recyclerView.setAdapter(mAdapter);
        recyclerView.invalidate();
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());



        String url = "http://vesta.sersoluciones.com:9080/points/get_distance/?lat=9999&lon=9999";

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "respuesta bien");
                        Gson gson = new Gson();
                        dataSet = gson.fromJson(response.toString(), new TypeToken<List<PointDTO>>(){}.getType());
                        //Guardo en la bd
                        dbAdapter.insert(dataSet);

                        mAdapter = new MyItemFragmentRecyclerViewAdapter(dataSet, mListener);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.invalidate();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        queue.add(jsObjRequest);

    }

    @Override
    public void onPause() {
        super.onPause();
        dbAdapter.close();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PointDTO item);
    }
}
