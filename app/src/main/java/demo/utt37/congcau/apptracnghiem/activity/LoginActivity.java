package demo.utt37.congcau.apptracnghiem.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.User;
import demo.utt37.congcau.apptracnghiem.response.ResponseMessage;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.admin.AdminActivity;
import demo.utt37.congcau.apptracnghiem.teacher.TeacherActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtAccount;
    static TextInputEditText edtPassword;
    Button btnLogin, btnCancel;
    ArrayList<User> list;
    boolean check = false;
    String status = "";
    SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edtAccount = findViewById(R.id.textInputEditTextEmail);
        edtPassword = findViewById(R.id.textInputEditTextPassword);
        btnLogin = findViewById(R.id.appCompatButtonLogin);
        btnCancel = findViewById(R.id.appCompatButtonCancel);
        list = new ArrayList<>();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Offline", Toast.LENGTH_SHORT).show();
                status = "offline";
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("status", status);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAdmin();

            }
        });
    }

    private void checkAdmin() {
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("acc_user", edtAccount.getText().toString());
        params.put("acc_pwd", edtPassword.getText().toString());
        mService.login(params).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (Integer.parseInt(response.body().getType()) == 0) {
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else if (Integer.parseInt(response.body().getType()) == 1) {
                            Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("code",edtAccount.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            status = "online";
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("status", status);
                            bundle.putString("code",edtAccount.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Lỗi: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int statusCode = response.code();
                    if (statusCode == 404) {
                        Toast.makeText(LoginActivity.this, "Lỗi : Không thể kết nối tới máy chủ ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Server lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
