package demo.utt37.congcau.apptracnghiem.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactStudentFragment extends Fragment {

    View view;
    TextView txtName,txtCode,txtPhone,txtClass,txtBirthday,txtAdd,txtGender;
    SOService mService;
    ArrayList<Student> dataStudent;


    public static ContactStudentFragment newInstance() {
//     Bundle args = new Bundle();
        ContactStudentFragment fragment = new ContactStudentFragment();
//    fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Thông tin sinh viên");
        view = inflater.inflate(R.layout.fragment_contact_student, container, false);
        initView();
        loadDataStudent();
        return view;
    }

    private void initView() {
        txtName = view.findViewById(R.id.txt_std_name);
        txtCode = view.findViewById(R.id.txt_std_code);
        txtPhone = view.findViewById(R.id.txt_std_phone);
        txtBirthday = view.findViewById(R.id.txt_std_birthday);
        txtClass = view.findViewById(R.id.txt_std_class);
        txtAdd = view.findViewById(R.id.txt_std_address);
        txtGender = view.findViewById(R.id.txt_std_gender);
        dataStudent = new ArrayList<>();

    }
    private void initData(){
        txtName.setText(dataStudent.get(0).getStdName());
        txtAdd.setText(dataStudent.get(0).getStd_add());
        txtClass.setText(dataStudent.get(0).getStdClass());
        txtBirthday.setText(dataStudent.get(0).getStdBirthday());
        txtPhone.setText(dataStudent.get(0).getStdPhone());
        txtCode.setText(dataStudent.get(0).getStdCode());
        txtGender.setText(dataStudent.get(0).getStdGender());
    }

    private void loadDataStudent() {
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("std_code", MainActivity.getCode());
        mService.getStudent(params).enqueue(new Callback<ArrayList<ResponseStudent>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseStudent>> call, Response<ArrayList<ResponseStudent>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseStudent item = response.body().get(i);
                        Student student = new Student();
                        student.setId(item.getId());
                        student.setStdName(item.getStdName());
                        student.setStdCode(item.getStdCode());
                        student.setStd_add(item.getStd_add());
                        student.setStdBirthday(item.getStdBirthday());
                        student.setStdClass(item.getStdClass());
                        student.setStdPhone(item.getStdPhone());
                        student.setStdGender(item.getStdGender());
                        dataStudent.add(student);
                    }
                    initData();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseStudent>> call, Throwable t) {

            }
        });
    }

}
