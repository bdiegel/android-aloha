package com.honu.aloha;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Provides a cross-fading transition between page slides of the view pager.
 *
 * 1. The header and content text slide from right to left with the page and gradually fade out.
 * 2. The page image and background color also fade out as the next page increases in opacity.
 * 3. When you scroll off the last welcome page, the main activity is revealed.
 */
public class CrossfadePageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {

        if (position == 0.0f || position <= -1.0f || position >= 1.0f) {
            return;
        }

        View fragmentView = page.findViewById(R.id.welcome_fragment);
        View headerView = page.findViewById(R.id.welcome_page_heading);
        View contentView = page.findViewById(R.id.welcome_page_content);

        int pageWidth = page.getWidth();
        page.setTranslationX(pageWidth * -position);

        if (fragmentView != null) {
            fragmentView.setAlpha(1.0f - Math.abs(position));
        }

        if (headerView != null) {
            headerView.setTranslationX(pageWidth * position);
            headerView.setAlpha(1.0f - Math.abs(position));
        }

        if (contentView != null) {
            contentView.setTranslationX(pageWidth * position);
            contentView.setAlpha(1.0f - Math.abs(position));
        }
    }
}
