package demo.utt37.congcau.apptracnghiem.teacher;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.activity.TestDoneActivity;
import demo.utt37.congcau.apptracnghiem.config.common.Hepper;
import demo.utt37.congcau.apptracnghiem.model.Question;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.response.ResponseMessage;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.sqlite.QuestionController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionFragment extends DialogFragment {

    Toolbar toolbar;
    View view;
    EditText edtQuestion, edtA, edtB, edtC, spnExam;
    Spinner spnResult, spnSubject, spnLevel;
    ImageView img;
    Button btnSave;
    QuestionController questionController;
    ArrayList<String> listExam, listResult, listSubject, listLevel;
    String subject = "";
    String result = "";
    String level = "";
    SOService mService;
    boolean check = false;
    QuestionModel questionModel;

    public AddQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_NoActionBar);
        Bundle bundle = getArguments();
        if (bundle != null) {
            check = bundle.getBoolean("check");
            questionModel = (QuestionModel) bundle.getSerializable("question");
        }
    }

    public static AddQuestionFragment newInstance(String content) {
        AddQuestionFragment pagerFragment = new AddQuestionFragment();
        Bundle bd = new Bundle();
        bd.putString("content", content);
        pagerFragment.setArguments(bd);
        return pagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_question, container, false);
        initToolBar();
        iniView();
        return view;
    }

    private void iniView() {

        edtQuestion = view.findViewById(R.id.edt_question);
        edtA = view.findViewById(R.id.edt_a);
        edtB = view.findViewById(R.id.edt_b);
        edtC = view.findViewById(R.id.edt_c);
        spnResult = view.findViewById(R.id.spn_result);
        spnExam = view.findViewById(R.id.spn_exam);
        spnSubject = view.findViewById(R.id.spn_subject);
        spnLevel = view.findViewById(R.id.spn_level);
        listResult = new ArrayList<>();
        listSubject = new ArrayList<>();
        listLevel = new ArrayList<>();

        listResult.add("A");
        listResult.add("B");
        listResult.add("C");

        listSubject.add("Tin học");
        listSubject.add("Tiếng anh");

        listLevel.add("1");
        listLevel.add("2");
        listLevel.add("3");

        ArrayAdapter adapterResult = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listResult);
        spnResult.setAdapter(adapterResult);

        ArrayAdapter adapterSubject = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listSubject);
        spnSubject.setAdapter(adapterSubject);

        ArrayAdapter adapterLevel = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listLevel);
        spnLevel.setAdapter(adapterLevel);

        spnResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                result = listResult.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                subject = listSubject.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                level = listLevel.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (check == true) {
            edtQuestion.setText(questionModel.getQuestion());
            edtA.setText(questionModel.getAns_a());
            edtB.setText(questionModel.getAns_b());
            edtC.setText(questionModel.getAns_c());
            spnExam.setText(questionModel.getNum_exam());
            spnResult.setSelection(listResult.indexOf(questionModel.getResult()));
            spnLevel.setSelection(listLevel.indexOf(questionModel.getLevel()));
            spnSubject.setSelection(listSubject.indexOf(questionModel.getSubject()));


        }

    }

    private void sendTOServer() {
        String question = edtQuestion.getText().toString();
        String A = edtA.getText().toString();
        String B = edtB.getText().toString();
        String C = edtC.getText().toString();
        String examCode = spnExam.getText().toString();
        if (examCode.equals("") || question.equals("")) {
            Toast.makeText(getActivity(), "Không được bỏ trống mã đề", Toast.LENGTH_SHORT).show();
        } else {
            mService = ApiUtils.getSOService();
            Map<String, Object> params = new HashMap<>();
            params.put("question", question);
            params.put("ans_a", A);
            params.put("ans_b", B);
            params.put("ans_c", C);
            params.put("result", result);
            params.put("num_exam", examCode);
            params.put("level", level);
            params.put("subject", subject);
            params.put("teacher_code", TeacherActivity.getCode());
            mService.createTest(params).enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getSuccess() == 1) {
                            Toast.makeText(getActivity(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                            ((TeacherActivity) getActivity()).loadData();
                            ((TeacherActivity) getActivity()).getData();

                            getDialog().dismiss();

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

        }
    }

    private void updateQuestion() {
        String question = edtQuestion.getText().toString();
        String A = edtA.getText().toString();
        String B = edtB.getText().toString();
        String C = edtC.getText().toString();
        String examCode = spnExam.getText().toString();
        if (examCode.equals("") || question.equals("")) {
            Toast.makeText(getActivity(), "Không được bỏ trống mã đề", Toast.LENGTH_SHORT).show();
        } else {
            mService = ApiUtils.getSOService();
            Map<String, Object> params = new HashMap<>();
            params.put("updateTest","updateTest");
            params.put("id", questionModel.getId());
            params.put("question", question);
            params.put("ans_a", A);
            params.put("ans_b", B);
            params.put("ans_c", C);
            params.put("result", result);
            params.put("num_exam", examCode);
            params.put("level", level);
            params.put("subject", subject);
            params.put("teacher_code", TeacherActivity.getCode());
            mService.updateTest(params).enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getSuccess() == 1) {
                            Toast.makeText(getActivity(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                            getDialog().dismiss();

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

        }
    }

    private void initToolBar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setSubtitle("Thêm câu hỏi");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_save) {
                    if (check == true) {
                        updateQuestion();
                    } else {
                        sendTOServer();
                    }
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_save);
        toolbar.setNavigationIcon(R.drawable.if_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    private void goBack() {
        new AlertDialog.Builder(getActivity())
                .setMessage("Bạn có muốn thoát mà không lưu")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
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
