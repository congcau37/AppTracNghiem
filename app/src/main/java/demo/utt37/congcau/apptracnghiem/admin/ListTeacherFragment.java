package demo.utt37.congcau.apptracnghiem.admin;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.adapter.StudentAdapter;
import demo.utt37.congcau.apptracnghiem.adapter.TeacherAdapter;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.model.Teacher;
import demo.utt37.congcau.apptracnghiem.service.SOService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListTeacherFragment extends DialogFragment {

    View view;
    SOService mService;
    ListView lv;
    ArrayList<Teacher> data;
    TeacherAdapter adapter;
    SwipeRefreshLayout srl;

    public ListTeacherFragment() {
        // Required empty public constructor
    }
    public static ListTeacherFragment newInstance(String content){
        ListTeacherFragment pagerFragment = new ListTeacherFragment();
        Bundle bd = new Bundle();
        bd.putString("content",content);
        pagerFragment.setArguments(bd);
        return pagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_teacher_list, container, false);
        initView();
        return view;
    }

    private void initView() {
        lv = view.findViewById(R.id.lv_teacher);
        data = new ArrayList<>();
        data = ((AdminActivity)getActivity()).getDataTeacher();
        adapter = new TeacherAdapter(getActivity(),R.layout.custom_item_lv_question,data);
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addingForm(new DetailTeacherFragment());
            }
        });
    }
    private void refresh(){
        data = new ArrayList<>();
        ((AdminActivity)getActivity()).loadDataTeacher();
        data = ((AdminActivity)getActivity()).getDataTeacher();
        adapter = new TeacherAdapter(getActivity(),R.layout.custom_item_lv_question,data);
        lv.setAdapter(adapter);
    }
    private void addingForm(DialogFragment dialogFragment){

        FragmentManager ft = getChildFragmentManager();
        dialogFragment = new DialogFragment();
        dialogFragment.show(ft,"dialog");
    }

}
