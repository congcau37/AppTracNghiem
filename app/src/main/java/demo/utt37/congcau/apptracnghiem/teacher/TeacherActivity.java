package demo.utt37.congcau.apptracnghiem.teacher;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.model.Teacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.response.ResponseTeacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseTest;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tabLayout;
    Toolbar toolbar;
    static String Code ="";
    SOService mService;
    ArrayList<Teacher> dataTeacher;
    ArrayList<QuestionModel> data;
    PagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        initView();
        initToolBar();
        loadDataTeacher();
        loadData();
        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        dataTeacher = new ArrayList<>();
        FragmentManager manager=getSupportFragmentManager();
        adapter=new PagerAdapter(manager);
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void initView(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Code = bundle.getString("code");
        data = new ArrayList<>();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.action_add)
                {
                   addingForm();
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

    private void loadDataTeacher(){
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("tch_code",Code);
        mService.getTeacher(params).enqueue(new Callback<ArrayList<ResponseTeacher>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseTeacher>> call, Response<ArrayList<ResponseTeacher>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseTeacher item = response.body().get(i);
                        Teacher teacher = new Teacher();
                        teacher.setId(item.getId());
                        teacher.setTchAdd(item.getTchAdd());
                        teacher.setTchBirthday(item.getTchBirthday());
                        teacher.setTchCode(item.getTchCode());
                        teacher.setTchPhone(item.getTchPhone());
                        teacher.setTchName(item.getTchName());
                        dataTeacher.add(teacher);
                    }
                    toolbar.setSubtitle("Giảng viên "+dataTeacher.get(0).getTchName());
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseTeacher>> call, Throwable t) {

            }
        });
    }
    public ArrayList<QuestionModel> loadData() {
        data = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("tch_code",Code);
        mService.getQuestion(params).enqueue(new Callback<ArrayList<ResponseTest>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseTest>> call, Response<ArrayList<ResponseTest>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseTest item = response.body().get(i);
                        QuestionModel questionModel = new QuestionModel();
                        questionModel.setId(item.getId());
                        questionModel.setQuestion(item.getQuestion());
                        questionModel.setAns_a(item.getAns_a());
                        questionModel.setAns_b(item.getAns_b());
                        questionModel.setAns_c(item.getAns_c());
                        questionModel.setLevel(item.getLevel());
                        questionModel.setNum_exam(item.getNum_exam());
                        questionModel.setResult(item.getResult());
                        questionModel.setTeacherCode(item.getteacher_code());
                        questionModel.setSubject(item.getSubject());
                        data.add(questionModel);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseTest>> call, Throwable t) {

            }
        });
        return data;
    }

    public static String getCode() {
        return Code;
    }

    public ArrayList<QuestionModel> getData() {
        return data;
    }
    private void addingForm(){
        FragmentManager ft = getSupportFragmentManager();
        AddQuestionFragment dialogFragment = new AddQuestionFragment();
        dialogFragment.show(ft,"dialog");
    }
    public void goBack() {
        new AlertDialog.Builder(TeacherActivity.this)
                .setMessage("Bạn có muốn thoát mà không lưu")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}

