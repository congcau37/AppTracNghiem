package demo.utt37.congcau.apptracnghiem.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.model.Score;
import demo.utt37.congcau.apptracnghiem.model.User;
import demo.utt37.congcau.apptracnghiem.response.ResponseMessage;
import demo.utt37.congcau.apptracnghiem.response.ResponseScore;
import demo.utt37.congcau.apptracnghiem.response.ResponseUser;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.teacher.TeacherActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    Toolbar toolbar;
    View view;
    String stdCode;
    TextView txtAccName;
    EditText edtAccOldPass, edtAccNewPass;
    Button btnSave, btnCacel;
    SOService mService;
    ArrayList<User> dataUser;


    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
//     Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
//    fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Đổi mật khẩu");
        stdCode = ((MainActivity) getActivity()).getCode();
        mService = ApiUtils.getSOService();
        initData();
        initView();
        return view;
    }

    private void initData() {
        dataUser = new ArrayList<>();
        getUser();
    }

    private void initView() {

        txtAccName = view.findViewById(R.id.edt_acc_name);
        edtAccOldPass = view.findViewById(R.id.edt_acc_password);
        edtAccNewPass = view.findViewById(R.id.edt_acc_new_password);
        btnSave = view.findViewById(R.id.ibt_save);
        btnCacel = view.findViewById(R.id.ibt_cancel);
        txtAccName.setText(stdCode);
        btnCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openHomeFragment();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChangeAccount();
            }
        });
    }

    private void getUser() {
        Map<String, Object> params = new HashMap<>();
        params.put("name",MainActivity.getCode());
        mService.getUser(params).enqueue(new Callback<ArrayList<ResponseUser>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseUser>> call, Response<ArrayList<ResponseUser>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseUser item = response.body().get(i);
                        User user = new User();
                        user.setId(item.getId());
                        user.setName(item.getName());
                        user.setPassword(item.getPassword());
                        dataUser.add(user);
                    }
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseUser>> call, Throwable t) {

            }
        });
    }

    private void saveChangeAccount() {
        Log.d("datassize",""+dataUser.size());
        String oldPassword = edtAccOldPass.getText().toString();
        String newPassword = edtAccNewPass.getText().toString();
        if (oldPassword.equals("")) {
            Toast.makeText(getActivity(), "Xin nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();

        } else if (oldPassword.equals(dataUser.get(0).getPassword())) {
            mService = ApiUtils.getSOService();
            Map<String, Object> params = new HashMap<>();
            params.put("updateUser", oldPassword);
            params.put("old_password", oldPassword);
            params.put("new_password", newPassword);
            params.put("name", MainActivity.getCode());
            mService.updateUser(params).enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getSuccess() == 1) {
                            Toast.makeText(getActivity(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                            ((MainActivity) getActivity()).openHomeFragment();

                        } else if (response.body().getSuccess() == 0) {
                            Toast.makeText(getActivity(), "Lỗi: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        int statusCode = response.code();
                        if (statusCode == 404) {
                            Toast.makeText(getActivity(), "Lỗi : Không thể kết nối tới máy chủ ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {

                }
            });

        }else {
            Toast.makeText(getActivity(), "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();

        }
    }

}
