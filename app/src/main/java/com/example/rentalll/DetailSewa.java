package com.example.rentalll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.rentalll.Adapter.BarangAdapter;
import com.example.rentalll.Model.Barang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DetailSewa extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Button btnAdd;



    private FirebaseFirestore db =  FirebaseFirestore.getInstance();

    private List<Barang> list = new ArrayList<>();
    private BarangAdapter barangAdapter;
    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sewa);
        recyclerView = findViewById(R.id.detailSewa);
        btnAdd = findViewById(R.id.btnHapus);


        progressDialog = new ProgressDialog(DetailSewa.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setTitle("Mengambil data");
        barangAdapter  = new BarangAdapter(getApplicationContext(),list);

        barangAdapter.setDialog(new BarangAdapter.Dialog() {
            @Override
            public void onClick(int post) {
                final CharSequence[] dialogItem = {"Edit", "Berhenti menyewa"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailSewa.this);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){


                            case 0:
                                Intent intent = new Intent(getApplicationContext(),Isi_data.class);
                                intent.putExtra("id" ,list.get(post).getId());
                                intent.putExtra("nama" ,list.get(post).getNama());
                                intent.putExtra("alamat" ,list.get(post).getAlamat());
                                intent.putExtra("noHp" ,list.get(post).getNomorHp());
                                intent.putExtra("lama" ,list.get(post).getLama());
                                startActivity(intent);
                                break;
                            case 1:

                                deleteData(list.get(post).getId());
                                break;
                        }
                    }
                });
                dialog.show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(barangAdapter);

        btnAdd.setOnClickListener(V ->{
            startActivity(new Intent(getApplicationContext(), MulaiMenyewa.class));
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData(){
        progressDialog.show();


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()){


                                Barang user = new Barang(document.getString("nama"), document.getString("alamat"), document.getString("noHp"), document.getString("lama"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                            barangAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data Gagal di Ambil!!!!!....",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }


    private void deleteData(String id){
        progressDialog.show();
        db.collection("users").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Gagal di Hapuss!!!!!....",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });

    }
}