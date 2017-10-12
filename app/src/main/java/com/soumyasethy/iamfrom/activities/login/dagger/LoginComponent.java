package com.soumyasethy.iamfrom.activities.login.dagger;

import com.soumyasethy.iamfrom.activities.login.LoginActivity;
import com.soumyasethy.iamfrom.app.dagger.AppComponent;

import dagger.Component;

@LoginScope
@Component(modules = {LoginModule.class}, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity mainActivity);

}
