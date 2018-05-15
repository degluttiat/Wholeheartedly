package com.example.alena.wholeheartedly;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private Button buttonPrev;
    private Button buttonNext;
    private Button buttonSend;
    String s;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    String s7;
    String s8;
    String s9;
    String s10;
    String s11;
    String s12;
    String s13;
    String s14;
    String s15;
    String s16;
    String s17;
    String option;
    boolean sendButtonOption = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quiz");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                EditText editText = findViewById(R.id.fragmentEditText);


                if (position == 0) {
                    buttonPrev.setVisibility(View.INVISIBLE);
                } else {
                    buttonPrev.setVisibility(View.VISIBLE);
                }

                if (position == 18) {
                    buttonNext.setVisibility(View.INVISIBLE);
                    buttonSend.setVisibility(View.VISIBLE);
                } else {
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonSend.setVisibility(View.INVISIBLE);
                }

                if (position == 0) {
                    if (editText.getText().toString().equals("")) {
                        s = "Null";
                    } else {
                        s = editText.getText().toString();
                    }
                }

                if (position == 1) {
                    if (editText.getText().toString().equals("")) {
                        s1 = "Null";
                    } else {
                        s1 = editText.getText().toString();
                    }
                }


                if (position == 2) {
                    if (editText.getText().toString().equals("")) {
                        s2 = "Null";
                    } else {
                        s2 = editText.getText().toString();
                    }
                }


                if (position == 3) {
                    if (editText.getText().toString().equals("")) {
                        s3 = "Null";
                    } else {
                        s3 = editText.getText().toString();
                    }
                }

                if (position == 4) {
                    if (editText.getText().toString().equals("")) {
                        s4 = "Null";
                    } else {
                        s4 = editText.getText().toString();
                    }
                }

                if (position == 5) {
                    if (editText.getText().toString().equals("")) {
                        s5 = "Null";
                    } else {
                        s5 = editText.getText().toString();
                    }
                }

                if (position == 6) {
                    if (editText.getText().toString().equals("")) {
                        s6 = "Null";
                    } else {
                        s6 = editText.getText().toString();
                    }
                }

                if (position == 7) {
                    if (editText.getText().toString().equals("")) {
                        s7 = "Null";
                    } else {
                        s7 = editText.getText().toString();
                    }
                }

                if (position == 8) {
                    if (editText.getText().toString().equals("")) {
                        s8 = "Null";
                    } else {
                        s8 = editText.getText().toString();
                    }
                }

                if (position == 9) {
                    if (editText.getText().toString().equals("")) {
                        s9 = "Null";
                    } else {
                        s9 = editText.getText().toString();
                    }
                }

                if (position == 10) {
                    if (editText.getText().toString().equals("")) {
                        s10 = "Null";
                    } else {
                        s10 = editText.getText().toString();
                    }
                }

                if (position == 11) {
                    if (editText.getText().toString().equals("")) {
                        s11 = "Null";
                    } else {
                        s11 = editText.getText().toString();
                    }
                }

                if (position == 12) {
                    if (editText.getText().toString().equals("")) {
                        s12 = "Null";
                    } else {
                        s12 = editText.getText().toString();
                    }
                }

                if (position == 13) {
                    if (editText.getText().toString().equals("")) {
                        s13 = "Null";
                    } else {
                        s13 = editText.getText().toString();
                    }
                }

                if (position == 14) {
                    if (editText.getText().toString().equals("")) {
                        s14 = "Null";
                    } else {
                        s14 = editText.getText().toString();
                    }
                }

                if (position == 15) {
                    if (editText.getText().toString().equals("")) {
                        s15 = "Null";
                    } else {
                        s15 = editText.getText().toString();
                    }
                }

                if (position == 16) {
                    if (editText.getText().toString().equals("")) {
                        s16 = "Null";
                    } else {
                        s16 = editText.getText().toString();
                    }
                }

                if (position == 17) {

                    RadioGroup radioGroup = findViewById(R.id.radioGroup);
                    RadioButton radioButton_1 = findViewById(R.id.radio_1);
                    RadioButton radioButton_2 = findViewById(R.id.radio_2);
                    RadioButton radioButton_3 = findViewById(R.id.radio_3);

                    SharedPreferences shPref = getPreferences(Context.MODE_PRIVATE);
                    int priceTag = shPref.getInt(getString(R.string.key_price_tag), 0);

                    switch (priceTag) {
                        case 1:
                            sendButtonOption = false;
                            radioButton_1.setChecked(true);
                            break;
                        case 2:
                            sendButtonOption = false;
                            radioButton_2.setChecked(true);
                            break;
                        case 3:
                            sendButtonOption = false;
                            radioButton_3.setChecked(true);
                            break;
                    }

                    shPref.edit()
                            .remove(getString(R.string.key_price_tag))
                            .apply();

                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {

                                case R.id.radio_1:
                                    option = "2 - 3 quatrains";
                                    sendButtonOption = false;
                                    break;
                                case R.id.radio_2:
                                    option = "4 - 6 quatrains";
                                    sendButtonOption = false;
                                    break;
                                case R.id.radio_3:
                                    option = "7 - 10 quatrains";
                                    sendButtonOption = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    });


                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        buttonPrev = findViewById(R.id.quizButtonPrev);
        buttonNext = findViewById(R.id.quizButtonNext);
        buttonSend = findViewById(R.id.quizButtonSend);
        buttonPrev.setVisibility(View.INVISIBLE);
        buttonSend.setVisibility(View.INVISIBLE);


        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.arrowScroll(View.FOCUS_RIGHT);
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendButtonOption) {
                    Toast.makeText(getApplicationContext(), "Select one of the options to continue",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "sergei.pismeny@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Wholeheartedly");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, Text());
                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }


            }
        });

    }


    String Text() {

        String BodyEmail = "Option: " + option + "\nName: \n" + s + "\nProfession: \n" + s1 + "\nSkills: \n" + s2 +
                "\nHobby: \n" + s3 + "\nLanguages s/he knows: \n" + s4 +
                "\nA sphere s/he is an expert in: \n" + s5 + "\nFavorite book: \n" + s6 +
                "\nFavorite movie: \n" + s7 + "\nFavorite sport activity: \n" + s8 +
                "\nFavorite meal: \n" + s9 + "\nPersonality traits that you appreciate: \n" + s10 +
                "\nEvents and recollections: \n" + s11 + "\nGeographic location: \n" + s12 +
                "\nOutlook: \n" + s13 + "\nGood or bad habits: \n" + s14 +
                "\nHow has that person impacted you? \n" + s15 +
                "\nSimilarities or differences between you and that person: \n" + s16 +
                "\nWhat would you like to wish to your dear person? \n" + s17;
        return BodyEmail;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            setRetainInstance(true);
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

            TextView textView = (TextView) rootView.findViewById(R.id.fragmentTextView);
            EditText editText = rootView.findViewById(R.id.fragmentEditText);
            int section = getArguments().getInt(ARG_SECTION_NUMBER);
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
                    editText.setHint("");

                    break;
                case 12:
                    textView.setText("Events and recollections:\n\nCircumstances you got to know each other under, a trip you've been to together, help in your moment of need, a surprise made for you etc");
                    editText.setHint("");

                    break;
                case 13:
                    textView.setText("Geographic location:\n\nFavorite cafe, bar, pub, etc / Countries, where the person has traveled / City on birth or where s/he living now");
                    editText.setHint("");

                    break;
                case 14:
                    textView.setText("Outlook:\n\nIs that person shorter or taller that most other people? / Haircut / Dressing style / Beard / Mustache");
                    editText.setHint("");

                    break;
                case 15:
                    textView.setText("Good or bad habits:\n\nLoves to help adult people / Loves to donate / Perfectionism at work / Smoking / Etc!");
                    editText.setHint("");

                    break;
                case 16:
                    textView.setText("How has that person impacted you?");

                    break;
                case 17:
                    textView.setText("Similarities or differences between you and that person:\n\nOr, maybe, you would like to emphasize how you complement each other?");
                    editText.setHint("");

                    break;
                case 18:
                    textView.setText("What would you like to wish to your dear person?\n\nThese wishes should be related to the event, be it health & wealth wishes for a b-day,healthy relationship, harmony and many children for a wedding day or a successful career for someone who just finished studying in a college etc");
                    editText.setHint("");

                    break;

                case 19:
                    rootView = inflater.inflate(R.layout.fragment_quiz2, container, false);

            }

            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 19;
        }
    }
}
