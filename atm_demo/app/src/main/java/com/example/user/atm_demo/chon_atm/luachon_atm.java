package com.example.user.atm_demo.chon_atm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.atm_demo.R;
import com.example.user.atm_demo.Xuly_Dialog.Dialog_timkiem;
import com.example.user.atm_demo.Xuly_Dialog.dialog_gioithieu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class luachon_atm extends Activity {

    private EditText edtimkiem;
    private ListView lvnganhang;
    private ArrayAdapter<String> adapter;
    private ImageView imgxoa;
    private ImageView imggioithieu;
    private AlertDialog alertDialog;
    private dialog_gioithieu dialogGioithieu;
    private ArrayList<String> ten_Ngan_Hang=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luachon_atm);

        Intent i=getIntent();
        ten_Ngan_Hang=i.getStringArrayListExtra("ten_NH");

        lvnganhang = (ListView) findViewById(R.id.lvnganhang);
        edtimkiem = (EditText) findViewById(R.id.edtimkiem);
        imgxoa=(ImageView)findViewById(R.id.imgxoa);
        imggioithieu=(ImageView)findViewById(R.id.imgGioithieu);

        dialogGioithieu=new dialog_gioithieu(luachon_atm.this,alertDialog);

        imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtimkiem.setText("");
                imgxoa.setVisibility(View.INVISIBLE);
            }
        });

        adapter = new ArrayAdapter<String>(this, R.layout.item_listview, R.id.tvtennganhang, ten_Ngan_Hang);
        lvnganhang.setAdapter(adapter);
        lvnganhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtimkiem.setText(ten_Ngan_Hang.get(position));
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ten_ngan_hang", edtimkiem.getText().toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        imggioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGioithieu.xuly_dialog();
            }
        });

        edtimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final ArrayList<String> temp = new ArrayList<String>();
                int textlength = edtimkiem.getText().length();
                if (textlength==0)
                    imgxoa.setVisibility(View.INVISIBLE);
                else
                    imgxoa.setVisibility(View.VISIBLE);
                temp.clear();
                for (int i = 0; i < ten_Ngan_Hang.size(); i++)
                {
                    if (textlength <= ten_Ngan_Hang.get(i).length())
                    {
                        if(edtimkiem.getText().toString().equalsIgnoreCase(
                                (String)
                                        ten_Ngan_Hang.get(i).subSequence(0,
                                                textlength)))
                        {
                            temp.add(ten_Ngan_Hang.get(i));
                        }
                    }
                }
                adapter = new ArrayAdapter<String>(luachon_atm.this, R.layout.item_listview, R.id.tvtennganhang, temp);
                lvnganhang.setAdapter(adapter);

                lvnganhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        edtimkiem.setText(temp.get(position));
                        Intent returnIntent=new Intent();
                        returnIntent.putExtra("ten_ngan_hang",edtimkiem.getText().toString());
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
