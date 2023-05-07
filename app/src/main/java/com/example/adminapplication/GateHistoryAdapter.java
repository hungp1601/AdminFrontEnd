package com.example.adminapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapplication.models.GateHistory;

import java.util.List;

public class GateHistoryAdapter extends RecyclerView.Adapter<GateHistoryAdapter.ViewHolder>{

    private List<GateHistory> gateHistories;
    private Context context;
    private  GateHistoryAdapter.OnItemClickListener listener;

    public GateHistoryAdapter(Context context,List<GateHistory> gateHistories, GateHistoryAdapter.OnItemClickListener  listener) {
        this.gateHistories = gateHistories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GateHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_listellipsetwo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GateHistoryAdapter.ViewHolder holder, int position) {
        holder.bind(gateHistories.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return gateHistories.size();
    }

    interface OnItemClickListener {
        void onItemClick(GateHistory gateHistory);
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        int id;

        private TextView txtThigiangi, txtThigianly, txtPlate, txtCoin, txtMaGiaoDich, txtStatus;

        public LinearLayout linearRowlinefive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtThigiangi = itemView.findViewById(R.id.txtThigiangi);
            txtThigianly = itemView.findViewById(R.id.txtThigianly);
            txtPlate = itemView.findViewById(R.id.txtPlate);
            txtCoin = itemView.findViewById(R.id.txtCoin);
            txtMaGiaoDich = itemView.findViewById(R.id.txtMaGiaoDich);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            linearRowlinefive = itemView.findViewById(R.id.linearRowlinefive); // initialize the LinearLayout view here
        }

        public void bind(GateHistory gateHistory, GateHistoryAdapter.OnItemClickListener listener) {

            txtThigiangi.setText("Thời gian gửi: " + gateHistory.getCheckInDate());
            txtThigianly.setText("Thời gian lấy: "+gateHistory.getCheckOutDate());
            txtPlate.setText("Biển số xe: " + gateHistory.getNumberPlate());
            txtCoin.setText( "Tiền: " + String.valueOf(gateHistory.getCoin()) );
            txtMaGiaoDich.setText("Mã giao dịch" + String.valueOf(gateHistory.getId()));
            txtStatus.setText("Trạng thái: Thành Công");

            id = gateHistory.getId();

            linearRowlinefive.setOnClickListener(view -> listener.onItemClick(gateHistory));
        }
    }


}
