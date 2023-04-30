package com.example.adminapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminapplication.models.User;
import com.example.adminapplication.models.request.ChangeUserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;

public class ThayDoiThongTin extends AppCompatActivity implements ResponseView {


    TextView txtMaTK, txtSoDu, txtTen, txtCCCD;
    EditText txtDiaChi, txtSDT;
    Spinner txtStatus;
    ImageView editUser, btnBack;
    boolean isEditting = false;
    UserPresenter userPresenter;
    User user;


    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);

        btnBack = findViewById(R.id.btnBack);

        txtMaTK = findViewById(R.id.txtMaTK);
        txtSoDu = findViewById(R.id.txtSoDu);
        txtTen = findViewById(R.id.txtTen);
        txtCCCD = findViewById(R.id.txtCCCD);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtSDT = findViewById(R.id.txtSDT);
        txtStatus = findViewById(R.id.txtStatus);
        editUser = findViewById(R.id.editUser);

        fetchUser();
        setBtnEditUser();

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), ThayDoiTT.class);
            startActivity(intent);
        });
    }

    private void fetchUser() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("clicked_user");

        txtMaTK.setText(String.valueOf(user.getEmail()));
        txtSoDu.setText(user.getCoin() + " đ");
        txtTen.setText(user.getFullName());
        txtCCCD.setText(user.getPersonalNumber());
        txtSDT.setText(user.getPhoneNumber());
        txtDiaChi.setText(user.getAddress());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.active_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtStatus.setAdapter(adapter);
        txtStatus.setSelection(user.getActivate() ? 0 : 1);
        activateUsesFields(false);
    }

    void setBtnEditUser() {
        editUser.setOnClickListener(v -> {
            if (isEditting) {
                String diachi, sdt;
                boolean status;
                diachi = txtDiaChi.getText().toString();
                sdt = txtSDT.getText().toString();
                String selectedStatus = txtStatus.getSelectedItem().toString();
                status = selectedStatus.equals("Active");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Update User Confirmation");
                builder.setMessage("Are you sure you want to update user?");

                builder.setPositiveButton("Yes", (dialog, which) -> {
                    try {
                        ChangeUserRequest changeUserRequest =
                                new ChangeUserRequest(sdt, diachi, status);
                        userPresenter = new UserPresenter(this);
                        userPresenter.updateUser(user.getId(), changeUserRequest);
                    } catch (Exception e) {
                        Toast.makeText(this, "Có lỗi xảy ra " + e, Toast.LENGTH_LONG).show();
                    }

                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    return;
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            isEditting = !isEditting;
            activateUsesFields(isEditting);

        });
    }

    private void activateUsesFields(Boolean status) {
        txtSDT.setEnabled(status);
        txtDiaChi.setEnabled(status);
        txtStatus.setEnabled(status);
    }

    @Override
    public void onComplete(BaseResponse account) {
        if (account.getCode() == -1) {
            Toast.makeText(this, account.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Update successfully.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}