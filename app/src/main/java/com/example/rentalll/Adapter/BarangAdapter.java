package com.example.rentalll.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalll.Model.Barang;
import com.example.rentalll.R;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    private Context context;
    private List<Barang> list;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int post);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public BarangAdapter(Context context, List<Barang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lihat_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.alamat.setText(list.get(position).getAlamat());
        holder.noHp.setText(list.get(position).getNomorHp());
        holder.lama.setText(list.get(position).getLama());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, alamat, noHp, lama;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.l_nama);
            alamat = itemView.findViewById(R.id.l_alamat);
            noHp = itemView.findViewById(R.id.l_noHP);
            lama = itemView.findViewById(R.id.l_sewa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}


