package com.example.user.atm_demo;

import android.support.design.widget.FloatingActionButton;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by User on 11/5/2016.
 */
public class Xuly_animation {
    private FloatingActionButton fab1,fab2,fab3;
    private Button btntennganhang,btnkhoanhcach;
    private Animation hien_fab1,hien_fab2,hien_fab3,an_fab1,an_fab2,an_fab3,hien_btntennganhang,
            hien_btnkhoangcach,an_btntennganhang,an_btnkhoangcach;

    public Xuly_animation(Animation an_btnkhoangcach, Animation an_btntennganhang, Animation an_fab1, Animation an_fab2,
                          Animation an_fab3, Button btnkhoanhcach, Button btntennganhang, FloatingActionButton fab1,
                          FloatingActionButton fab2, FloatingActionButton fab3, Animation hien_btnkhoangcach, Animation hien_btntennganhang,
                          Animation hien_fab1, Animation hien_fab2, Animation hien_fab3) {
        this.an_btnkhoangcach = an_btnkhoangcach;
        this.an_btntennganhang = an_btntennganhang;
        this.an_fab1 = an_fab1;
        this.an_fab2 = an_fab2;
        this.an_fab3 = an_fab3;
        this.btnkhoanhcach = btnkhoanhcach;
        this.btntennganhang = btntennganhang;
        this.fab1 = fab1;
        this.fab2 = fab2;
        this.fab3 = fab3;
        this.hien_btnkhoangcach = hien_btnkhoangcach;
        this.hien_btntennganhang = hien_btntennganhang;
        this.hien_fab1 = hien_fab1;
        this.hien_fab2 = hien_fab2;
        this.hien_fab3 = hien_fab3;
    }
    public void hienfab(){
        //hien fab1
        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)fab1.getLayoutParams();
        layoutParams.rightMargin+=(int)(fab1.getWidth()*1.1);
        layoutParams.bottomMargin-=(int)(fab1.getHeight()*1.2);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hien_fab1);
        fab1.setClickable(true);
        //hien fab2
        FrameLayout.LayoutParams layoutParams1=(FrameLayout.LayoutParams)fab2.getLayoutParams();
        layoutParams1.rightMargin+=(int)(fab2.getWidth()*1.8);
        layoutParams1.bottomMargin+=(int)(fab2.getHeight()*0.2);
        fab2.setLayoutParams(layoutParams1);
        fab2.startAnimation(hien_fab2);
        fab2.setClickable(true);
        //hien fab3
        FrameLayout.LayoutParams layoutParams2=(FrameLayout.LayoutParams)fab3.getLayoutParams();
        layoutParams2.rightMargin+=(int)(fab3.getWidth()*1.1);
        layoutParams2.bottomMargin+=(int)(fab3.getHeight()*1.55);
        fab3.setLayoutParams(layoutParams2);
        fab3.startAnimation(hien_fab3);
        fab3.setClickable(true);
    }
    public void anfab(){
        //an fab1
        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)fab1.getLayoutParams();
        layoutParams.rightMargin-=(int)(fab1.getWidth()*1.1);
        layoutParams.bottomMargin+=(int)(fab1.getHeight()*1.2);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(an_fab1);
        fab1.setClickable(false);
        //an fab2
        FrameLayout.LayoutParams layoutParams1=(FrameLayout.LayoutParams)fab2.getLayoutParams();
        layoutParams1.rightMargin-=(int)(fab2.getWidth()*1.8);
        layoutParams1.bottomMargin-=(int)(fab2.getHeight()*0.2);
        fab2.setLayoutParams(layoutParams1);
        fab2.startAnimation(an_fab2);
        fab2.setClickable(false);
        //an fab3
        FrameLayout.LayoutParams layoutParams2=(FrameLayout.LayoutParams)fab3.getLayoutParams();
        layoutParams2.rightMargin-=(int)(fab3.getWidth()*1.1);
        layoutParams2.bottomMargin-=(int)(fab3.getHeight()*1.55);
        fab3.setLayoutParams(layoutParams2);
        fab3.startAnimation(an_fab3);
        fab3.setClickable(false);
    }
    public void hienbutton(){
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) btntennganhang.getLayoutParams();
        layoutParams.topMargin += (int) (btntennganhang.getHeight() * (1.5));
        btntennganhang.setLayoutParams(layoutParams);
        btntennganhang.startAnimation(hien_btntennganhang);
        btntennganhang.setClickable(true);


        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) btnkhoanhcach.getLayoutParams();
        layoutParams1.topMargin += (int) (btnkhoanhcach.getHeight() * (2.65));
        btnkhoanhcach.setLayoutParams(layoutParams1);
        btnkhoanhcach.startAnimation(hien_btnkhoangcach);
        btnkhoanhcach.setClickable(true);

    }
    public void anbutton(){
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) btntennganhang.getLayoutParams();
        layoutParams.topMargin -= (int) (btntennganhang.getHeight() * (1.5));
        btntennganhang.setLayoutParams(layoutParams);
        btntennganhang.startAnimation(an_btntennganhang);
        btntennganhang.setClickable(false);


        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) btnkhoanhcach.getLayoutParams();
        layoutParams1.topMargin -= (int) (btnkhoanhcach.getHeight() * (2.65));
        btnkhoanhcach.setLayoutParams(layoutParams1);
        btnkhoanhcach.startAnimation(an_btnkhoangcach);
        btnkhoanhcach.setClickable(false);
    }
}
