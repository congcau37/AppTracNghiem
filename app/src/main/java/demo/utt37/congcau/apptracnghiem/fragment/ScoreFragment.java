package demo.utt37.congcau.apptracnghiem.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.History;
import demo.utt37.congcau.apptracnghiem.model.Score;
import demo.utt37.congcau.apptracnghiem.response.ResponseHistory;
import demo.utt37.congcau.apptracnghiem.response.ResponseScore;
import demo.utt37.congcau.apptracnghiem.service.ApiUtils;
import demo.utt37.congcau.apptracnghiem.service.SOService;
import demo.utt37.congcau.apptracnghiem.sqlite.ScoreController;
import demo.utt37.congcau.apptracnghiem.activity.MainActivity;
import demo.utt37.congcau.apptracnghiem.adapter.ScoreAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

private ListView lvScore;
private ScoreAdapter adapter;
private ArrayList<Score> data;
private SOService mService;


    public ScoreFragment() {
        // Required empty public constructor
    }
    public static ScoreFragment newInstance() {
//     Bundle args = new Bundle();
        ScoreFragment fragment = new ScoreFragment();
//    fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Điểm cao");
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls(view);
        loadData();
        initEvent();
    }

    private void initEvent() {
        data = new ArrayList<>();
        adapter = new ScoreAdapter(data,getActivity());
        lvScore.setAdapter(adapter);
    }

    private void initControls(View view) {

        lvScore = (ListView) view.findViewById(R.id.lv_score);

    }

    private void loadData() {
        mService = ApiUtils.getSOService();
        Map<String, Object> params = new HashMap<>();
        mService.getScore(params).enqueue(new Callback<ArrayList<ResponseScore>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseScore>> call, Response<ArrayList<ResponseScore>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        ResponseScore item = response.body().get(i);
                        Score score = new Score();
                        score.setId(Integer.parseInt(item.getId()));
                        score.setStdName(item.getStd_name());
                        score.setStdCode(item.getStd_code());
                        score.setScore(Integer.parseInt(item.getScore()));
                        data.add(score);
                    }
                    highScore();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseScore>> call, Throwable t) {

            }
        });
    }

    private void highScore(){
        Score point;
//        for (int i =0;i<data.size()-1;i++){
//            for (int j = i+1;j<data.size();j++){
//                if(data.get(i).getStdCode().equals(data.get(j).getStdCode())){
//                    if ((data.get(i).getScore()) < (data.get(j).getScore())){
//                        data.remove(j);
//                    }else {
//                        data.remove(i);
//                    }
//                }
//            }
//        }
        for (int i =0;i<data.size()-1;i++){
            for (int j = i+1;j<data.size();j++){
                if ((data.get(i).getScore()) < (data.get(j).getScore())){
                    point = data.get(i);
                    data.set(i,data.get(j));
                    data.set(j,point);
                }
                if(data.get(i).getStdCode().equals(data.get(j).getStdCode())){
                    if ((data.get(i).getScore()) > (data.get(j).getScore())){
                        data.remove(j);
                    }else {
                        data.remove(i);
                    }
                }

            }
        }
       adapter.notifyDataSetChanged();
    }

}
