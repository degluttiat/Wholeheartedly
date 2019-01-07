package com.sergeipis.alena.wholeheartedly;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ExamplesFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ImageView btnLeft;
    private ImageView btnRight;

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
        btnLeft = rootView.findViewById(R.id.btnLeft);
        btnRight = rootView.findViewById(R.id.btnRight);
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

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);

        return rootView;
    }

    // View.OnClickListener

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLeft:
                ((ExamplesActivity)getActivity()).onBtnLeftClick();
                break;
            case R.id.btnRight:
                ((ExamplesActivity)getActivity()).onBtnRightClick();
                break;

        }
    }
}

