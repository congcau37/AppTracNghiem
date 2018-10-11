package demo.utt37.congcau.apptracnghiem.admin;


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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.response.ResponseMessage;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.teacher.TeacherActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserForm extends DialogFragment {


    Toolbar toolbar;
    View view;
    EditText edtName,edtCode,edtPhone,edtClass,edtBirthday,edtAdd;
    RadioGroup rdgType,rdgGender;
    RadioButton rdbStudent,rdbTeacher,rdbMale,rdbFemale;
    TextView txtClass;

    SOService mService;
    String gender = "",type="";
    public AddUserForm() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_NoActionBar);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_user_form, container, false);
        initToolBar();
        initView();
        return view;
    }

    private void initView() {

        edtAdd = view.findViewById(R.id.edt_tch_address);
        edtBirthday = view.findViewById(R.id.edt_tch_birthday);
        edtCode = view.findViewById(R.id.edt_tch_code);
        edtPhone = view.findViewById(R.id.edt_tch_phone);
        edtName = view.findViewById(R.id.edt_tch_name);
        rdgType = view.findViewById(R.id.rdg_type);
        rdbStudent = view.findViewById(R.id.rdb_student);
        rdbTeacher = view.findViewById(R.id.rdb_teacher);
        txtClass = view.findViewById(R.id.txt_tch_class);
        edtClass = view.findViewById(R.id.edt_tch_class);
        rdgGender = view.findViewById(R.id.rdg_gender);
        rdbFemale = view.findViewById(R.id.rdb_female);
        rdbMale = view.findViewById(R.id.rdb_male);

        rdgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdb_student:
                        txtClass.setVisibility(View.VISIBLE);
                        edtClass.setVisibility(View.VISIBLE);
                        type = "2";
                        break;
                    case R.id.rdb_teacher:
                        txtClass.setVisibility(View.GONE);
                        edtClass.setVisibility(View.GONE);
                        type = "1";
                        break;
                }
            }
        });

        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdb_male:

                        gender = "nam";
                        break;
                    case R.id.rdb_female:

                        gender= "nữ";
                        break;
                }
            }
        });
    }

    private void initToolBar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setSubtitle("Thêm mới");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.action_save)
                {

                    sendTOServer();
                }

                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_save);
        toolbar.setNavigationIcon(R.drawable.if_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void sendTOServer() {
        String name = edtName.getText().toString();
        String code = edtCode.getText().toString();
        String stdClass = edtClass.getText().toString();
        String phone = edtPhone.getText().toString();
        String add = edtAdd.getText().toString();
        String birthday = edtBirthday.getText().toString();

        if (name.equals("")||code.equals("")||type.equals("")) {
            Toast.makeText(getActivity(), "Không được bỏ trống tên, mã và chức vụ ", Toast.LENGTH_SHORT).show();
        } else {
            mService = ApiUtils.getSOService();
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            params.put("type", type);
            params.put("name", name);
            params.put("birthday", birthday);
            params.put("phone", phone);
            params.put("add", add);
            params.put("gender", gender);
            if(type.equals("2")){
                params.put("class", stdClass);
            }
            mService.createUser(params).enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getSuccess() == 1) {
                            Toast.makeText(getActivity(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                            getDialog().dismiss();

                        } else {
                            Toast.makeText(getActivity(), "Lỗi: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

        }
    }

}
