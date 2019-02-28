package com.example.cf17marcsoler.ra2_m8examen;

import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.JarOutputStream;

public class Controller extends AppCompatActivity implements
                        MakeReserva.MakeReservaListener
{

    Toolbar menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        menu = findViewById(R.id.menu);
        setActionBar(menu);

    }

    //Controller methods
    public void loadPlatos(){}

    //Region Toolbar Methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        FragmentManager fm = getSupportFragmentManager();

        switch (menuItem.getItemId()){
            case R.id.makeReserva:
                //Dialog to add player
                Fragment reserva = MakeReserva.newInstance();
                fm.beginTransaction().replace(R.id.container, reserva, "ReservaFragment");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void makeReserva(String fecha, int comensales, String nombre, String telefono) {
        // pasar a la base


    }
    //endregion


}
