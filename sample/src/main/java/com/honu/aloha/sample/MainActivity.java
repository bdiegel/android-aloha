package com.honu.aloha.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.honu.aloha.WelcomeHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            maybeShowWelcomeActivity();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.main_title);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        }

        TextView url = (TextView) findViewById(R.id.github_url);
        url.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void maybeShowWelcomeActivity() {

        // FOR TESTING ONLY: resetting the versionCode will FORCE the welcome activity to display every time
        WelcomeHelper.clearLastRunVersionCode(this);

        if (WelcomeHelper.isWelcomeRequired(this)) {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_info:
                startActivity(new Intent(this, InfoActivity.class));
                return true;
            case R.id.action_welcome:
                maybeShowWelcomeActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
