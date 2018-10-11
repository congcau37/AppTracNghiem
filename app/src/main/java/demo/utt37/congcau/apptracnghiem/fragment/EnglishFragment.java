package demo.utt37.congcau.apptracnghiem.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.ExamModel;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.adapter.ExamAdapter;
import demo.utt37.congcau.apptracnghiem.response.ResponseTest;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.test.slide.ScreenSlideActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment {
    private ExamAdapter examAdapter;
    private ArrayList<ExamModel> examModelArrayList;
    //    private ArrayList<QuestionModel> questionList;
    private GridView grv;
    //    private QuestionController questionController;
    private ArrayList<QuestionModel> listQuestion;
    private RadioGroup rdgLevel;
    private RadioButton rdbLV1, rdbLv2, rdbLV3;

    SOService mService;
    ArrayList<QuestionModel> data;


    public EnglishFragment() {
        // Required empty public constructor
    }

    public static EnglishFragment newInstance() {
//     Bundle args = new Bundle();
        EnglishFragment fragment = new EnglishFragment();
//    fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tiếng Anh");
        return inflater.inflate(R.layout.fragment_english, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mService = ApiUtils.getSOService();
        initControls(view);
        initEvents();

    }

    private void initControls(View view) {
        grv = (GridView) view.findViewById(R.id.grv_english);
        rdgLevel = view.findViewById(R.id.rdg_level);
        rdbLV1 = view.findViewById(R.id.rdb_lv_1);
        rdbLv2 = view.findViewById(R.id.rdb_lv_2);
        rdbLV3 = view.findViewById(R.id.rdb_lv_3);
    }

    private void initEvents() {
        loadData();
        examModelArrayList = new ArrayList<>();
        data = new ArrayList<>();
        examAdapter = new ExamAdapter(getActivity(), R.layout.custom_gridview, examModelArrayList);
        grv.setAdapter(examAdapter);
        listQuestion = new ArrayList<>();
        grv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                Bundle bundle = new Bundle();
                String numExam = examModelArrayList.get(i).getNameExam();
                int page = getQuestion(numExam).size();
                listQuestion = getQuestion(numExam);
                bundle.putParcelableArrayList("question",listQuestion);
                bundle.putInt("num_page", page);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        rdgLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdb_lv_1:
                        examModelArrayList.clear();
                        getExam("1");
                        break;
                    case R.id.rdb_lv_2:
                        examModelArrayList.clear();
                        getExam("2");
                        break;
                    case R.id.rdb_lv_3:
                        examModelArrayList.clear();
                        getExam("3");
                        break;
                }
            }
        });
    }

    private void loadData() {
        Map<String, Object> params = new HashMap<>();
        params.put("subject", "Tiếng anh");
        mService.getExam(params).enqueue(new Callback<ArrayList<ResponseTest>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseTest>> call, Response<ArrayList<ResponseTest>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseTest item = response.body().get(i);
                        QuestionModel questionModel = new QuestionModel();
//                        questionModel.setId(item.getQuestion());
                        questionModel.setQuestion(item.getQuestion());
                        questionModel.setAns_a(item.getAns_a());
                        questionModel.setAns_b(item.getAns_b());
                        questionModel.setAns_c(item.getAns_c());
                        questionModel.setLevel(item.getLevel());
                        questionModel.setNum_exam(item.getNum_exam());
                        questionModel.setResult(item.getResult());
                        questionModel.setTeacherCode(item.getteacher_code());
                        questionModel.setSubject(item.getSubject());
                        questionModel.setAnswer("");
                        data.add(questionModel);
                    }
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseTest>> call, Throwable t) {

            }
        });
    }

    private void getExam(String level) {
        for (int j = 0; j < data.size(); j++) {
            if (Integer.parseInt(data.get(j).getLevel()) == Integer.parseInt(level)) {
                examModelArrayList.add(new ExamModel(String.valueOf(data.get(j).getNum_exam())));
            }
        }
        Log.d("datasize",""+examModelArrayList.size());
        for (int k = 0; k < examModelArrayList.size()-1; k++) {
            for (int m = k+1; m < examModelArrayList.size(); m++) {
                if (examModelArrayList.get(k).getNameExam().toString().equals(examModelArrayList.get(m).getNameExam().toString())) {
                    examModelArrayList.remove(m);
                }
            }
        }
        Log.d("datasize",""+examModelArrayList.size());
        examAdapter.notifyDataSetChanged();
    }

    private ArrayList<QuestionModel> getQuestion(String numExam){
        listQuestion.clear();
        for (int i = 0 ; i< data.size(); i++){
            if(String.valueOf(data.get(i).getNum_exam()).equals(numExam)){
                listQuestion.add(data.get(i));
            }
        }
        return listQuestion;
    }
}
