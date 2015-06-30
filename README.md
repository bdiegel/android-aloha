Aloha
=====
A library for adding a simple welcome activity to your Android app.

<img src='https://raw.githubusercontent.com/bdiegel/android-aloha-sample/master/res/prod/screenshots/nexus5/welcome_page_0.png' width=175/>
<img src='https://raw.githubusercontent.com/bdiegel/android-aloha-sample/master/res/prod/screenshots/nexus5/welcome_page_1.png' width=175/>
<img src='https://raw.githubusercontent.com/bdiegel/android-aloha-sample/master/res/prod/screenshots/nexus5/welcome_page_2.png' width=175/>
<img src='https://raw.githubusercontent.com/bdiegel/android-aloha-sample/master/res/prod/screenshots/nexus5/main_activity.png' width=175/>


Specify resource identifiers to customize each welcome page:

| Description           | View      | Resource Id  (yours)       |
| :-------------------- |:--------- |----------------------------|
| **image**             | ImageView | R.drawable.your_image      |
| **header**            | TextView  | R.string.your_page_header  |
| **content**           | TextView  | R.string.your_page_content |
| **background color**  | Layout    | R.color.your_page_color    |

Documentation
-------------
See the [Aloha](http://bdiegel.github.io/android-aloha) project page for more details.


Sample Application
------------------

For a working implementation see the sample application project: [android-aloha-sample](https://github.com/bdiegel/android-aloha-sample).

The sample application can also be installed from the Play Store.


Usage
-----

  1. The library is available on JCenter. Add the dependency to your build.gradle file:

         dependencies {
             compile 'honuapps:aloha:0.9.0'
         }

  2. Prepare your resources for each welcome page: image, header, content, and background color.
  3. Create a WelcomeActivity that extends [BaseWelcomeActivity](https://github.com/bdiegel/android-aloha/blob/master/aloha/src/main/java/com/honu/aloha/BaseWelcomeActivity.java).
  4. In your WelcomeActivity, implement createPages():

        @Override
        public void createPages() {
          addPage(new PageDescriptor(R.string.welcome_header_0, R.string.welcome_content_0, R.drawable.welcome_image_0, R.color.welcome_color_0));
          addPage(new PageDescriptor(R.string.welcome_header_1, R.string.welcome_content_1, R.drawable.welcome_image_1, R.color.welcome_color_1));
          addPage(new PageDescriptor(R.string.welcome_header_2, R.string.welcome_content_2, R.drawable.welcome_image_2, R.color.welcome_color_2));
        }

  5. Declare your WelcomeActivity in AndroidManifest.xml:

        <activity android:name=".WelcomeActivity" />

  6. Modify your MainActivity to start the WelcomeActivity:

        if (WelcomeHelper.isWelcomeRequired(this)) {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }


Acknowledgments
---------------
The sample application uses images under CC0 license.

License
-------
ISC license information [LICENSE](LICENSE.txt)

