package com.example.adminapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapplication.models.User;
import com.example.adminapplication.models.response.BaseResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;
    private Context context;

        public UserAdapter(Context context, List<User> users) {
            this.users = users;
            this.context = context;
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_listuser, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.txtSDT.setText(user.getPhoneNumber());
        holder.txtTen.setText(user.getFullName());
        holder.id = user.getId();
        holder.mUserButton.setOnClickListener(view -> Toast.makeText(context, "Button clicked for user " + position, Toast.LENGTH_SHORT).show());
        holder.itemView.setOnClickListener(view -> Toast.makeText(context, "Item clicked for user " + position, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        int id;
        public TextView txtSDT;
        public TextView txtTen;
        public ImageButton mUserButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtTen = itemView.findViewById(R.id.txtTen);
            mUserButton = itemView.findViewById(R.id.btnVector);
        }

    }


}