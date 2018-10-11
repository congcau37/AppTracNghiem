package demo.utt37.congcau.apptracnghiem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.fragment.AccountFragment;
import demo.utt37.congcau.apptracnghiem.fragment.EnglishFragment;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.fragment.ReviewFragment;
import demo.utt37.congcau.apptracnghiem.fragment.ContactStudentFragment;
import demo.utt37.congcau.apptracnghiem.fragment.HistoryTestFragment;
import demo.utt37.congcau.apptracnghiem.fragment.InformaticsFragment;
import demo.utt37.congcau.apptracnghiem.fragment.ScoreFragment;
import demo.utt37.congcau.apptracnghiem.fragment.HomeFragment;
import demo.utt37.congcau.apptracnghiem.fragment.SearchQuesFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String status = "";
    SOService mService;
    ArrayList<Student> dataStudent;
    static String Code = "";
    static String name = "";
    static String subject = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        status = bundle.getString("status");
        Code = bundle.getString("code");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(status.equals("offline")){
            toggleVisibility(navigationView.getMenu(), R.id.nav_Informatics, false);
            toggleVisibility(navigationView.getMenu(), R.id.nav_english, false);
            toggleVisibility(navigationView.getMenu(), R.id.nav_search, false);
            toggleVisibility(navigationView.getMenu(), R.id.nav_score, false);
            toggleVisibility(navigationView.getMenu(), R.id.nav_information, false);
            toggleVisibility(navigationView.getMenu(), R.id.nav_history, false);
            toggleVisibility(navigationView.getMenu(), R.id.nav_account, false);
        }else {
            dataStudent = new ArrayList<>();
            loadDataStudent();
        }
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Logout) {
            onBackPressed();
            LoginActivity.edtPassword.setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
         if (id == R.id.nav_Informatics) {
            openInformaticsFragment();
        } else if (id == R.id.nav_english) {
            openEnglishFragment();
        } else if (id == R.id.nav_home) {
            openHomeFragment();
        } else if (id == R.id.nav_score) {
            openScoreFragment();
        } else if (id == R.id.nav_search) {
            openSearchFragment();
        }else if (id == R.id.navStruct) {
             openContructFragment();
         }else if (id == R.id.nav_history) {
             openHistoryTestFragment();
         }else if (id == R.id.nav_information) {
             openContactStudentFragment();
         }else if (id == R.id.nav_account) {
             openAccountFragment();
         }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void nextFragment(int id, android.support.v4.app.Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(id, fragment).commit();
    }

    public void openInformaticsFragment() {
        subject = "Tin học";
        nextFragment(R.id.content_layout, InformaticsFragment.newInstance());
    }

        public void openEnglishFragment() {
        subject = "Tiếng anh";
        nextFragment(R.id.content_layout, EnglishFragment.newInstance());
    }

    public void openHomeFragment() {
        nextFragment(R.id.content_layout, HomeFragment.newInstance());
    }
    public void openScoreFragment() {
        nextFragment(R.id.content_layout, ScoreFragment.newInstance());
    }
    public void openContructFragment() {
        nextFragment(R.id.content_layout, ReviewFragment.newInstance());
    }

    public void openHistoryTestFragment() {
        nextFragment(R.id.content_layout, HistoryTestFragment.newInstance());
    }

    public void openContactStudentFragment() {
        nextFragment(R.id.content_layout, ContactStudentFragment.newInstance());
    }

    public void openSearchFragment() {
        nextFragment(R.id.content_layout, SearchQuesFragment.newInstance());
    }
    public void openAccountFragment() {
        nextFragment(R.id.content_layout, AccountFragment.newInstance());
    }
    private void toggleVisibility(Menu menu, @IdRes int id, boolean visible){
        menu.findItem(id).setVisible(visible);
    }

    private void loadDataStudent(){
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("std_code",Code);
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
                        dataStudent.add(student);
                        Log.d("dataStudent",""+dataStudent.size());
                    }
                    name = dataStudent.get(0).getStdName();
                    getSupportActionBar().setTitle("Sinh viên "+name);
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseStudent>> call, Throwable t) {

            }
        });
    }

    public static String getCode() {
        return Code;
    }

    public static String getName(){
        return name;
    }


}
