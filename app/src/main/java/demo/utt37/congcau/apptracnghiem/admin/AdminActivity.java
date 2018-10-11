package demo.utt37.congcau.apptracnghiem.admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.model.Teacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.response.ResponseTeacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseTest;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.teacher.TeacherActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tabLayout;
    Toolbar toolbar;

    SOService mService;
    ArrayList<Student> dataStudent;
    ArrayList<Teacher> dataTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initToolBar();
        loadDataStudent();
        loadDataTeacher();
        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager manager=getSupportFragmentManager();
        PagerAdapter adapter=new PagerAdapter(manager);
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setSubtitle("Xin chào Admin");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.action_add)
                {

                    addingForm(new AddUserForm());
                }

                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_add);
        toolbar.setNavigationIcon(R.drawable.if_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    private void addingForm(DialogFragment dialogFragment){
        FragmentManager ft = getSupportFragmentManager();
        dialogFragment.show(ft,"dialog");
    }

    public void loadDataStudent() {
        dataStudent = new ArrayList<>();
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("std_code", "all");
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
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseStudent>> call, Throwable t) {

            }
        });
    }

    public void loadDataTeacher() {
        dataTeacher = new ArrayList<>();
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("tch_code", "all");
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
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseTeacher>> call, Throwable t) {

            }
        });
    }

    public ArrayList<Student> getDataStudent() {
        return dataStudent;
    }

    public ArrayList<Teacher> getDataTeacher() {
        return dataTeacher;
    }
}

