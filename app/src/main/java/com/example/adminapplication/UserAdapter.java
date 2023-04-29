package com.example.adminapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapplication.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;
    private Context context;
    private OnItemClickListener listener;


    public UserAdapter(Context context, List<User> users, OnItemClickListener listener) {
        this.users = users;
        this.context = context;
        this.listener = listener;
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

        holder.bind(users.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        int id;
        public TextView txtSDT;
        public TextView txtTen;
        public LinearLayout btnUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtTen = itemView.findViewById(R.id.txtTen);
            btnUser = itemView.findViewById(R.id.btnUser);
        }

        public void bind(User user, OnItemClickListener listener) {
            txtSDT.setText(user.getPhoneNumber());
            txtTen.setText(user.getFullName());
            id = user.getId();

            btnUser.setOnClickListener(view -> listener.onItemClick(user));
        }

    }
    interface OnItemClickListener {
        void onItemClick(User user);
    }

}