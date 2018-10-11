package demo.utt37.congcau.apptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import demo.utt37.congcau.apptracnghiem.R;
import demo.utt37.congcau.apptracnghiem.config.common.QuestionListener;
import demo.utt37.congcau.apptracnghiem.model.History;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;
import demo.utt37.congcau.apptracnghiem.teacher.ListQuestionFragment;
import demo.utt37.congcau.apptracnghiem.teacher.TeacherActivity;

public class QuestionAdapterTch extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<QuestionModel> data;
    private QuestionListener questionListener;
    private ArrayList<QuestionModel> list = new ArrayList<>();

    public QuestionAdapterTch(Context context, int layout, ArrayList<QuestionModel> data) {
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
//            viewHolder.imgEdit = (ImageView) view.findViewById(R.id.img_edit);
            viewHolder.imgDel = (ImageView) view.findViewById(R.id.img_del);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        QuestionModel questionModel = data.get(i);
        viewHolder.txtQuestion.setText(questionModel.getQuestion());

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

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(data);

        }
        else
        {
            for (QuestionModel wp : data)
            {
                if (wp.getQuestion().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    list.add(wp);
                }
            }
        }

        QuestionAdapterTch questionAdapterTch = new QuestionAdapterTch(context, R.layout.custom_item_lv_question,list);
        questionAdapterTch.notifyDataSetChanged();
    }
}

