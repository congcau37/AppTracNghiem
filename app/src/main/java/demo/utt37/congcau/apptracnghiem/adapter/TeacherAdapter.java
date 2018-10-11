package demo.utt37.congcau.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.model.Student;
import demo.utt37.congcau.apptracnghiem.model.Teacher;

public class TeacherAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Teacher> data;

    public TeacherAdapter(Context context, int layout, ArrayList<Teacher> data) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            viewHolder= new ViewHolder();
            view = layoutInflater.inflate(layout, null);
            viewHolder.txtQuestion = (TextView) view.findViewById(R.id.txt_question);
            viewHolder.imgDel = (ImageView) view.findViewById(R.id.img_del);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Teacher teacher = data.get(i);
        viewHolder.txtQuestion.setText(teacher.getTchName());
        viewHolder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView txtQuestion;
        ImageView imgEdit,imgDel;
    }
}

