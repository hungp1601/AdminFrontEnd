package com.example.adminapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapplication.models.Car;
import com.example.adminapplication.models.User;
import com.example.adminapplication.models.request.ChangeUserRequest;
import com.example.adminapplication.models.response.BaseResponse;
import com.example.adminapplication.presenters.UserPresenter;
import com.example.adminapplication.views.ResponseView;
import com.google.gson.internal.LinkedTreeMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ThayDoiThongTin extends AppCompatActivity implements ResponseView, PlateAdapter.OnItemClickListener  {

    private static final int GALLERY_REQUEST_CODE = 1;
    private final int REQUEST_CODE_PERMISSIONS = 10;
    private final String[] REQUIRED_PERMISSIONS =
            new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"
                    ,"android.permission.READ_EXTERNAL_STORAGE"};

    TextView txtMaTK, txtSoDu, txtTen, txtCCCD;
    EditText txtDiaChi, txtSDT;
    Spinner txtStatus;
    ImageView editUser, btnBack;
    boolean isEditting = false;
    UserPresenter userPresenter;
    RecyclerView recyclerListCar;
    List<Car> listCar;
    PlateAdapter adapter;

    User user;
    AppCompatButton btnThemXe;

    int id;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin);

        btnBack = findViewById(R.id.btnBack);
        btnThemXe = findViewById(R.id.btnThemXe);

        txtMaTK = findViewById(R.id.txtMaTK);
        txtSoDu = findViewById(R.id.txtSoDu);
        txtTen = findViewById(R.id.txtTen);
        txtCCCD = findViewById(R.id.txtCCCD);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtSDT = findViewById(R.id.txtSDT);
        txtStatus = findViewById(R.id.txtStatus);
        editUser = findViewById(R.id.editUser);
        recyclerListCar = findViewById(R.id.recyclerListcar);
        listCar = new ArrayList<>();


        fetchUser();
        fetchPlate();
        setBtnEditUser();
        requestPermission();

        btnBack.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(v.getContext(), ThayDoiTT.class);
            startActivity(intent);
        });

        btnThemXe.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //start camera when permissions have been granted otherwise exit app
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean allPermissionsGranted(){
        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
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

    void fetchPlate() {
        userPresenter = new UserPresenter(this);
        userPresenter.getPlates(user.getId());
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

    private void requestPermission(){
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", "plate.jpg", requestBody);

                userPresenter = new UserPresenter(this);
                userPresenter.registerPlate(user.getId(), imagePart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete(BaseResponse account) {
        if (account.getCode() == -1) {
            Toast.makeText(this, account.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            fetchPlate();
            Toast.makeText(getApplicationContext(), "Update successfully.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(BaseResponse account, String type) {
        if (account.getCode() == -1) {
            Toast.makeText(this, account.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            switch (type) {
                case "getPlates":
                {
                    LinkedTreeMap<String, Object> t = (LinkedTreeMap<String, Object>) account.getData();
                    List<LinkedTreeMap<String, Object>> items = (List<LinkedTreeMap<String, Object>>) t.get("items");

                    if (items != null) {
                        for(LinkedTreeMap<String, Object> obj:items) {
                            Car car = new Car(
                                Double.valueOf(String.valueOf(obj.get("Id"))).intValue(),
                                Double.valueOf(String.valueOf(obj.get("UserId"))).intValue(),
                                (String) obj.get("NumberPlate"),
                                (String) obj.get("ImagePath")
                            );

                            listCar.add(car);
                        }
                        recyclerListCar.setLayoutManager(new LinearLayoutManager(this));

                        adapter = new PlateAdapter(this, listCar,this);
                        recyclerListCar.setAdapter(adapter);
                    }
                    break;
                }
                case "anotherExample":
                    break;
                default:
                    break;
            }
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

    @Override
    public void onItemClick(Car car) {

    }
}