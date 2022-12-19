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

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ViewHolder>{
    Context context;
    ArrayList<Sold> items;
    RecyclerProductAdapter(Context context,ArrayList<Sold> items){
        this.context =context;
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_sold_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.s_no.setText(String.valueOf(holder.getAdapterPosition()));
        holder.type.setText(String.valueOf(items.get(holder.getAdapterPosition()).type));
        holder.weight.setText(items.get(holder.getAdapterPosition()).weight + "g");
        holder.weight_poly.setText(items.get(holder.getAdapterPosition()).weightPoly + "g");
        holder.quality.setText(String.valueOf(items.get(holder.getAdapterPosition()).quality) + '%');
        holder.labour_piece.setText("Rs " + items.get(holder.getAdapterPosition()).labour);
        holder.num.setText(String.valueOf(items.get(holder.getAdapterPosition()).numPiece));
        double price = items.get(holder.getAdapterPosition()).getprice();
        double fine =items.get(holder.getAdapterPosition()).getfine();
        holder.price.setText("Rs " + String.format("%.1f",price));
        holder.fine.setText(String.format("%.3f",fine) + 'g');

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_sold_items);
                EditText weight,wpp,num,lab,quality;
                weight = dialog.findViewById(R.id.weight);
                wpp = dialog.findViewById(R.id.wpp);
                num = dialog.findViewById(R.id.num);
                lab = dialog.findViewById(R.id.elab);
                quality = dialog.findViewById(R.id.quality);
                Spinner types = dialog.findViewById(R.id.type_spinner);
                Spinner slab = dialog.findViewById(R.id.l_spinner);
                Button btadd = dialog.findViewById(R.id.btadd);
                btadd.setText(R.string.Update);

                weight.setText(String.valueOf(items.get(holder.getAdapterPosition()).weight));
                wpp.setText(String.valueOf(items.get(holder.getAdapterPosition()).weightPoly));
                String[] labs = items.get(holder.getAdapterPosition()).labour.split("/");
                lab.setText(labs[0]);
                quality.setText(String.valueOf(items.get(holder.getAdapterPosition()).quality));
                num.setText(String.valueOf(items.get(holder.getAdapterPosition()).numPiece));
                String check = "gold";
                types.setSelection(check.equals(items.get(holder.getAdapterPosition()).type) ? 0 : 1);
                slab.setSelection(labs[1].equals("Kg") ? 0 : 1);
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
                        if(!flag) {
                            weight1 = Double.parseDouble(weight.getText().toString());
                            TextView v = (TextView) types.getSelectedView();
                            TextView v1 = (TextView) slab.getSelectedView();
                            type1 = v.getText().toString();
                            slab1 = v1.getText().toString();
                            if(!wpp.getText().toString().isEmpty()) {
                                wpp1 = Double.parseDouble(wpp.getText().toString());
                            }
                            num1 = Double.parseDouble(num.getText().toString());
                            lab1 = lab.getText().toString() + slab1;
                            quality1 = Double.parseDouble(quality.getText().toString());

                            items.set(holder.getAdapterPosition(),
                                    new Sold(type1, weight1, wpp1, num1, quality1, lab1));
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
        TextView s_no,type,quality,weight,weight_poly,num,labour_piece,fine,price;
        GridLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            s_no = itemView.findViewById(R.id.Serialid);
            type = itemView.findViewById(R.id.type);
            quality = itemView.findViewById(R.id.quality);
            weight = itemView.findViewById(R.id.weight);
            weight_poly = itemView.findViewById(R.id.weight_poly);
            num = itemView.findViewById(R.id.num);
            labour_piece = itemView.findViewById(R.id.labour);
            fine = itemView.findViewById(R.id.fine);
            price = itemView.findViewById(R.id.Price);
            item = itemView.findViewById(R.id.item);
        }
    }


}
