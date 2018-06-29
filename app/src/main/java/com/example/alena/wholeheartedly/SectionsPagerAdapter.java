package com.example.alena.wholeheartedly;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a QuestionFragment (defined as a static inner class below).
        return QuestionFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 19;
    }
}