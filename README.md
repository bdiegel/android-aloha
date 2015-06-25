Aloha
=====
Aloha is a library for adding a simple welcome activity to your Android app.

Specify resource identifiers to customize each welcome page:

|                       |                            |
| :-------------------- | :--------------------------|
| **image**             | R.drawable.your_image      |
| **header**            | R.string.your_page_header  |
| **content**           | R.string.your_page_content |
| **background color**  | R.color.your_page_color    |


The `versionCode` of the client app is stored in a SharedPreference to help determine if the welcome should be displayed.

### Usage
*For a working implementation see the application in the `sample/` folder.*

1. Prepare your resources for each welcome page: image, header, content, and background color
2. Create a WelcomeActivity that extends [BaseWelcomeActivity](https://github.com/bdiegel/android-aloha/blob/master/aloha/src/main/java/com/honu/aloha/BaseWelcomeActivity.java)
3. Implement createPages():

        @Override
        public void createPages() {
          addPage(new PageDescriptor(R.string.welcome_header_0, R.string.welcome_content_0, R.drawable.welcome_image_0, R.color.welcome_color_0));
          addPage(new PageDescriptor(R.string.welcome_header_1, R.string.welcome_content_1, R.drawable.welcome_image_1, R.color.welcome_color_1));
          addPage(new PageDescriptor(R.string.welcome_header_2, R.string.welcome_content_2, R.drawable.welcome_image_2, R.color.welcome_color_2));
        }

4. Declare your WelcomeActivity in AndroidManifest.xml:

        <activity android:name=".WelcomeActivity" />

5. Modify your MainActivity to start the WelcomeActivity:

        if (WelcomeHelper.isWelcomeRequired(this)) {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

### Recommendations
Follow these suggestions for the best results:

* Use reasonably sized image resources: try 400x400
* The default (and currently only) theme is light on dark. Choose appropriate colors.
* User Toolbar in your MainActivity layout and disable the window title in the style:

        <style name="AppTheme.Base" parent="Theme.AppCompat.Light.NoActionBar">
            <item name="android:windowNoTitle">true</item>
        </style>


### Acknowledgments

