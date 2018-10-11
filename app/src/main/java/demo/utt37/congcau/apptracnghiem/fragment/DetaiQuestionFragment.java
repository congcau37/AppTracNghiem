package demo.utt37.congcau.apptracnghiem.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.teacher.AddQuestionFragment;
import demo.utt37.congcau.apptracnghiem.teacher.PagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetaiQuestionFragment extends AppCompatActivity {

    Toolbar toolbar;
    SOService mService;
    TextView txtQues,txtA,txtB,txtC,txtResult,txtExamCode,txtLevel,txtSubject;
    QuestionModel questionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detai_question);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        questionModel = (QuestionModel) bundle.getSerializable("question");
        initToolBar();
        iniView();

    }

    private void iniView() {
        txtA = findViewById(R.id.txt_ans_a);
        txtB = findViewById(R.id.txt_ans_b);
        txtC = findViewById(R.id.txt_ans_c);
        txtQues = findViewById(R.id.txt_question);
        txtResult = findViewById(R.id.txt_result);
        txtExamCode = findViewById(R.id.txt_exam_code);
        txtLevel = findViewById(R.id.txt_level);
        txtSubject = findViewById(R.id.txt_subject);

        txtQues.setText(questionModel.getQuestion());
        txtA.setText(questionModel.getAns_a());
        txtB.setText(questionModel.getAns_b());
        txtC.setText(questionModel.getAns_c());
        txtResult.setText(questionModel.getResult());
        txtExamCode.setText(questionModel.getNum_exam());
        txtSubject.setText(questionModel.getSubject());
        txtLevel.setText(questionModel.getLevel());

    }
    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setSubtitle("Chi tiết câu hỏi");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_edit){
                    addingForm(new AddQuestionFragment(),questionModel,true);
//                    addingForm(new AddQuestionFragment(),questionModel,true);
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_edit);
        toolbar.setNavigationIcon(R.drawable.if_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void addingForm(DialogFragment dialogFragment, QuestionModel question,boolean check){
        FragmentManager ft = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putSerializable("question",question);
        bundle.putBoolean("check",check);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft,"dialog");
    }

}

