package demo.utt37.congcau.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.ExamModel;

/**
 * Created by cong on 1/29/2018.
 */

public class ExamAdapter extends BaseAdapter{
private Context context;
private int layout;
private ArrayList<ExamModel> examModelArrayList ;

    public ExamAdapter(Context context, int layout, ArrayList<ExamModel> examModelArrayList) {
        this.context = context;
        this.layout = layout;
        this.examModelArrayList = examModelArrayList;
    }

    @Override
    public int getCount() {
        return examModelArrayList.size();
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
        if (view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.txt_exam = (TextView) view.findViewById(R.id.txt_exam);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ExamModel examModel = examModelArrayList.get(i);
        viewHolder.txt_exam.setText("Đề "+examModel.getNameExam());
        return view;
    }
    public class ViewHolder{
        private TextView txt_exam;
    }
}
