package demo.utt37.congcau.apptracnghiem.teacher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter  extends FragmentStatePagerAdapter {

    int position;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new TeacherContactFragment();
                position=0;
                break;
            case 1:
                frag=new ListQuestionFragment();
                break;
        }
        return frag;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title="Thông tin Giáo viên";
                break;
            case 1:
                title="Danh sách Câu hỏi";
                break;
        }

        return title;
    }

    public int getPosition() {
        return position;
    }
}

