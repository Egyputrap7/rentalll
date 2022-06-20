package com.example.rentalll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Isi_data extends AppCompatActivity {
    private EditText nama,alamat,noHp,lamaSewa;
    private Button Simpan, ShowData;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_data);
        nama = findViewById(R.id.msNama);
        alamat = findViewById(R.id.msAlamat);
        noHp = findViewById(R.id.msHp);
        lamaSewa = findViewById(R.id.msLama);
        Simpan = findViewById(R.id.btnSimpan);

        progressDialog = new ProgressDialog(Isi_data.this);
        progressDialog.setTitle("Loading..");
        progressDialog.setTitle("Menyimpan..");

        Simpan.setOnClickListener(v ->{
            /**
             * memanggil method save data
             */

            if (nama.getText().length()>0 && alamat.getText().length()>0&& noHp.getText().length()>0&& lamaSewa.getText().length()>0){
                saveData(nama.getText().toString(), alamat.getText().toString(), noHp.getText().toString(), lamaSewa.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(),"Silahkan Isi Semua !!!!!....",Toast.LENGTH_SHORT).show();

            }
        });

        /**
         * mendapatkan data dari main activity
         */

        Intent intent = getIntent();
        if (intent != null){
            id = intent.getStringExtra("id");
            nama.setText(intent.getStringExtra("nama"));
            alamat.setText(intent.getStringExtra("alamat"));
            noHp.setText(intent.getStringExtra("noHp"));
            lamaSewa.setText(intent.getStringExtra("lama"));

        }
    }

    private void saveData(String nama,String alamat,String noHp,String lama){
        Map<String, Object> user = new HashMap<>();
        user.put("nama",nama);
        user.put("alamat",alamat);
        user.put("noHp",noHp);
        user.put("lama",lama);



        progressDialog.show();

        /**
         * Jika id kosong maka akan edit data
         */

        if (id !=null){
            /**
             * Kode untuk edit data forestore dengan mengambil id
             */

            db.collection("users").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil !!!!!....",Toast.LENGTH_SHORT).show();
                                finish();

                            }else {
                                Toast.makeText(getApplicationContext(),"Gagal !!!!!....",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }else {
            /**
             * kode untuk menambahkan data dengan .add
             */

            db.collection("users")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(getApplicationContext(),"Berhasill!!!!!....",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });
        }
    }

}