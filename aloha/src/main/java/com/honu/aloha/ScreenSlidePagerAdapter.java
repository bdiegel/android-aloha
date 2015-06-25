package com.honu.aloha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bdiegel on 6/22/15.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<PageDescriptor> mPageDescriptors = new ArrayList<PageDescriptor>();


    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position < 0 || position > mPageDescriptors.size() + 1) {
            throw new IllegalArgumentException("Welcome page not available for position: " + position);
        }

        Fragment welcomePage = null;

        // swiping one page beyond the last page returns am empty transparent fragment
        if (position == mPageDescriptors.size()) {
            welcomePage = TransparentFragment.newInstance();
        } else {
            PageDescriptor pageDescriptor = mPageDescriptors.get(position);
            welcomePage = WelcomeFragment.newInstance(pageDescriptor);
        }

        return welcomePage;
    }

    public void addPage(PageDescriptor pageDescriptor) {
        mPageDescriptors.add(pageDescriptor);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPageDescriptors.size() + 1;
    }

    public static class TransparentFragment extends Fragment{

        private final static String LAYOUT_ID = "layout_id";

        private final static int WELCOME_FRAGMENT_ID = R.layout.fragment_transparent;

        public static TransparentFragment newInstance() {
            TransparentFragment fragment = new TransparentFragment();
            Bundle args = new Bundle();
            args.putInt(LAYOUT_ID, WELCOME_FRAGMENT_ID);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(getArguments().getInt(LAYOUT_ID, -1), container, false);
            return rootView;
        }
    }
}
