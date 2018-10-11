package demo.utt37.congcau.apptracnghiem.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.config.common.Hepper;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.response.ResponseMessage;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.sqlite.ScoreController;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestDoneActivity extends AppCompatActivity {

    private TextView txtTrue, txtFail, txtNoAns, txtTotalPoint;
    private Button btnAgain, btnExit, btnSave;
    private ArrayList<QuestionModel> arr = new ArrayList<QuestionModel>();
    private int numTrue = 0, numFail = 0, numNoAns = 0, totalPoint = 0;
    private ScoreController scoreController;
    private TextView txtname;
    TextView txtExamCode, txtLevel ,txtstdCode,txtSubject;
    SOService mService;
    ArrayList<Student> dataStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
        loadDataStudent();
        initControls();
        initEvent();


    }

    private void initEvent() {
        final Intent intent = getIntent();
        arr = (ArrayList<QuestionModel>) intent.getExtras().getSerializable("questionModelArrayList");
        showPoint();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setMessage("Bạn có muốn thoát mà không lưu điểm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater layoutInflater = TestDoneActivity.this.getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.alert_dialog_saver_point, null);
                builder.setView(view1);
                txtname = (TextView) view1.findViewById(R.id.txt_name);
                txtExamCode = (TextView) view1.findViewById(R.id.txt_exam_code);
                txtLevel = (TextView) view1.findViewById(R.id.txt_level);
                txtstdCode = (TextView) view1.findViewById(R.id.txt_student_code);
                txtSubject = (TextView) view1.findViewById(R.id.txt_subject);
                TextView txtPoint = (TextView) view1.findViewById(R.id.txt_point);
                final int numTotal = numTrue * 10;
                txtPoint.setText(numTotal + "");
                txtLevel.setText(arr.get(0).getLevel());
                txtExamCode.setText(arr.get(0).getNum_exam() + "");
                txtname.setText(dataStudent.get(0).getStdName());
                txtstdCode.setText(dataStudent.get(0).getStdCode());
                txtSubject.setText(MainActivity.subject);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveTest();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }

        });

    }

    private void initControls() {
        txtTrue = (TextView) findViewById(R.id.txt_true);
        txtFail = (TextView) findViewById(R.id.txt_fail);
        txtNoAns = (TextView) findViewById(R.id.txt_unanswered);
        txtTotalPoint = (TextView) findViewById(R.id.txt_total_point);
//        btnAgain = (Button) findViewById(R.id.btn_again);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnSave = (Button) findViewById(R.id.btn_save_point);
        dataStudent = new ArrayList<>();
    }

    private void showPoint() {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getAnswer().equals("") == true) {
                numNoAns++;
            } else if (arr.get(i).getResult().equals(arr.get(i).getAnswer()) == true) {
                numTrue++;
            } else {
                numFail++;
            }
        }
        txtNoAns.setText("" + numNoAns);
        txtTrue.setText("" + numTrue);
        txtFail.setText("" + numFail);
        totalPoint = numTrue*10;
        txtTotalPoint.setText("" + (totalPoint));
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

    private void saveTest() {
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("std_name", dataStudent.get(0).getStdName());
        params.put("exam_code", arr.get(0).getNum_exam());
        params.put("level", arr.get(0).getLevel());
        params.put("date", Hepper.setDate());
        params.put("score", totalPoint);
        params.put("std_code", dataStudent.get(0).getStdCode());
        params.put("subject",MainActivity.subject);
        mService.saveTest(params).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
//                        Toast.makeText(TestDoneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(TestDoneActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(TestDoneActivity.this, "Lỗi: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int statusCode = response.code();
                    if (statusCode == 404) {
                        Toast.makeText(TestDoneActivity.this, "Lỗi : Không thể kết nối tới máy chủ ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TestDoneActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {

            }
        });

    }

}
