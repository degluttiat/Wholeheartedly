package com.example.alena.wholeheartedly;

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
    private int section;
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
            case 0:
                return  "2 - 3 quatrains";
            case 1:
                return  "4 - 6 quatrains";
            case 2:
                return  "7 - 10 quatrains";
            default:
                return "";
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

        TextView textView = rootView.findViewById(R.id.fragmentTextView);
        mEditText = rootView.findViewById(R.id.fragmentEditText);
        section = getArguments().getInt(ARG_SECTION_NUMBER);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        switch (section) {
            case 1:
                textView.setText("Name:");

                break;
            case 2:
                textView.setText("Profession:");

                break;
            case 3:
                textView.setText("Skills:");

                break;
            case 4:
                textView.setText("Hobby:");

                break;
            case 5:
                textView.setText("Languages s/he knows:");

                break;
            case 6:
                textView.setText("A sphere s/he is an expert in:");

                break;
            case 7:
                textView.setText("Favorite book:");

                break;
            case 8:
                textView.setText("Favorite movie:");

                break;
            case 9:
                textView.setText("Favorite sport activity:");

                break;
            case 10:
                textView.setText("Favorite meal:");

                break;
            case 11:
                textView.setText("Personality traits that you appreciate:\n\nKindness, beauty, intuition, ability to listen, self criticism etc");
                mEditText.setHint("");

                break;
            case 12:
                textView.setText("Events and recollections:\n\nCircumstances you got to know each other under, a trip you've been to together, help in your moment of need, a surprise made for you etc");
                mEditText.setHint("");

                break;
            case 13:
                textView.setText("Geographic location:\n\nFavorite cafe, bar, pub, etc / Countries, where the person has traveled / City on birth or where s/he living now");
                mEditText.setHint("");

                break;
            case 14:
                textView.setText("Outlook:\n\nIs that person shorter or taller that most other people? / Haircut / Dressing style / Beard / Mustache");
                mEditText.setHint("");

                break;
            case 15:
                textView.setText("Good or bad habits:\n\nLoves to help adult people / Loves to donate / Perfectionism at work / Smoking / Etc!");
                mEditText.setHint("");

                break;
            case 16:
                textView.setText("How has that person impacted you?");

                break;
            case 17:
                textView.setText("Similarities or differences between you and that person:\n\nOr, maybe, you would like to emphasize how you complement each other?");
                mEditText.setHint("");

                break;
            case 18:
                textView.setText("What would you like to wish to your dear person?\n\nThese wishes should be related to the event, be it health & wealth wishes for a b-day,healthy relationship, harmony and many children for a wedding day or a successful career for someone who just finished studying in a college etc");
                mEditText.setHint("");

                break;

            case 19:
                rootView = inflater.inflate(R.layout.fragment_quiz2, container, false);
                mRadioGroup = rootView.findViewById(R.id.radioGroup);
                RadioButton radioButton_1 = rootView.findViewById(R.id.radio_1);
                RadioButton radioButton_2 = rootView.findViewById(R.id.radio_2);
                RadioButton radioButton_3 = rootView.findViewById(R.id.radio_3);

                SharedPreferences shPref = getActivity().getPreferences(Context.MODE_PRIVATE);
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

                shPref.edit()
                        .remove(getString(R.string.key_price_tag))
                        .apply();

        }

        return rootView;
    }
}