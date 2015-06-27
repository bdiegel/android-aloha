## Working on the icon artwork


### Explanation of files

Sample app icon artwork

    ic_launcher.xcf           Gimp source artwork. Work begins here.
    ic_launcher.png           864x864 icon source image, exported from Gimp

    Pineapple-orig.xcf        Original unchanged artwork

Sample app Welcome page artwork

    welcome_image_0.xcf       Gimp source artwork. Work begins here.
    welcome_image_1.xcf       Gimp source artwork. Work begins here.
    welcome_image_2.xcf       Gimp source artwork. Work begins here.
FIXME these don't live here any longer
    welcome_image_0.png       400x400 image for the Android app
    welcome_image_1.png       400x400 image for the Android app
    welcome_image_2.png       400x400 image for the Android app

    PalmTree-orig.xcf         Original unchanged artwork for welcome_image_0
    Hibiscus-orig.xcf         Original unchanged artwork for welcome_image_1
    Turtle-orig.xcf           Original unchanged artwork for welcome_image_2


### How to make the launcher icon artwork

After working on the XCF file, save the source PNG for Android along with the XCF work from Gimp

   * `File menu -> Export As...` and choose to overwrite
     `ic_launcher.png`. Use the PNG default values in the dialog. This
     is the image for Android.
   * `File menu -> Save As...` and choose to overwrite
     `ic_launcher.xcf`. This is just to save the work done in Gimp.


Next, make the Android icons with Android Asset Studio's Launcher
Icon Generator

   * Load the [Launcher Icon Generator](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html) in a browser
   * In `Foreground`, click `IMAGE` and pick the `ic_launcher.png`
     file we made above.
   * In `TRIM`, click `DON'T TRIM`
   * Under `Shape`, click `BEVEL`
   * Click the `GENERATE WEB ICON` button
   * Confirm that all of the artwork looks amazing
   * Click the `DOWNLOAD .ZIP` button, save the file alongside the
     other files (it gets committed to source control as well)


Now see the section below _Deploying Into The Project Dir_


### How to work on the Welcome images

Edit the files (e.g. `res/art/welcome_image_0.xcf`) and then 
`File menu -> Export As...` to
`app/src/main/res/drawable/welcome_image_0.png`


### Play Store feature image

The feature image master artwork is `res/art/feature-image.xcf`.
Also contained in this directory is the original background photo
`res/art/feature-photo.jpg`.

After editing `feature-image.xcf`, export a JPEG image to
`res/prod/feature-image.jpg`. Use Quality: 100%. This is the final
artwork to be uploaded to Google.


### Deploying Into The Project Dir

The next step is to deploy the files in this ZIP to their proper
locations in the repository. Inside the ZIP, they're almost in the
right directory structure already, we can unpack in-place like this
(starting in the project root dir):

    $ pushd sample/src/main/
    $ unzip /path/to/zipfile

If this was the Launcher Icon, do this as well:

    $ mv web_hi_res_512.png ../../res/prod/

And then back out and commit:

    $ popd
    $ git commit ...
