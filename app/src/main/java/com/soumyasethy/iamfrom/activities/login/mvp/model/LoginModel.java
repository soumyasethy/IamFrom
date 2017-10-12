package com.soumyasethy.iamfrom.activities.login.mvp.model;

import android.app.Activity;

import com.soumyasethy.iamfrom.app.network.IamFromNetwork;
import com.soumyasethy.iamfrom.app.network.model.Details;
import com.twistedequations.rxstate.RxSaveState;

import rx.Observable;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 10/10/17.
 */
public class LoginModel {

    public static final String LOGIN_STATE_KEY = "LOGIN_KEY";
    private final Activity activity;
    private final IamFromNetwork iamFromNetwork;


    public LoginModel(Activity activity, IamFromNetwork iamFromNetwork) {
        this.activity = activity;
        this.iamFromNetwork = iamFromNetwork;
    }


    public Observable<Details> getLoggedUserDetails(String username, String password) {
        return iamFromNetwork.loginUser(new Credentials(username, password));
    }

    public void saveRepoListState(Details repoList) {
        RxSaveState.updateSaveState(activity, bundle -> {
            bundle.putParcelable(LOGIN_STATE_KEY, repoList);
        });
    }


    public Observable<Details> getReposFromSaveState() {
        return RxSaveState.getSavedState(activity)
                .map(bundle -> bundle.getParcelable(LOGIN_STATE_KEY));
    }


    public void startRepoActivity(Details details) {
        // MainActivity.start(activity, details);
    }

}