package com.soumyasethy.iamfrom.activities.new_home.dagger;

import com.soumyasethy.iamfrom.activities.new_home.MainActivity;
import com.soumyasethy.iamfrom.app.dagger.AppComponent;

import dagger.Component;

@HomeScope
@Component(modules = {HomeModule.class}, dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(MainActivity mainActivity);

}
