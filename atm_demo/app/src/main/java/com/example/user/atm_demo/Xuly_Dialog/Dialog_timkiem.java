package com.example.user.atm_demo.Xuly_Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.user.atm_demo.R;
import com.example.user.atm_demo.chon_atm.luachon_atm;
import com.example.user.atm_demo.cosodulieu.csdl_bank;
import com.example.user.atm_demo.cosodulieu.laydulieu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class Dialog_timkiem extends Activity {

    private Button btntimkiem,btnhuy,btnvitrihientaitimkiem,btntenganhang,btnvitribatdau;
    private Spinner spbankinh;
    private String arr[]={"Tất cả","1 Km","2 Km","3 Km"};
    private String vitribatdau_timkiem="Nhập địa điểm bắt đầu";
    private String vitribatdau_timkiem1="";
    private String stbankinh="";
    private Double TK_lat=0.0;
    private Double TK_lng=0.0;
    private Integer bankinh=0;
    //co so du kieu
    private csdl_bank csdl;
    private ArrayList<String> ten_NH=new ArrayList<String>();
    private ArrayList<String> NganHang_lienket=new ArrayList<String>();
    private Cursor c;
    private Marker marker_atm;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_timkiem);

        anhxa();
        csdl.csdl_tenNH();
        spinner();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnvitribatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickPlace(v, 1);
            }
        });
        btnvitrihientaitimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnvitribatdau.setText("Vị trí hiện tại");
            }
        });
        btntenganhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dialog_timkiem.this,luachon_atm.class);
                i.putExtra("ten_NH",ten_NH);
                startActivityForResult(i, 2);
            }
        });
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent=new Intent();
                returnIntent.putExtra("toado_lat",TK_lat.toString());
                returnIntent.putExtra("toado_lng",TK_lng.toString());
                returnIntent.putExtra("vitribatdau_timkiem1",vitribatdau_timkiem1);
                returnIntent.putExtra("vitribatdau_timkiem",vitribatdau_timkiem);
                returnIntent.putExtra("ban_kinh",bankinh.toString());
                returnIntent.putExtra("ten_NH",btntenganhang.getText().toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
    private void anhxa(){
        btntimkiem=(Button) findViewById(R.id.btntimkiem_atm);
        btnhuy=(Button) findViewById(R.id.btnthoat);
        btntenganhang=(Button) findViewById(R.id.btndialog_tenNH);
        btnvitrihientaitimkiem=(Button) findViewById(R.id.btnvitrihientaitimkiem);
        btnvitribatdau=(Button) findViewById(R.id.btnvitribatdau);
        spbankinh=(Spinner) findViewById(R.id.spbankinh);
        csdl=new csdl_bank(c,Dialog_timkiem.this,NganHang_lienket,ten_NH);

    }
    private void spinner(){
        // khai báo một mảng ArrayAdapter có kiểu String với item là mảng string vừa khai báo ở trên
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,arr);

        // chọn kiểu hiển thị khi click vào spinner giống dạng một dialog custom
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // thêm mảng ArrayAdapter vừa tạo vào spinner
        spbankinh.setAdapter(adapter);

        // phương thức bắt sự kiện spinner
        spbankinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                //  arg2 là vị trí của phần tử trong mảng tính từ 0,1,2..
                //item[arg2] là giá trị hay tên phần tử tương ứng vị trí arg2
                stbankinh=arr[arg2];
                bankinh1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    private void bankinh1(){
        if (stbankinh.equals("Tất cả")){
            bankinh=0;
        }
        if (stbankinh.equals("1 Km")){
            bankinh=1000;
        }
        if (stbankinh.equals("2 Km")){
            bankinh=2000;
        }
        if (stbankinh.equals("3 Km")){
            bankinh=3000;
        }
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
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                Place place=PlaceAutocomplete.getPlace(this,data);
                vitribatdau_timkiem=place.getName().toString();
                vitribatdau_timkiem1=place.getAddress().toString();
                TK_lat=place.getLatLng().latitude;
                TK_lng=place.getLatLng().longitude;
                btnvitribatdau.setText(vitribatdau_timkiem);
            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (requestCode==2)
        {
            if (resultCode==RESULT_OK){
                btntenganhang.setText(data.getStringExtra("ten_ngan_hang").toString());
            }
        }
    }
}
