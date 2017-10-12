package com.soumyasethy.iamfrom.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.soumyasethy.iamfrom.BuildConfig;
import com.soumyasethy.iamfrom.R;
import com.soumyasethy.iamfrom.app.dagger.AppComponent;
import com.soumyasethy.iamfrom.app.dagger.DaggerAppComponent;
import com.soumyasethy.iamfrom.app.dagger.module.AppModule;
import com.soumyasethy.iamfrom.ext.Constants;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class IamFromApplication extends Application {

    private AppComponent appComponent;

    public static IamFromApplication get(Activity activity) {
        return (IamFromApplication) activity.getApplication();
    }

    public static IamFromApplication get(Service service) {
        return (IamFromApplication) service.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    super.log(priority, Constants.LOGTAG, message, t);
                }
            });
        }

        JodaTimeAndroid.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public AppComponent component() {
        return appComponent;
    }
}
