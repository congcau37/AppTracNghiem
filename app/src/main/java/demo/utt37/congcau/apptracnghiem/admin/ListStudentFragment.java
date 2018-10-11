package demo.utt37.congcau.apptracnghiem.admin;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.adapter.QuestionAdapterTch;
import demo.utt37.congcau.apptracnghiem.adapter.StudentAdapter;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.teacher.TeacherActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListStudentFragment extends Fragment {

    View view;
    SOService mService;
    ListView lv;
    ArrayList<Student> data;
    StudentAdapter adapter;
    SwipeRefreshLayout srl;

    public ListStudentFragment() {
        // Required empty public constructor
    }

    public static ListStudentFragment newInstance(String content){
        ListStudentFragment pagerFragment = new ListStudentFragment();
        Bundle bd = new Bundle();
        bd.putString("content",content);
        pagerFragment.setArguments(bd);
        return pagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_list, container, false);
        initView();
        return view;
    }

    private void initView() {
        lv = view.findViewById(R.id.lv_student);
        data = new ArrayList<>();
        data = ((AdminActivity)getActivity()).getDataStudent();
        adapter = new StudentAdapter(getActivity(),R.layout.custom_item_lv_question,data);
        lv.setAdapter(adapter);


        srl = view.findViewById(R.id.swiperefresh);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                        srl.setRefreshing(false);
                    }
                },1500);
            }
        });
    }

    private void refresh(){
        data = new ArrayList<>();
        ((AdminActivity)getActivity()).loadDataStudent();
        data = ((AdminActivity)getActivity()).getDataStudent();
        adapter = new StudentAdapter(getActivity(),R.layout.custom_item_lv_question,data);
        lv.setAdapter(adapter);
    }
}
