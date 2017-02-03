package org.cursoandroid.applicationbmi.styleapplication;

import android.app.AlertDialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.cursoandroid.applicationbmi.styleapplication.fragments.ItemFragmentFragment;
import org.cursoandroid.applicationbmi.styleapplication.models.PointDTO;
import org.cursoandroid.applicationbmi.styleapplication.utilities.MyPreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemFragmentFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private MyPreferences pref;
    private int pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cerrando aplicación");

        //Inicio el preference
        pref = new MyPreferences(this);

        replaceFagment(new ItemFragmentFragment());
    }

    private void replaceFagment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                                .setTitle("Atención")
                                                .setIcon(R.drawable.ic_menu_manage)
                                                .setMessage("¿Desea salir de la aplicación?")
                                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        Toast.makeText(MainActivity.this, "Cerrando...", Toast.LENGTH_LONG).show();
                                                        progressDialog.show();
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                progressDialog.dismiss();
                                                                pref.setStateLogin(false);
                                                               finish();
                                                            }
                                                        }, 3000);
                                                    }
                                                })
                                                .setNegativeButton("No", null);
            builder.create().show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onListFragmentInteraction(PointDTO item) {
        pk = item.getId();
        Log.e(TAG, "_pk " + pk);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getGroupId()){
            case 1:
                sendNotification();
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
                break;
            case 2:
                break;

            case 3:
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void sendNotification() {
        Intent intent = new Intent(this, DetailActivity.class);
        Notification notificacion = new Notification.Builder(this)
                                .setSmallIcon(R.drawable.ic_menu_share)
                                .setWhen(System.currentTimeMillis())
                                .setContentText("Notificacion")
                                .setContentTitle("Revice por favor al notificación")
                                .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
                                .setPriority(Notification.PRIORITY_MAX).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificacion);

    }
}
