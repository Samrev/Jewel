package com.example.jewel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleCashAdapter extends RecyclerView.Adapter<RecycleCashAdapter.ViewHolder>{
    Context context;
    ArrayList<CashPayment> items;
    RecycleCashAdapter(Context context,ArrayList<CashPayment> items){
        this.context =context;
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_cash_received,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double fine=0;
        if(holder.getAdapterPosition() == 1){
            fine =items.get(holder.getAdapterPosition()).getfine();
        }
        else{
            fine = items.get(holder.getAdapterPosition()).getfine();
        }
        holder.s_no.setText(String.valueOf(holder.getAdapterPosition()));
        holder.type.setText(String.valueOf(items.get(holder.getAdapterPosition()).type));
        holder.value.setText("Rs " + String.valueOf(items.get(holder.getAdapterPosition()).Value));
        holder.amount.setText("Rs " + String.valueOf(items.get(holder.getAdapterPosition()).Amount));
        holder.fine.setText(String.format("%.3f",fine) + 'g');

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView s_no,type,value,fine,amount;
        GridLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            s_no = itemView.findViewById(R.id.Serialid);
            type = itemView.findViewById(R.id.type);
            fine = itemView.findViewById(R.id.fine);
            value = itemView.findViewById(R.id.value);
            amount = itemView.findViewById(R.id.amount);
            item = itemView.findViewById(R.id.item);
        }
    }


}
