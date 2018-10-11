package demo.utt37.congcau.apptracnghiem.test.slide;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.config.common.Instance;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    private ArrayList<QuestionModel> arr_Ques;
    private int mPageNumber;
    private int checkAnswwer;
    private TextView txtNumber, txtQuestion;
    private RadioGroup rdg;
    private RadioButton rdbA, rdbB, rdbC, rdbD;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        txtNumber = (TextView) rootView.findViewById(R.id.tvNum);
        rdg = (RadioGroup) rootView.findViewById(R.id.radGroup);
        rdbA = (RadioButton) rootView.findViewById(R.id.radA);
        rdbB = (RadioButton) rootView.findViewById(R.id.radB);
        rdbC = (RadioButton) rootView.findViewById(R.id.radC);
        txtQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Ques = new ArrayList<QuestionModel>();
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        arr_Ques = screenSlideActivity.getData();
        mPageNumber = getArguments().getInt(Instance.KEY_PAGE);
        checkAnswwer = getArguments().getInt(Instance.ARG_CHECK_ANSWER);
    }

    public static ScreenSlidePageFragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
        Bundle bd = new Bundle();
        bd.putInt(Instance.KEY_PAGE, pageNumber);
        bd.putInt(Instance.ARG_CHECK_ANSWER, checkAnswer);
        screenSlidePageFragment.setArguments(bd);
        return screenSlidePageFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNumber.setText("Câu:" + (mPageNumber + 1));
        txtQuestion.setText(arr_Ques.get(mPageNumber).getQuestion());
        Log.d("question", arr_Ques.get(mPageNumber).getQuestion());
        rdbA.setText(getItem(mPageNumber).getAns_a());
        rdbB.setText(getItem(mPageNumber).getAns_b());
        rdbC.setText(getItem(mPageNumber).getAns_c());
//        rdbD.setText(arr_Ques.get(mPageNumber).getAns_d());

        if (checkAnswwer != 0) {
            rdbA.setClickable(false);
            rdbB.setClickable(false);
            rdbC.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }

        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                Toast.makeText(getActivity(), "đây là rda " + i, Toast.LENGTH_SHORT).show();
                getItem(mPageNumber).choiceID = i;
                getItem(mPageNumber).setAnswer(getChoiceFromID(i));

            }
        });

    }

    private QuestionModel getItem(int i) {
        return arr_Ques.get(i);
    }

    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        }
//        else if (ID == R.id.radD) {
//            return "D";
//        }
        else
            return "";
    }

    private void getCheckAns(String ans) {
        if (ans.equals("A") == true) {

            rdbA.setBackgroundColor(Color.RED);
        } else if (ans.equals("B") == true) {
            rdbB.setBackgroundColor(Color.RED);
        }
        else if (ans.equals("C") == true) {
            rdbC.setBackgroundColor(Color.RED);
        } else ;
    }
}
