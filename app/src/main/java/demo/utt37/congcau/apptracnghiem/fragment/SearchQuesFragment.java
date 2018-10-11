package demo.utt37.congcau.apptracnghiem.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.adapter.QuestionAdapter;
import demo.utt37.congcau.apptracnghiem.sqlite.QuestionController;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchQuesFragment extends Fragment {

    ListView lvQuestion;
    QuestionController questionController;
    QuestionAdapter adapter;
    EditText edtSearch;
    ImageButton imgSubject;
    String subject="";

    public SearchQuesFragment() {
        // Required empty public constructor
    }
    public static SearchQuesFragment newInstance() {
//     Bundle args = new Bundle();
        SearchQuesFragment fragment = new SearchQuesFragment();
//    fragment.setArguments(args);
        return fragment;
    }
    public void initControls(){
        imgSubject=(ImageButton) getActivity().findViewById(R.id.imgSubject);
        lvQuestion=(ListView) getActivity().findViewById(R.id.lvQuestion);
        edtSearch=(EditText)getActivity().findViewById(R.id.edtSearch);
        questionController =new QuestionController(getActivity());
        listCursor(questionController.getSearchQuestion(edtSearch.getText().toString()));
    }

    public void listCursor(Cursor cursor){
        adapter=new QuestionAdapter(getActivity(),cursor,true);
        lvQuestion.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tìm kiếm");
        return inflater.inflate(R.layout.fragment_search_ques, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initControls();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listCursor(questionController.getSearchQuestion(edtSearch.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imgSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

    }
    public void showMenu(View v){
        PopupMenu popupMenu= new PopupMenu(getActivity(), v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.questall:
                        subject="";
                        listCursor(questionController.getQuestionBySubject(subject));
                        break;
                    case R.id.english:
                        subject="English";
                        listCursor(questionController.getQuestionBySubject(subject));
                        break;
                    case R.id.informatics:
                        subject="Informatics";
                        listCursor(questionController.getQuestionBySubject(subject));
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
