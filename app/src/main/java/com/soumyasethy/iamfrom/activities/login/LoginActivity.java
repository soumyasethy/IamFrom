package com.soumyasethy.iamfrom.activities.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.soumyasethy.iamfrom.activities.login.dagger.DaggerLoginComponent;
import com.soumyasethy.iamfrom.activities.login.dagger.LoginModule;
import com.soumyasethy.iamfrom.activities.login.mvp.LoginPresenter;
import com.soumyasethy.iamfrom.activities.login.mvp.LoginView;
import com.soumyasethy.iamfrom.app.IamFromApplication;

import javax.inject.Inject;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 10/10/17.
 */
public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginView view;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerLoginComponent.builder()
                .appComponent(IamFromApplication.get(this).component())
                .loginModule(new LoginModule(this))
                .build().inject(this);

        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
