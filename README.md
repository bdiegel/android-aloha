Aloha
=====
A library for adding a simple welcome activity to your Android app.

Specify resource identifiers to customize each welcome page:

| Description           | View      | Resource Id                |
| :-------------------- |:--------- |----------------------------|
| **image**             | ImageView | R.drawable.your_image      |
| **header**            | TextView  | R.string.your_page_header  |
| **content**           | TextView  | R.string.your_page_content |
| **background color**  | Layout    | R.color.your_page_color    |

Sample App
----------

For a working implementation see the application in the `sample/` folder.


Usage
-----

  1. Prepare your resources for each welcome page: image, header, content, and background color.
  2. Create a WelcomeActivity that extends [BaseWelcomeActivity](https://github.com/bdiegel/android-aloha/blob/master/aloha/src/main/java/com/honu/aloha/BaseWelcomeActivity.java).
  3. Implement createPages() in WelcomeActivity:

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

About
-----

The `versionCode` of the client app can be used to show the welcome activity on app install or upgrade.
WelcomeHelper.isWelcomeRequired(this) compares versions and updates a SharedPreference when 'true'.

The PageTansformer provides a cross-fading transition between page slides of the view pager. The text slides with the
page and gradually fades out. The image and background color also fade out as the new page increases in opacity. When
you scroll off the last welcome page, the main activity is revealed.

Recommendations
---------------

Follow these suggestions for the best results:

  * Use reasonably sized image resources. The layouts use `centerInside` for the ImageView scale type. This means
    your image will scale down only if necessary and never be scaled up. A 400x400 image works well with most devices.
  * When selecting background colors keep in mind that the default theme is light on dark.
  * Use Toolbar in your MainActivity layout and disable the window title in the style:

        <style name="AppTheme.Base" parent="Theme.AppCompat.Light.NoActionBar">
            <item name="android:windowNoTitle">true</item>
        </style>


Acknowledgments
---------------
The sample application uses images under CC0 license.

License
-------
License information [LICENSE](LICENSE.txt)

