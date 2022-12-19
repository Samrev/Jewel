package com.example.jewel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleMetalAdapter extends RecyclerView.Adapter<RecycleMetalAdapter.ViewHolder>{
    Context context;
    ArrayList<MetalPayment> items;
    RecycleMetalAdapter(Context context,ArrayList<MetalPayment> items){
        this.context =context;
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_metal_received,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double fine;
        fine = items.get(holder.getAdapterPosition()).getfine();
        holder.s_no.setText(String.valueOf(holder.getAdapterPosition()));
        holder.type.setText(String.valueOf(items.get(holder.getAdapterPosition()).type));
        holder.quality.setText(String.format("Rs %s", items.get(holder.getAdapterPosition()).quality));
        holder.weight.setText(String.format("Rs %s", items.get(holder.getAdapterPosition()).weight));
        holder.fine.setText(String.format("%.3f",fine) + 'g');
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView s_no,type,quality,fine,weight;
        public ViewHolder(View itemView) {
            super(itemView);
            s_no = itemView.findViewById(R.id.Serialid);
            type = itemView.findViewById(R.id.type);
            fine = itemView.findViewById(R.id.fine);
            quality = itemView.findViewById(R.id.quality);
            weight = itemView.findViewById(R.id.weight);
        }
    }


}
