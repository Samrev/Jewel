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
        holder.quality.setText(String.format("%s%%", items.get(holder.getAdapterPosition()).quality));
        holder.weight.setText(String.format("%sg", items.get(holder.getAdapterPosition()).weight));
        holder.fine.setText(String.format("%.3f",fine) + 'g');

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_metal);
                EditText weight,quality;
                weight = dialog.findViewById(R.id.weight);
                quality = dialog.findViewById(R.id.quality);
                Spinner types = dialog.findViewById(R.id.type_spinner);
                Button btadd = dialog.findViewById(R.id.btadd);
                btadd.setText(R.string.Update);

                weight.setText(String.valueOf(items.get(holder.getAdapterPosition()).weight));
                quality.setText(String.valueOf(items.get(holder.getAdapterPosition()).quality));
                String check = "gold";
                types.setSelection(check.equals(items.get(holder.getAdapterPosition()).type) ? 0 : 1);
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
                            TextView v = (TextView) types.getSelectedView();
                            type1 = v.getText().toString();
                            quality1 = Double.parseDouble(quality.getText().toString());

                            items.set(holder.getAdapterPosition(),
                                    new MetalPayment(weight1,quality1,type1));
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
        TextView s_no,type,quality,fine,weight;
        GridLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            s_no = itemView.findViewById(R.id.Serialid);
            type = itemView.findViewById(R.id.type);
            fine = itemView.findViewById(R.id.fine);
            quality = itemView.findViewById(R.id.quality);
            weight = itemView.findViewById(R.id.weight);
            item = itemView.findViewById(R.id.item);
        }
    }


}
