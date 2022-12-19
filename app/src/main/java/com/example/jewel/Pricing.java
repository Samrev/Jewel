package com.example.jewel;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Pricing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        EditText goldPrice,silverPrice;
        Button btPrice;
        goldPrice = findViewById(R.id.goldPriceId);
        silverPrice = findViewById(R.id.silverPriceId);
        btPrice = findViewById(R.id.btPrice);
        Intent inext = new Intent(Pricing.this , Selling.class);
        btPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double gP = 0,sP = 0;
                if(!goldPrice.getText().toString().isEmpty()) {
                    gP = Double.parseDouble(goldPrice.getText().toString());
                }
                if(!goldPrice.getText().toString().isEmpty()) {
                    sP = Double.parseDouble(silverPrice.getText().toString());
                }
                inext.putExtra("goldPrice",gP);
                inext.putExtra("silverPrice",sP);
                startActivity(inext);
            }
        });
    }
}