package demo.utt37.congcau.apptracnghiem.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.History;
import demo.utt37.congcau.apptracnghiem.response.ResponseHistory;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.adapter.HistoryAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryTestFragment extends Fragment {

    View view;
    ListView lvHistory;
    SOService mService;
    ArrayList<History> data;
    HistoryAdapter adapter;

    public static HistoryTestFragment newInstance() {
//     Bundle args = new Bundle();
        HistoryTestFragment fragment = new HistoryTestFragment();
//    fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Lịch sử làm bài");
        view = inflater.inflate(R.layout.fragment_history_test, container, false);
        initView();
        loadData();
        return view;
    }

    private void initView() {
        lvHistory = view.findViewById(R.id.lv_history);
        data = new ArrayList<>();
        adapter = new HistoryAdapter(getActivity(),R.layout.custom_item_history,data);
        lvHistory.setAdapter(adapter);
    }

    private void loadData() {
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        params.put("std_code", MainActivity.getCode());
        mService.getHistory(params).enqueue(new Callback<ArrayList<ResponseHistory>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseHistory>> call, Response<ArrayList<ResponseHistory>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseHistory item = response.body().get(i);
                        History history = new History();
                        history.setId(item.getId());
                        history.setDate(item.getDate());
                        history.setExam_code(item.getExam_code());
                        history.setLevel(item.getLevel());
                        history.setScore(item.getScore());
                        history.setStd_code(item.getStd_code());
                        history.setStd_name(item.getStd_name());
                        data.add(history);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseHistory>> call, Throwable t) {

            }
        });
    }
}
