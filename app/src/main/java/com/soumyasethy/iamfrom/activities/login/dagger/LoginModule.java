package com.soumyasethy.iamfrom.activities.login.dagger;

import android.app.Activity;

import com.soumyasethy.iamfrom.activities.login.mvp.LoginPresenter;
import com.soumyasethy.iamfrom.activities.login.mvp.LoginView;
import com.soumyasethy.iamfrom.activities.login.mvp.model.LoginModel;
import com.soumyasethy.iamfrom.app.network.IamFromNetwork;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private final Activity activity;

    public LoginModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @LoginScope
    public LoginView view() {
        return new LoginView(activity);
    }

    @Provides
    @LoginScope
    public LoginModel model(IamFromNetwork iamFromNetwork) {
        return new LoginModel(activity, iamFromNetwork);
    }

    @Provides
    @LoginScope
    public LoginPresenter homePresenter(LoginView loginView, LoginModel model) {
        return new LoginPresenter(loginView, model);
    }

}
