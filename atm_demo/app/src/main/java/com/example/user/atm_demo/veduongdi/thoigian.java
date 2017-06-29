package com.example.user.atm_demo.veduongdi;

/**
 * Created by User on 10/24/2016.
 */
public class thoigian {
    private String text;
    private int value;

    public thoigian(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
