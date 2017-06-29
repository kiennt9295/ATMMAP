package com.example.user.atm_demo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.atm_demo.Xuly_Dialog.Dialog_timduong;
import com.example.user.atm_demo.Xuly_Dialog.Dialog_timkiem;
import com.example.user.atm_demo.Xuly_Dialog.dialog_gioithieu;
import com.example.user.atm_demo.chon_atm.luachon_atm;
import com.example.user.atm_demo.cosodulieu.csdl_bank;
import com.example.user.atm_demo.cosodulieu.laydulieu;
import com.example.user.atm_demo.veduongdi.DirectionFinder;
import com.example.user.atm_demo.veduongdi.DirectionFinderListener;
import com.example.user.atm_demo.veduongdi.Route;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,DirectionFinderListener {

    private GoogleMap mMap;
    private FrameLayout khoidong;
    //khai bao bien floadbutton
    private FloatingActionButton fab,fab1,fab2,fab3;
    //button khi nhan vao marker
    private Button btntennganhang,btnkhoangcach;
    private ImageView imglogo,imggioithieu,imgthongtin;
    private TextView tvlogo;

    private GoogleApiClient googleApiClient;
    private Location LastLocation;
    private Marker LocationMarker;
    private LocationRequest locationRequest;

    private Xuly_animation xuly_animation;
    private KiemtraINTERNET kiemtraINTERNET;

    private AlertDialog dialog1;
    private Animation rotate,animTogether,animMove,animMove2,show_fab_1,hide_fab_1,show_fab_2,hide_fab_2,show_fab_3,hide_fab_3,animMovean,animMove2an;
    private boolean ktfab=true,ktinternet=false;

    private dialog_gioithieu dialogGioithieu;

    //tim kiem tru atm
    private Double TK_lat=0.0;
    private Double TK_lng=0.0;
    private int bankinh=0;
    private Marker marker_TK;
    private Circle circle_TK;
    private String vitribatdau_timkiem="Nhập địa điểm bắt đầu";
    private String vitribatdau_timkiem1="";
    //tim duong di giua 2 diem
    private Double TDBD_lat=0.0;
    private Double TDBD_lng=0.0;
    private Double TDKT_lat=0.0;
    private Double TDKT_lng=0.0;
    private List<Marker> marker_atm=new ArrayList<>();
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();

    //Co so du lieu
    private csdl_bank truycap_csdl;
    private laydulieu csdl_nganhang;
    private ArrayList<String> atm_ten=new ArrayList<String>();
    private ArrayList<Double> atm_lat=new ArrayList<Double>();
    private ArrayList<Double> atm_lng=new ArrayList<Double>();
    private ArrayList<String> ten_NH=new ArrayList<String>();
    private ArrayList<String> NganHang_lienket=new ArrayList<String>();
    private Cursor c;
    private String tenbang;

    private boolean kiemtra_veduong=true;

    //
    private ArrayList<Double> kc_tam=new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        INTERNET();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        anhxa();
        truycap_csdl.csdl_tenNH();

        tvlogo.startAnimation(rotate);
        imglogo.startAnimation(animTogether);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ktfab == true) {
                    btntennganhang.setText("tên ngân hàng");
                    btnkhoangcach.setText("Khoảng cách");
                    xuly_animation.hienfab();
                    xuly_animation.hienbutton();
                    ktfab = false;
                } else {
                    xuly_animation.anfab();
                    xuly_animation.anbutton();
                    ktfab = true;
                }
            }
        });
        btntennganhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this,luachon_atm.class);
                i.putExtra("ten_NH",ten_NH);
                startActivityForResult(i,1);
            }
        });
        btnkhoangcach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imggioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGioithieu.xuly_dialog();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this,Dialog_timduong.class);
                startActivityForResult(i, 3);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this,Dialog_timkiem.class);
                startActivityForResult(i, 2);
            }
        });
    }
    public void anhxa(){
        khoidong = (FrameLayout) findViewById(R.id.khoidong);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        imglogo=(ImageView)findViewById(R.id.imglogo);
        imggioithieu=(ImageView)findViewById(R.id.imgGioithieu);
        tvlogo=(TextView)findViewById(R.id.tvlogo);
        btntennganhang=(Button)findViewById(R.id.btntenNH);
        btnkhoangcach=(Button)findViewById(R.id.btnKC);

        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);

        animMove = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animMove2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move2);
        animMovean = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_an);
        animMove2an = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move2_an);
        animTogether = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.together);
        rotate=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);

        xuly_animation=new Xuly_animation(animMove2an,animMovean,hide_fab_1,hide_fab_2,hide_fab_3,btnkhoangcach,btntennganhang,
                fab1,fab2,fab3,animMove2,animMove,show_fab_1,show_fab_2,show_fab_3);
        dialogGioithieu=new dialog_gioithieu(MapsActivity.this,dialog1);
        truycap_csdl=new csdl_bank(c,MapsActivity.this,NganHang_lienket,ten_NH);
    }
    private void INTERNET() {
        kiemtraINTERNET = new KiemtraINTERNET(getApplicationContext());
        ktinternet = kiemtraINTERNET.isketnoiInternet();
        if (ktinternet == false) {
            AlertDialog dialog = new AlertDialog.Builder(MapsActivity.this)
                    .setTitle("Thông báo")
                    .setMessage("Bạn chưa kết nối Internet!!!")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create();
            dialog.show();
        }
        else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                kiemtranguoidung();
            }
        }
    }

        @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            hienthi_tatca_atm();
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient();
                    mMap.setMyLocationEnabled(true);
                }
                else {
                    MarkerOptions markerOptions = new MarkerOptions();
                    // Add a marker in Sydney and move the camera
                    LatLng spkt = new LatLng(10.7859733, 106.7005283);
                    markerOptions.position(spkt);
                    markerOptions.title("Đại học SPKT TPHCM");
                    //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.superman));
                    mMap.addMarker(markerOptions);

                    //move map camera
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(spkt));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                }
            }
            else {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                khoidong.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.VISIBLE);
            }
        });
    }
    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LastLocation = location;
        if (LocationMarker != null) {
            LocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        //markerOptions.title(location.getProvider());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        LocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        //stop location updates
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }

    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean kiemtranguoidung(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (googleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                }
                return;
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==1){
            if (resultCode==RESULT_OK){
                btntennganhang.setText(data.getStringExtra("ten_ngan_hang").toString());
                tenbang=btntennganhang.getText().toString();

                if(marker_atm!=null){
                    for (Marker marker:marker_atm){
                        marker.remove();
                    }
                    atm_ten.clear();
                    atm_lat.clear();
                    atm_lng.clear();
                }
                csdl_nganhang = new laydulieu(atm_lat, atm_lng, atm_ten, c, MapsActivity.this,tenbang);
                csdl_nganhang.bang_ngan_hang();
                hienthi_atm(tenbang);
            }
        }

        if (requestCode==2){
            if (resultCode==RESULT_OK){
                vitribatdau_timkiem=data.getStringExtra("vitribatdau_timkiem");
                vitribatdau_timkiem1=data.getStringExtra("vitribatdau_timkiem1");
                TK_lat=Double.valueOf(data.getStringExtra("toado_lat").toString());
                TK_lng=Double.valueOf(data.getStringExtra("toado_lng").toString());
                bankinh=Integer.valueOf(data.getStringExtra("ban_kinh"));
                tenbang=data.getStringExtra("ten_NH");
                timkiem();
            }
        }
        if (requestCode==3){
            if (resultCode==RESULT_OK){
                TDBD_lat=Double.valueOf(data.getStringExtra("TDBD_lat"));
                TDBD_lng=Double.valueOf(data.getStringExtra("TDBD_lng"));
                TDKT_lat=Double.valueOf(data.getStringExtra("TDKT_lat"));
                TDKT_lng=Double.valueOf(data.getStringExtra("TDKT_lng"));
                duongdi();
            }
        }
    }
    @Override
    public void onDirectionFinderStart() {
        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route) {
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();
        for (Route route1 : route) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route1.startLocation, 16));
            Toast.makeText(MapsActivity.this, "Khoảng cách: " + route1.duration.getText() + "Thời gian" + route1.distance.getText(), Toast.LENGTH_SHORT).show();

            if (kiemtra_veduong==true) {
                originMarkers.add(mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.home))
                        .title(route1.startAddress)
                        .position(route1.startLocation)));
                destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.home))
                        .title(route1.endAddress)
                        .position(route1.endLocation)));
            }
            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.GREEN).
                    width(10);

            for (int i = 0; i < route1.points.size(); i++)
                polylineOptions.add(route1.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));

            }
        kiemtra_veduong=true;
    }

    private void timkiem(){
        if (marker_TK!=null)
        {
            marker_TK.remove();
            marker_TK=null;
            circle_TK.remove();
            circle_TK=null;
        }
        LatLng TK_toado = new LatLng(TK_lat,TK_lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(TK_toado);
        markerOptions.title(vitribatdau_timkiem + ": " + vitribatdau_timkiem1);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.home));
        marker_TK= mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TK_toado));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        circle_TK=drawCircle(new LatLng(TK_lat,TK_lng),bankinh);
        khoangcach_bankinh(1000);
    }
    public void hienthi_atm(String ten_bang){
        int i=0;
        for (int j=0;j<atm_lat.size();j++) {
            i++;
            atm(j,ten_bang);
        }
        Toast.makeText(this,ten_bang+": "+i,Toast.LENGTH_LONG).show();
    }
    private void atm(int j,String ten_bang){
        LatLng TK_toado = new LatLng(atm_lat.get(j), atm_lng.get(j));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(TK_toado);
        markerOptions.title(ten_bang + ": " + atm_ten.get(j));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.atm));
        marker_atm.add(mMap.addMarker(markerOptions));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                kiemtra_veduong=false;
                String name = marker.getTitle();
                Double origin = TK_lat;
                Double origin1 = TK_lng;
                Double destination = marker.getPosition().latitude;
                Double destination1 = marker.getPosition().longitude;
                Toast.makeText(MapsActivity.this, name, Toast.LENGTH_LONG).show();
                if (!name.equalsIgnoreCase(vitribatdau_timkiem + ": " + vitribatdau_timkiem1)) {
                    try {
                        new DirectionFinder(MapsActivity.this, origin, origin1, destination, destination1).execute();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }
    private void hienthi_tatca_atm(){
        for (int i=0;i<ten_NH.size();i++){
            tenbang=ten_NH.get(i);
            csdl_nganhang = new laydulieu(atm_lat, atm_lng, atm_ten, c, MapsActivity.this,tenbang);
            csdl_nganhang.bang_ngan_hang();
            hienthi_atm(tenbang);
        }
    }
    private Circle drawCircle(LatLng latLng,int met) {
        CircleOptions options=new CircleOptions().center(latLng).radius(met).fillColor(0x22EE00FF).strokeColor(Color.BLUE).strokeWidth(3);
        return mMap.addCircle(options);
    }
    private void duongdi(){
        Double origin=TDBD_lat;
        Double origin1=TDBD_lng;
        Double destination = TDKT_lat;
        Double destination1=TDKT_lng;
        try {
            new DirectionFinder(this, origin,origin1, destination,destination1).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void khoangcach_bankinh(int gioihan_bankinh) {
        LatLng mMarkerA = new LatLng(TK_lat, TK_lng);
        int k = 0;
        if (marker_atm != null) {
            for (Marker marker : marker_atm) {
                marker.remove();
            }
        }
        for (int i = 0; i < atm_lat.size(); i++) {
            LatLng mMarkerB = new LatLng(atm_lat.get(i), atm_lng.get(i));
            double distance = SphericalUtil.computeDistanceBetween(mMarkerA, mMarkerB);
            if (distance <= Double.valueOf(gioihan_bankinh)) {
                atm(i,"");
            }
        }
    }
}
