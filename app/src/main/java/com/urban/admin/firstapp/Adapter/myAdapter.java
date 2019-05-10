package com.urban.admin.firstapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.urban.admin.firstapp.Fragments.update;
import com.urban.admin.firstapp.Model.UserData;
import com.urban.admin.firstapp.R;

import java.util.ArrayList;
import java.util.List;


public class myAdapter extends RecyclerView.Adapter<myAdapter.viewholder> {
    List<UserData> data = new ArrayList<>();
    Context context;

    public myAdapter(List<UserData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder viewholder, final int i) {
        UserData userData = data.get(i);
        viewholder.id.setText(userData.getId());
        viewholder.name.setText(userData.getName());
        viewholder.email.setText(userData.getEmail());
        viewholder.mobile.setText(Long.toString(userData.getMobile()));
        viewholder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update update = new update();
                Bundle bundle = new Bundle();
                bundle.putString("id", data.get(i).getId());
                bundle.putString("Name", data.get(i).getName());
                bundle.putString("Mobile", String.valueOf(data.get(i).getMobile()));
                bundle.putString("Email", data.get(i).getEmail());
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                update.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.MainLayout, update).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView id, name, mobile, email;
        ImageButton edit;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.uid);
            name = itemView.findViewById(R.id.uname);
            mobile = itemView.findViewById(R.id.uphone);
            email = itemView.findViewById(R.id.umail);
            edit = itemView.findViewById(R.id.uedit);
        }
    }
}

