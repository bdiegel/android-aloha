package com.honu.aloha.sample;

import android.os.Bundle;

import com.honu.aloha.BaseWelcomeActivity;
import com.honu.aloha.PageDescriptor;


public class WelcomeActivity extends BaseWelcomeActivity {

    private static final String LOG_TAG = BaseWelcomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void createPages() {
        addPage(new PageDescriptor(R.string.welcome_header_0, R.string.welcome_content_0, R.drawable.welcome_image_0, R.color.welcome_color_0));
        addPage(new PageDescriptor(R.string.welcome_header_1, R.string.welcome_content_1, R.drawable.welcome_image_1, R.color.welcome_color_1));
        addPage(new PageDescriptor(R.string.welcome_header_2, R.string.welcome_content_2, R.drawable.welcome_image_2, R.color.welcome_color_2));
    }

}
