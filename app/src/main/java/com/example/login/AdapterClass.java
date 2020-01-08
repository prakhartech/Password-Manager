package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    ArrayList<Deal> list;
    public AdapterClass(ArrayList<Deal> list){
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(list.get(i).getUserTitle());
        myViewHolder.id.setText(list.get(i).getUserWebsite());
        myViewHolder.id.setText(list.get(i).getUserLink());
        myViewHolder.id.setText(list.get(i).getUserUserId());
        myViewHolder.id.setText(list.get(i).getUserpass());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,descweb,desclink,descuid,descpass;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.dealId);
            descweb = itemView.findViewById(R.id.description);
            desclink = itemView.findViewById(R.id.description2);
            descuid = itemView.findViewById(R.id.description3);
            descpass = itemView.findViewById(R.id.description4);
        }
    }
    }


