package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapplication.models.GateHistory;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.GateHistoryPresenter;
import com.example.adminapplication.views.ResponseView;
import com.google.gson.internal.LinkedTreeMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LichSuXe extends AppCompatActivity implements ResponseView, GateHistoryAdapter.OnItemClickListener{

    EditText txtStartDate, txtEndDate;
    RecyclerView recyclerListGate;
    GateHistoryPresenter gateHistoryPresenter;
    List<GateHistory> gateHistories;
    GateHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_xe);
        txtStartDate = (EditText) findViewById(R.id.txtStartDate);
        txtEndDate = (EditText) findViewById(R.id.txtEndDate);
        recyclerListGate = findViewById(R.id.recyclerGateHistory);
        gateHistories = new ArrayList<>();

        getListGateHistory();



//        txtStartDate.setOnClickListener(v->{
//            selectDate(txtStartDate);
//        });
//
//        txtEndDate.setOnClickListener(v->{
//            selectDate(txtEndDate);
//        });


    }

    private void selectDate(EditText editText){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();
    }

    public void getListGateHistory(){
        try {
            gateHistoryPresenter = new GateHistoryPresenter(this);
            gateHistoryPresenter.getAllGateHistory();
        }
        catch(Exception e) {
            System.out.print(e);
            Toast.makeText(this, "Có lỗi xảy ra " + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onComplete(BaseResponse allGateHistory) {
        if(allGateHistory.getCode() == -1 ){
            Toast.makeText(this, allGateHistory.getMessage(),Toast.LENGTH_LONG).show();
        }
        else {
            LinkedTreeMap<String, Object> t = (LinkedTreeMap<String, Object>) allGateHistory.getData();


            List<LinkedTreeMap<String, Object>> items = (List<LinkedTreeMap<String, Object>>) t.get("items");

            if (items != null) {
                for(LinkedTreeMap<String, Object> obj:items) {
                    GateHistory gateHistory = new GateHistory(
                            Double.valueOf(String.valueOf(obj.get("Id"))).intValue(),
                            Double.valueOf(String.valueOf(obj.get("UserId"))).intValue(),
                            (String) obj.get("NumberPlate"),
                            (String) obj.get("CheckInDate"),
                            (String) obj.get("CheckOutDate"),
                            (String) obj.get("ImagePathCheckIn"),
                            (String) obj.get("ImagePathCheckOut"),
                            Float.valueOf(String.valueOf(obj.get("Coin"))).floatValue()
                    );

                    gateHistories.add(gateHistory);
                }
                recyclerListGate.setLayoutManager(new LinearLayoutManager(this));
                System.out.println(gateHistories);
                adapter = new GateHistoryAdapter(this, gateHistories, this);
                recyclerListGate.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onComplete(BaseResponse account, String type) {

    }

    @Override
    public void onError(String message) {
        System.out.print("---------------------------------------");
        System.out.print(message);

    }

    @Override
    public void onItemClick(GateHistory gateHistory) {

    }
}