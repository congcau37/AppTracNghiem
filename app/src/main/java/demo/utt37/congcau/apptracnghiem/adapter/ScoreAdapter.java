package demo.utt37.congcau.apptracnghiem.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.Score;

public class ScoreAdapter extends BaseAdapter {

private ArrayList<Score> data;
private Context context;
LayoutInflater inflater;

    public ScoreAdapter(ArrayList<Score> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = inflater.inflate(R.layout.custom_items_point_list,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.txtName = view.findViewById(R.id.txt_name);
            viewHolder.txtCode = view.findViewById(R.id.txt_score);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Score score = data.get(i);
        viewHolder.txtName.setText(score.getStdName());
        viewHolder.txtCode.setText(score.getScore()+" Đ");
        return view;
    }

    public class ViewHolder{
        TextView txtName,txtCode;
    }
}
