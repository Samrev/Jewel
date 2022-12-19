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
import android.widget.Spinner;
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
        holder.value.setText("Rs " + String.valueOf(items.get(holder.getAdapterPosition()).value));
        holder.amount.setText("Rs " + String.valueOf(items.get(holder.getAdapterPosition()).amount));
        holder.fine.setText(String.format("%.3f",fine) + 'g');

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_cash);
                EditText amount,value;
                amount = dialog.findViewById(R.id.amount);
                value = dialog.findViewById(R.id.value);
                Spinner types = dialog.findViewById(R.id.type_spinner);
                Button btadd = dialog.findViewById(R.id.btadd);
                btadd.setText(R.string.Update);

                amount.setText(String.valueOf(items.get(holder.getAdapterPosition()).amount));
                value.setText(String.valueOf(items.get(holder.getAdapterPosition()).value));
                String check = "gold";
                types.setSelection(check.equals(items.get(holder.getAdapterPosition()).type) ? 0 : 1);
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
                            TextView v = (TextView) types.getSelectedView();
                            type1 = v.getText().toString();
                            value1 = Double.parseDouble(value.getText().toString());

                            items.set(holder.getAdapterPosition(),
                                    new CashPayment(amount1,value1,type1));
                            notifyItemChanged(holder.getAdapterPosition());
                            dialog.dismiss();
                        }

                    }
                });
                dialog.show();
            }
        });
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                items.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

                return true;
            }
        });
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
