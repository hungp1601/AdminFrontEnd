package com.example.adminapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapplication.models.User;
import com.example.adminapplication.models.response.BaseResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;
    private OnItemClickListener onItemClickListener;

    public UserAdapter(List<User> users, OnItemClickListener onItemClickListener) {
        this.users = users;
        this.onItemClickListener = onItemClickListener;
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
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        int id;
        public TextView txtSDT;
        public TextView txtTen;
        private OnItemClickListener onItemClickListener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtTen = itemView.findViewById(R.id.txtTen);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }
}