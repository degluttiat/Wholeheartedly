package com.sergeipis.alena.wholeheartedly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private EditText mEditText;
    private RadioGroup mRadioGroup;

    public QuestionFragment() {
    }

    public static QuestionFragment newInstance(int sectionNumber) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public String getEditTextString() {
        return mEditText.getText().toString();
    }

    public String getSelectedOptionText() {
        if (mRadioGroup == null) {
            return "";
        }
        int x = mRadioGroup.getCheckedRadioButtonId(); // -1 or id of selected RadioButton

        switch (x) {
            case R.id.radio_1:
                return getString(R.string.price1);
            case R.id.radio_2:
                return getString(R.string.price2);
            case R.id.radio_3:
                return getString(R.string.price3);
            default:
                return "";
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setRetainInstance(true);
        int section = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView;
        if (section == 1){
            rootView = inflater.inflate(R.layout.fragment_quiz_start, container, false);
        } else if (section == 20) {
            rootView = inflater.inflate(R.layout.fragment_quiz2, container, false);
            setPrices(rootView);
        } else {
            rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
            setQuestion(section, rootView);
        }

        return rootView;
    }



    private void setQuestion(int section, View rootView) {
        TextView textView = rootView.findViewById(R.id.fragmentTextView);
        String[] questionsArray = getResources().getStringArray(R.array.questions_array);
        textView.setText(questionsArray[section -1]);

        mEditText = rootView.findViewById(R.id.fragmentEditText);
    }
 
    private void setPrices(View rootView) {
        mRadioGroup = rootView.findViewById(R.id.radioGroup);
        RadioButton radioButton_1 = rootView.findViewById(R.id.radio_1);
        RadioButton radioButton_2 = rootView.findViewById(R.id.radio_2);
        RadioButton radioButton_3 = rootView.findViewById(R.id.radio_3);

        SharedPreferences shPref = getContext().getApplicationContext()
                .getSharedPreferences("shpref", Context.MODE_PRIVATE);

        int priceTag = shPref.getInt(getString(R.string.key_price_tag), 0);
        switch (priceTag) {
            case 0:
            case 1:
                radioButton_1.setChecked(true);
                break;
            case 2:
                radioButton_2.setChecked(true);
                break;
            case 3:
                radioButton_3.setChecked(true);
                break;
        }

        shPref.edit().remove(getString(R.string.key_price_tag)).apply();
    }
}