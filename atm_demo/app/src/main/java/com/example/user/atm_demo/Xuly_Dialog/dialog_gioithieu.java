package com.example.user.atm_demo.Xuly_Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.user.atm_demo.R;

/**
 * Created by User on 11/10/2016.
 */
public class dialog_gioithieu {
    private Context context;
    private AlertDialog dialog;

    public dialog_gioithieu(Context context, AlertDialog dialog) {
        this.context = context;
        this.dialog = dialog;
    }

    public void xuly_dialog() {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_gioithieu, null);

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(true);

        dialog = alert.create();
        dialog.show();
    }
}
