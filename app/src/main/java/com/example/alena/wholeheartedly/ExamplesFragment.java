package com.example.alena.wholeheartedly;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ExamplesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public ExamplesFragment() {
    }


    public static ExamplesFragment newInstance(int sectionNumber) {
        ExamplesFragment fragment = new ExamplesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_examples, container, false);
        TextView textView = rootView.findViewById(R.id.section_label);
        int section = getArguments().getInt(ARG_SECTION_NUMBER);
        ConstraintLayout layout = rootView.findViewById(R.id.constraintLayout);
        ImageView btnLeft = rootView.findViewById(R.id.btnLeft);
        ImageView btnRight = rootView.findViewById(R.id.btnRight);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        switch (section) {
            case 1:
                btnLeft.setVisibility(View.GONE);
                textView.setText(getString(R.string.text2));
                break;
            case 2:
                textView.setText(getString(R.string.text1));
                break;
            case 3:
                btnRight.setVisibility(View.GONE);
                textView.setText(getString(R.string.text));
                break;
        }
        return rootView;
    }
}

