package com.honu.aloha;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Utility functions to help with determining if the welcome activity should be displayed.
 */
public class WelcomeHelper {

    private static final String LOG_TAG = WelcomeHelper.class.getSimpleName();

    // name of shared preference to store last version code
    public final static String PREF_LAST_RUN_VERSION_CODE = "lastRunVersionCode";

    // useful flag for testing welcome activity
    protected static boolean ALWAYS_SHOW_WELCOME = false;

    /**
     * Flag to set if you want isWelcomeRequired to always return true.
     * This is usefult for testing your welcome screen during development.
     *
     * @param show  turns override on/off (off by default)
     */
    public static void setAlwaysShowWelcome(boolean show) {
        ALWAYS_SHOW_WELCOME = show;
    }

    /**
     * Used to determine whether your main activity should display the welcome activity first.
     * Compares the current app version to the last known version of the app that was run.
     * If the current version is newer, we update the shared preference that stores the version
     * code and the method returns true.
     *
     * @param context application context for getting shared preferences
     * @return true if the welcome activity should be displayed by app
     */
    public static boolean isWelcomeRequired(Context context) {

        if (ALWAYS_SHOW_WELCOME)
            return true;

        // version codes to compare
        int lastRunVersionCode = getSharedPreferenceInt(context, PREF_LAST_RUN_VERSION_CODE);
        int installVersionCode = getInstallVersionCode(context);

        if (installVersionCode > lastRunVersionCode) {

            // automatically update the stored version code
            setLastRunVersionCode(context, installVersionCode);

            return true;
        }

        return false;
    }

    /**
     * Returns the last known version code of app as stored by a shared preference
     * @param context
     * @return
     */
    public static int getLastRunVersionCode(Context context) {
        return getSharedPreferenceInt(context, PREF_LAST_RUN_VERSION_CODE);
    }

    public static void clearLastRunVersionCode(Context context) {
        setLastRunVersionCode(context, -1);
    }

    public static void updatetLastRunVersionCode(Context context) {
        int installVersionCode = getInstallVersionCode(context);
        setLastRunVersionCode(context, installVersionCode);
    }

    /**
     * Stores the specified version code in a shared preference
     * @param context
     * @param versionCode
     */
    public static void setLastRunVersionCode(Context context, int versionCode) {
        Log.d(LOG_TAG, "Setting " + PREF_LAST_RUN_VERSION_CODE + " to: " + versionCode);
        putSharedPreferenceInt(context, PREF_LAST_RUN_VERSION_CODE, versionCode);
    }

    /**
     * Returns the version code of the currently running app
     * @param context
     * @return
     */
    public static int getInstallVersionCode(Context context) {
        int version = -1;
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    /**
     * Return int value of shared preference specifies by key
     * @param context
     * @param key
     * @return
     */
    public static int getSharedPreferenceInt(Context context, String key) {

        int result = -1;
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (sharedPreferences != null) {
                result = sharedPreferences.getInt(key, -1);
            }
        }
        return result;
    }

    /**
     * Sets a shared preference key to specified int value
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreferenceInt(Context context, String key, int value) {

        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Editor edit = sharedPreferences.edit();
            edit.putInt(key, value);

            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }
}



