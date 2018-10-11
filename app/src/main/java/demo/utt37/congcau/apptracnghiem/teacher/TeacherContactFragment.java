package demo.utt37.congcau.apptracnghiem.teacher;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.model.Teacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.response.ResponseTeacher;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherContactFragment extends DialogFragment {

    View view;
    TextView txtName,txtCode,txtPhone,txtGender,txtBirthday,txtAdd;
    SOService mService;
    ArrayList<Teacher> dataTeacher;
    
    public TeacherContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_teacher_contact, container, false);
        initView();
        loaddataTeacher();
        return view;
    }
    private void initView() {
        txtName = view.findViewById(R.id.txt_tch_name);
        txtCode = view.findViewById(R.id.txt_tch_code);
        txtPhone = view.findViewById(R.id.txt_tch_phone);
        txtBirthday = view.findViewById(R.id.txt_tch_birthday);
        txtAdd = view.findViewById(R.id.txt_tch_address);
        txtGender = view.findViewById(R.id.txt_tch_gender);
        dataTeacher = new ArrayList<>();

    }
    private void initData(){
        txtName.setText(dataTeacher.get(0).getTchName());
        txtAdd.setText(dataTeacher.get(0).getTchAdd());
        txtBirthday.setText(dataTeacher.get(0).getTchBirthday());
        txtPhone.setText(dataTeacher.get(0).getTchPhone());
        txtCode.setText(dataTeacher.get(0).getTchCode());
        txtGender.setText(dataTeacher.get(0).getTchGender());
    }

    private void loaddataTeacher() {
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("tch_code", TeacherActivity.getCode());
        mService.getTeacher(params).enqueue(new Callback<ArrayList<ResponseTeacher>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseTeacher>> call, Response<ArrayList<ResponseTeacher>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseTeacher item = response.body().get(i);
                        Teacher teacher = new Teacher();
                        teacher.setId(item.getId());
                        teacher.setTchName(item.getTchName());
                        teacher.setTchPhone(item.getTchPhone());
                        teacher.setTchCode(item.getTchCode());
                        teacher.setTchBirthday(item.getTchBirthday());
                        teacher.setTchAdd(item.getTchAdd());
                        teacher.setTchGender(item.getTchGender());
                        dataTeacher.add(teacher);
                    }
                    initData();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseTeacher>> call, Throwable t) {

                Toast.makeText(getActivity(),"lỗi",Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void refresh(){
//        if(getParentFragment() instanceof TeacherContactFragment){
//            ((TeacherActivity) getActivity()).loadData();
//        }
//    }
}
