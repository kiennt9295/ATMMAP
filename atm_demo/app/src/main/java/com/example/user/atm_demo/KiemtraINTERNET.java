package com.example.user.atm_demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class KiemtraINTERNET {
    private Context context;
    public KiemtraINTERNET(Context context){
        this.context=context;
    }
    public boolean isketnoiInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
}
