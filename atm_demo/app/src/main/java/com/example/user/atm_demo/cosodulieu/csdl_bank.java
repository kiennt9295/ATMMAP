package com.example.user.atm_demo.cosodulieu;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 11/11/2016.
 */
public class csdl_bank {
    private Context context;
    private ArrayList<String> ten_NH=new ArrayList<String>();
    private ArrayList<String> NganHang_lienket=new ArrayList<String>();
    private Cursor c;

    public csdl_bank(Cursor c, Context context, ArrayList<String> nganHang_lienket, ArrayList<String> ten_NH) {
        this.c = c;
        this.context = context;
        NganHang_lienket = nganHang_lienket;
        this.ten_NH = ten_NH;
    }

    public void csdl_tenNH(){
        int j=0;
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
        c=myDbHelper.query1("BANK", null, null, null, null, null, null);
        if (c.moveToFirst()){
            do {
                ten_NH.add(j,c.getString(1));
                NganHang_lienket.add(j,c.getString(2));
                j++;
            } while (c.moveToNext());
        }
    }

}
