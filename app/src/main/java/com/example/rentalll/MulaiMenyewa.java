package com.example.rentalll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MulaiMenyewa extends AppCompatActivity {
    ImageView gambar1,gambar2;
    Button lihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulai_menyewa);
        gambar1 = findViewById(R.id.imgTas);
        gambar2 = findViewById(R.id.imgSpatu);

        gambar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Isi_data.class);
                startActivity(intent);
            }
        });
        gambar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(getApplicationContext(),Isi_data.class);
                startActivity(inten);
            }
        });

    }
}