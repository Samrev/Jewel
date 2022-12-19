package com.example.jewel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Selling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);

        Intent inet = getIntent();
        ArrayList<Sold> products = new ArrayList<>();

        RecyclerView items = findViewById(R.id.itemids);
        Button btadd = findViewById(R.id.btadd);
        items.setLayoutManager(new LinearLayoutManager(this));
        double gP = inet.getDoubleExtra("goldPrice",0);
        double sP = inet.getDoubleExtra("silverPrice",0);
        RecyclerProductAdapter adapter = new RecyclerProductAdapter(this,products);
        items.setAdapter(adapter);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Selling.this);
                dialog.setContentView(R.layout.add_sold_items);
                dialog.show();

                EditText weight,wpp,num,lab,quality;
                weight = dialog.findViewById(R.id.weight);
                wpp = dialog.findViewById(R.id.wpp);
                num = dialog.findViewById(R.id.num);
                lab = dialog.findViewById(R.id.elab);
                quality = dialog.findViewById(R.id.quality);
                Spinner type = dialog.findViewById(R.id.type_spinner);
                Spinner slab = dialog.findViewById(R.id.l_spinner);
                Button btadd = dialog.findViewById(R.id.btadd);
                btadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double weight1, wpp1=0, num1,quality1;
                        String type1,slab1,lab1;
                        boolean flag = false;
                        if(weight.getText().toString().isEmpty()){
                            flag = true;
                            weight.setError("Required");
                        }
                        if(num.getText().toString().isEmpty()){
                            flag = true;
                            num.setError("Required");
                        }
                        if(lab.getText().toString().isEmpty()){
                            flag = true;
                            lab.setError("Required");
                        }
                        if(quality.getText().toString().isEmpty()){
                            flag = true;
                            quality.setError("Required");
                        }
                        else if(Double.parseDouble(quality.getText().toString())>100){
                            flag = true;
                            quality.setError("Must be between 0-100");
                        }

                        if(!flag) {
                            weight1 = Double.parseDouble(weight.getText().toString());
                            TextView v = (TextView) type.getSelectedView();
                            TextView v1 = (TextView) slab.getSelectedView();
                            type1 = v.getText().toString();
                            slab1 = v1.getText().toString();
                            if(!wpp.getText().toString().isEmpty()) {
                                wpp1 = Double.parseDouble(wpp.getText().toString());
                            }
                            num1 = Double.parseDouble(num.getText().toString());
                            lab1 = lab.getText().toString() + slab1;
                            quality1 = Double.parseDouble(quality.getText().toString());

                            products.add(new Sold(type1, weight1, wpp1, num1, quality1, lab1));
                            adapter.notifyItemInserted(products.size() - 1);
                            items.scrollToPosition(products.size() - 1);
                            dialog.dismiss();
                        }
                    }
                });

            }
        });
        Button btcal = findViewById(R.id.btcal);
        TextView calgfine, calsfine,calamount;
        calgfine = findViewById(R.id.calgfine);
        calsfine = findViewById(R.id.calsfine);
        calamount = findViewById(R.id.calamount);


        btcal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                double gp =0,sp=0,am=0;
                String gold = "gold";
                for(Sold s : products){
                    if(gold.equals(s.type)){
                        gp += s.getfine();
                    }
                    else{
                        sp += s.getfine();
                    }
                    am += s.getprice();
                }
                calgfine.setText(String.format("%.3f",gp) + "g");
                calsfine.setText(String.format("%.3f",sp) + "g");
                calamount.setText("Rs "+ am);
            }
        });
        Button btcont = findViewById(R.id.btcont);
        btcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Selling.this , Receiving.class);
                startActivity(inext);
            }
        });
    }

}