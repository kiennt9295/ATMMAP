package com.example.user.atm_demo.Xuly_Dialog;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.user.atm_demo.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class Dialog_timduong extends Activity {

    private String vitribatdau_timduong="Nhập địa điểm bắt đầu";
    private String vitribatdau_timduong1="";
    private Double TDBD_lat=0.0;
    private Double TDBD_lng=0.0;
    private String vitriketthuc_timduong="Nhập địa điểm kết thúc";
    private String vitriketthuc_timduong1="";
    private Double TDKT_lat=0.0;
    private Double TDKT_lng=0.0;

    private Button btnTimkiem_duong,btnThoat,btnVitribatdau_duong,btnVitriketthuc_duong,btnVitrihientai_duong,btnVitrihientai_duong1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_timduong);

        anhxa();
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnVitribatdau_duong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickPlace(v, 1);
            }
        });
        btnVitriketthuc_duong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickPlace(v,2);
            }
        });
        btnTimkiem_duong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnItent1=new Intent();
                returnItent1.putExtra("TDBD_lat",TDBD_lat.toString());
                returnItent1.putExtra("TDBD_lng",TDBD_lng.toString());
                returnItent1.putExtra("TDKT_lat",TDKT_lat.toString());
                returnItent1.putExtra("TDKT_lng",TDKT_lng.toString());
                setResult(RESULT_OK,returnItent1);
                finish();
            }
        });

    }
    private void anhxa(){
        btnTimkiem_duong=(Button)findViewById(R.id.btntimkiem_duong);
        btnThoat=(Button)findViewById(R.id.btnthoat_duong);
        btnVitribatdau_duong=(Button)findViewById(R.id.btnvitrribatdau_duong);
        btnVitriketthuc_duong=(Button)findViewById(R.id.btnvitriketthuc_duong);
        btnVitrihientai_duong=(Button)findViewById(R.id.btnvitrihientai_duong);
        btnVitrihientai_duong1=(Button)findViewById(R.id.btnvitrihientai_duong1);

    }
    public void OnclickPlace(View view,int id) {
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, id);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                vitribatdau_timduong1=place.getAddress().toString();
                vitribatdau_timduong=place.getName().toString();
                btnVitribatdau_duong.setText(vitribatdau_timduong);
                TDBD_lat=place.getLatLng().latitude;
                TDBD_lng=place.getLatLng().longitude;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                vitriketthuc_timduong1=place.getAddress().toString();
                vitriketthuc_timduong=place.getName().toString();
                TDKT_lat=place.getLatLng().latitude;
                TDKT_lng=place.getLatLng().longitude;
                btnVitriketthuc_duong.setText(vitriketthuc_timduong);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

    }
}
