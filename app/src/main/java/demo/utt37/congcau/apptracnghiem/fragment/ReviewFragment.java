package demo.utt37.congcau.apptracnghiem.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.utt37.congcau.apptracnghiem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    public static ReviewFragment newInstance() {
//     Bundle args = new Bundle();
        ReviewFragment fragment = new ReviewFragment();
//    fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_construct, container, false);
    }

}
