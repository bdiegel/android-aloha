package com.honu.aloha;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A basic welcome activity that can be customized.
 *
 * 1. Extend BaseWelcomeActivity
 * 2. Implement the abstract method createPages()
 * 3. Create a PageDescriptor for each page. Specify a resources identifier for each page element:
 *    - image, background color, header and content text
 * 4. Use addPage(pageDescription) to add welcome pages the page adapter
 */
public abstract class BaseWelcomeActivity extends AppCompatActivity {

    private static final String LOG_TAG = BaseWelcomeActivity.class.getSimpleName();

    protected ViewPager mViewPager;
    protected PagerAdapter mPagerAdapter;
    protected LinearLayout mPageIndicator;
    protected Button mSkipButton;
    protected Button mDoneButton;
    protected Button mNextButton;

    protected boolean mIsOpaque = true;


    /**
     * Clients must implement to add welcome pages.
     *
     *  public abstract void createPages() {
     *     addPage(new PageDescriptor(R.string.welcome_header_0, R.string.welcome_content_0, R.drawable.welcome_image_0, R.color.welcome_color_0));
     *     addPage(new PageDescriptor(R.string.welcome_header_1, R.string.welcome_content_1, R.drawable.welcome_image_1, R.color.welcome_color_1));
     *     addPage(new PageDescriptor(R.string.welcome_header_2, R.string.welcome_content_2, R.drawable.welcome_image_2, R.color.welcome_color_2));
     *  }
     */
    public abstract void createPages();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // prevents the toolbar from the main Activity below from showing through (note: API 19 specific)
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_welcome);

        // handler for 'skip' button press
        mSkipButton = Button.class.cast(findViewById(R.id.skip));
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endWelcome();
            }
        });

        // handler for 'next' button press
        mNextButton = Button.class.cast(findViewById(R.id.next));
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        });

        // handler for 'done' button press
        mDoneButton = Button.class.cast(findViewById(R.id.done));
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endWelcome();
            }
        });

        // create view pager
        mViewPager = (ViewPager) findViewById(R.id.pager);

        // create view pager adapter
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        // bind adapter to view
        mViewPager.setAdapter(mPagerAdapter);

        // create and bind a page transformer
        mViewPager.setPageTransformer(true, new CrossfadePageTransformer());

        // handler for page change events
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int totalPages = mPagerAdapter.getCount();

                // change the view pager opacity on the last slide (as the fragment becomes more transparent)
                if (position == totalPages - 2 && positionOffset > 0) {
                    if (mIsOpaque) {
                        mViewPager.setBackgroundColor(Color.TRANSPARENT);
                        mIsOpaque = false;
                    }
                } else {
                    if (!mIsOpaque) {
                        mViewPager.setBackgroundColor(getResources().getColor(R.color.primary_material_light));
                        mIsOpaque = true;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);

                int totalPages = mPagerAdapter.getCount();

                // show 'done' button on last visible page
                if (position == totalPages - 2) {
                    mSkipButton.setVisibility(View.GONE);
                    mNextButton.setVisibility(View.GONE);
                    mDoneButton.setVisibility(View.VISIBLE);
                    // the 'skip' and 'next' buttons visible
                } else if (position < totalPages - 2) {
                    mSkipButton.setVisibility(View.VISIBLE);
                    mNextButton.setVisibility(View.VISIBLE);
                    mDoneButton.setVisibility(View.GONE);
                    // final transition
                } else if (position == totalPages - 1) {
                    endWelcome();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // add descriptors for pages
        createPages();

        // build the page indicator
        createIndicators();
    }

    public void addPage(PageDescriptor pageDescriptor) {
        ((ScreenSlidePagerAdapter)mPagerAdapter).addPage(pageDescriptor);
    }

    @Override
    public void onBackPressed() {

        // on first page, let system handle the back button
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (mViewPager != null) {
            mViewPager.clearOnPageChangeListeners();
        }
    }

    private void createIndicators() {

        mPageIndicator = (LinearLayout) findViewById(R.id.page_indicators);

        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (5 * scale + 0.5f);

        int totalPages = mPagerAdapter.getCount();

        for (int i = 0; i < totalPages - 1; i++) {
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.drawable.circle);
            circle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            circle.setAdjustViewBounds(true);
            circle.setPadding(padding, 0, padding, 0);
            mPageIndicator.addView(circle);
        }

        setIndicator(0);
    }

    private void setIndicator(int index) {
        int totalPages = mPagerAdapter.getCount();

        if (index < totalPages) {

            for (int i = 0; i < totalPages - 1; i++) {

                ImageView circle = (ImageView) mPageIndicator.getChildAt(i);
                if (i == index) {
                    circle.setColorFilter(getResources().getColor(R.color.button_selected));
                } else {
                    circle.setColorFilter(getResources().getColor(R.color.button_deselected));
                }
            }
        }
    }

    private void endWelcome() {
        finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}