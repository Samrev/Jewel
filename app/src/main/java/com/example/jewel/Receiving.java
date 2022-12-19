package com.example.jewel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Receiving extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
        ArrayList<CashPayment> cpay = new ArrayList<>();

        RecyclerView citems = findViewById(R.id.cashids);
        Button btaddc = findViewById(R.id.btaddc);
        citems.setLayoutManager(new LinearLayoutManager(this));
        RecycleCashAdapter adapter = new RecycleCashAdapter(this, cpay);
        citems.setAdapter(adapter);

        btaddc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Receiving.this);
                dialog.setContentView(R.layout.add_cash);
                dialog.show();
                EditText amount,value;
                amount = dialog.findViewById(R.id.amount);
                value = dialog.findViewById(R.id.value);
                Spinner type = dialog.findViewById(R.id.type_spinner);
                Button btadd = dialog.findViewById(R.id.btadd);
                btadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double amount1,value1;
                        String type1;
                        boolean flag = false;
                        if(amount.getText().toString().isEmpty()){
                            flag = true;
                            amount.setError("Required");
                        }
                        if(value.getText().toString().isEmpty()){
                            flag = true;
                            value.setError("Required");
                        }
                        if(!flag) {
                            amount1 = Double.parseDouble(amount.getText().toString());
                            TextView v = (TextView) type.getSelectedView();
                            type1 = v.getText().toString();
                            value1 = Double.parseDouble(value.getText().toString());

                            cpay.add(new CashPayment(amount1,value1,type1));
                            adapter.notifyItemInserted(cpay.size() - 1);
                            citems.scrollToPosition(cpay.size() - 1);
                            dialog.dismiss();
                        }
                    }
                });

            }
        });

        ArrayList<MetalPayment> mpay = new ArrayList<>();
        RecyclerView mitems = findViewById(R.id.metalids);
        Button btaddm = findViewById(R.id.btaddm);
        mitems.setLayoutManager(new LinearLayoutManager(this));
        RecycleMetalAdapter adapter2 = new RecycleMetalAdapter(this,mpay);
        mitems.setAdapter(adapter2);

        btaddm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Receiving.this);
                dialog.setContentView(R.layout.add_metal);
                dialog.show();
                EditText weight,quality;
                weight = dialog.findViewById(R.id.weight);
                quality = dialog.findViewById(R.id.quality);
                Spinner type = dialog.findViewById(R.id.type_spinner);
                Button btadd = dialog.findViewById(R.id.btadd);
                btadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double weight1,quality1;
                        String type1;
                        boolean flag = false;
                        if(weight.getText().toString().isEmpty()){
                            flag = true;
                            weight.setError("Required");
                        }
                        if(quality.getText().toString().isEmpty()){
                            flag = true;
                            quality.setError("Required");
                        }
                        if(!flag) {
                            weight1 = Double.parseDouble(weight.getText().toString());
                            TextView v = (TextView) type.getSelectedView();
                            type1 = v.getText().toString();
                            quality1 = Double.parseDouble(quality.getText().toString());

                            mpay.add(new MetalPayment(quality1,weight1,type1));
                            adapter.notifyItemInserted(mpay.size() - 1);
                            mitems.scrollToPosition(mpay.size() - 1);
                            dialog.dismiss();
                        }
                    }
                });

            }
        });
        Button btcal = findViewById(R.id.btcal);
        TextView calgfine = findViewById(R.id.calgfine);
        TextView calsfine = findViewById(R.id.calsfine);
        btcal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double gp =0,sp=0;
                String gold = "gold";
                for(CashPayment p : cpay){
                    if(gold.equals(p.type)){
                        gp += p.getfine();
                    }
                    else{
                        sp += p.getfine();
                    }
                }
                for(MetalPayment p : mpay){
                    if(gold.equals(p.type)){
                        gp += p.getfine();
                    }
                    else{
                        sp += p.getfine();
                    }
                }
                calgfine.setText(String.format("%.3f",gp) + "g");
                calsfine.setText(String.format("%.3f",sp) + "g");
            }
        });

    }
}