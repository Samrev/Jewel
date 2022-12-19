package com.example.jewel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView Clients;
    Button btcont;
    ArrayList<String> Clientids = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcont = findViewById(R.id.clientcont);
        Clients = findViewById(R.id.clientid);
        Clientids.add("Demo");


        ArrayAdapter<String> Clientadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Clientids);
        Clients.setAdapter(Clientadapter);
        Clients.setThreshold(2);
        Intent inext = new Intent(MainActivity.this , Pricing.class);
        btcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(inext);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitdiag = new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit")
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        exitdiag.show();
    }
}