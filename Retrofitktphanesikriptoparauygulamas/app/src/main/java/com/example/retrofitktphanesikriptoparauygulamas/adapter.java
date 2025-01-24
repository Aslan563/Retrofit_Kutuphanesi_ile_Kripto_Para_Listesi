package com.example.retrofitktphanesikriptoparauygulamas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.vievholder>{
  ArrayList<kriptomodel> arrayListkripto;
  Context context;

  String[] colors={"#f8eba6","#e20d0d","#7313de","#1ad7e9","#efed1b"};

    public adapter( ArrayList<kriptomodel> arrayListkripto,Context context) {
        this.context = context;
        this.arrayListkripto = arrayListkripto;
    }

    @NonNull
    @Override
    public vievholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rowlayout,parent,false);
        return new vievholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vievholder holder, int position) {
    kriptomodel kriptomodel=arrayListkripto.get(position);
    holder.binding(kriptomodel,colors,position);

    }

    @Override
    public int getItemCount() {
        return arrayListkripto.size();
    }

    class vievholder extends RecyclerView.ViewHolder {
        TextView textcurrency,textprice;
        public vievholder(@NonNull View itemView) {
            super(itemView);
            textcurrency=itemView.findViewById(R.id.textcurrency);
            textprice=itemView.findViewById(R.id.textprice);
        }

        public  void binding(kriptomodel crycmodel,String[] colors,int postion){
        itemView.setBackgroundColor(Color.parseColor(colors[postion%5]));
        textcurrency=itemView.findViewById(R.id.textcurrency);
        textprice=itemView.findViewById(R.id.textprice);
        textcurrency.setText(crycmodel.currency);
            textprice.setText(crycmodel.price);
        }
    }
}
