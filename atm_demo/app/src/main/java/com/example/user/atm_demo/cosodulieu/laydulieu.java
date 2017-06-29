package com.example.user.atm_demo.cosodulieu;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import com.example.user.atm_demo.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 11/10/2016.
 */
public class laydulieu {
    private Context context;
    private ArrayList<String> atm_ten=new ArrayList<String>();
    private ArrayList<Double> atm_lat=new ArrayList<Double>();
    private ArrayList<Double> atm_lng=new ArrayList<Double>();
    private Cursor c;
    private String tenbang;

    public laydulieu(ArrayList<Double> atm_lat, ArrayList<Double> atm_lng, ArrayList<String> atm_ten, Cursor c, Context context,
                     String tenbang) {
        this.atm_lat = atm_lat;
        this.atm_lng = atm_lng;
        this.atm_ten = atm_ten;
        this.c = c;
        this.context = context;
        this.tenbang = tenbang;
    }

    public void bang_ngan_hang(){
        int i=0;
        DatabaseSqlite myDbHelper = new DatabaseSqlite(context);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c = myDbHelper.query(tenbang, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                atm_ten.add(i,c.getString(1));
                atm_lat.add(i,c.getDouble(2));
                atm_lng.add(i,c.getDouble(3));
                i++;

            } while (c.moveToNext());
        }
    }
}
