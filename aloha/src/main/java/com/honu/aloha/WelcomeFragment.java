package com.honu.aloha;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeFragment extends Fragment {

    // Bundle key for the layout id
    private final static String LAYOUT_ID = "layout_id";

    // Bundle keys for PageDescription attributes
    private final static String HEADING = "heading";
    private final static String CONTENT = "content";
    private final static String IMAGE = "image";
    private final static String BACKGROUND_COLOR = "background_color";

    // Id of the welcome page fragment
    private final static int WELCOME_FRAGMENT_ID = R.layout.fragment_welcome;


    public static WelcomeFragment newInstance(PageDescriptor pageDescriptor) {
        WelcomeFragment frag = new WelcomeFragment();

        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID, WELCOME_FRAGMENT_ID);
        args.putInt(HEADING, pageDescriptor.getHeaderText());
        args.putInt(CONTENT, pageDescriptor.getContentText());
        args.putInt(IMAGE, pageDescriptor.getImageId());
        args.putInt(BACKGROUND_COLOR, pageDescriptor.getBackgroundColor());

        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        ViewGroup rootView = (ViewGroup) inflater.inflate(args.getInt(LAYOUT_ID, -1), container, false);

        TextView headingTextView = (TextView) rootView.findViewById(R.id.welcome_page_heading);
        TextView contentTextView = (TextView) rootView.findViewById(R.id.welcome_page_content);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.welcome_page_image);
        View layoutView = rootView.findViewById(R.id.welcome_fragment);

        headingTextView.setText(getActivity().getResources().getString(args.getInt(HEADING)));
        contentTextView.setText(getActivity().getResources().getString(args.getInt(CONTENT)));
        layoutView.setBackgroundColor(getActivity().getResources().getColor(args.getInt(BACKGROUND_COLOR)));

        Drawable imageDrawable = getActivity().getResources().getDrawable(args.getInt(IMAGE));
        imageView.setImageDrawable(imageDrawable);

        return rootView;
    }
}
