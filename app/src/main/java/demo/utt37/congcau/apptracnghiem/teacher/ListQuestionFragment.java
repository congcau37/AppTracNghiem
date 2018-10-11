package demo.utt37.congcau.apptracnghiem.teacher;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.adapter.QuestionAdapterTch;
import demo.utt37.congcau.apptracnghiem.adapter.TeacherAdapter;
import demo.utt37.congcau.apptracnghiem.admin.AdminActivity;
import demo.utt37.congcau.apptracnghiem.config.common.QuestionListener;
import demo.utt37.congcau.apptracnghiem.fragment.DetaiQuestionFragment;
import demo.utt37.congcau.apptracnghiem.model.Question;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.model.Teacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseTest;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListQuestionFragment extends Fragment implements QuestionListener {

    ListView lv;
    View view;
    SOService mService;
    ArrayList<QuestionModel> data;
    QuestionAdapterTch adapter;
    SwipeRefreshLayout srl;
    QuestionModel question;
    ImageView imgSearch;
    EditText edtSearch;

    public ListQuestionFragment() {
        // Required empty public constructor
    }

    public static ListQuestionFragment newInstance(String content) {
        ListQuestionFragment pagerFragment = new ListQuestionFragment();
        Bundle bd = new Bundle();
        bd.putString("content", content);
        pagerFragment.setArguments(bd);
        return pagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_ques, container, false);
        initView();
        return view;
    }

    private void initView() {
        lv = view.findViewById(R.id.lv_question);
        imgSearch = view.findViewById(R.id.imgSubject);
        edtSearch = view.findViewById(R.id.edtSearch);
        data = new ArrayList<>();
        data = ((TeacherActivity) getActivity()).getData();
        adapter = new QuestionAdapterTch(getActivity(), R.layout.custom_item_lv_question, data);
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
                }, 1500);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetaiQuestionFragment.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question", data.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
                lv.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void refresh() {
        data = new ArrayList<>();
        ((TeacherActivity) getActivity()).loadData();
        data = ((TeacherActivity) getActivity()).getData();
        adapter = new QuestionAdapterTch(getActivity(), R.layout.custom_item_lv_question, data);
        lv.setAdapter(adapter);
    }

    @Override
    public void editComponent(String id) {
        addingForm(new AddQuestionFragment(), null);
    }

    @Override
    public void deleteComponent(String id) {

    }

    private void addingForm(DialogFragment dialogFragment, QuestionModel question) {
        FragmentManager ft = getFragmentManager();
        Bundle bundle = new Bundle();
        dialogFragment = new DialogFragment();
        bundle.putSerializable("question", question);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft, "dialog");
    }

    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.questall:
                        adapter = new QuestionAdapterTch(getActivity(), R.layout.custom_item_lv_question, data);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.english:
                        ArrayList<QuestionModel> listEnglish = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getSubject().equals("Tiếng anh")) {
                                listEnglish.add(data.get(i));
                            }
                        }
                        adapter = new QuestionAdapterTch(getActivity(), R.layout.custom_item_lv_question, listEnglish);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.informatics:
                        ArrayList<QuestionModel> listInformatic = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getSubject().equals("Tin học")) {
                                listInformatic.add(data.get(i));
                            }
                        }
                        adapter = new QuestionAdapterTch(getActivity(), R.layout.custom_item_lv_question, listInformatic);
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        popupMenu.inflate(R.menu.menu_question);
        setForceShowIcon(popupMenu);
        popupMenu.show();
    }

    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}

