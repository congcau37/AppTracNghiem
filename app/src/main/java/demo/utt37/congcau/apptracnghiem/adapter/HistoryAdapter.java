package demo.utt37.congcau.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.History;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<History> data;

    public HistoryAdapter(Context context, int layout, ArrayList<History> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            viewHolder= new ViewHolder();
            view = layoutInflater.inflate(layout, null);
            viewHolder.txtDate = (TextView) view.findViewById(R.id.txt_date);
            viewHolder.txtScore = (TextView) view.findViewById(R.id.txt_score);
            viewHolder.txtLevel = (TextView) view.findViewById(R.id.txt_level);
            viewHolder.txtExamCode = (TextView) view.findViewById(R.id.txt_exam_code);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        History history = data.get(i);
        viewHolder.txtDate.setText("Thời gian" + "\n"+history.getDate());
        viewHolder.txtScore.setText("Điểm "+history.getScore());
        viewHolder.txtLevel.setText("Cấp độ "+history.getLevel());
        viewHolder.txtExamCode.setText("Mã đề "+history.getExam_code());
        return view;
    }

    public class ViewHolder {
        TextView txtDate, txtScore,txtLevel,txtExamCode;
    }
}

