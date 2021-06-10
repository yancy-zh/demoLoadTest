package com.example.demoloadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    Hashtable<String, String> deviceInfo = new Hashtable<>();
    static final String DEFAULT_HOST_ADDRESS = "212.114.131.103";
    static final String DEFAULT_PORT_NUMBER = "443";
    static final String port = "80";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendOnClick(View v){
        EditText editTextDeviceID = findViewById(R.id.editTextDeviceId);
        deviceInfo.put("Device ID", editTextDeviceID.getText().toString());
//        ["Device ID", editTextDeviceID.getText().toString()]
    }
}