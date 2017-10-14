package com.soumyasethy.iamfrom.activities.home.mvp;

import android.app.Activity;

import com.soumyasethy.iamfrom.app.network.IamFromNetwork;
import com.soumyasethy.iamfrom.app.network.model.Details;
import com.twistedequations.rxstate.RxSaveState;

import rx.Observable;

public class HomeModel {

    public static final String REPOLIST_STATE_KEY = "REPOLIST_STATE_KEY";
    private final Activity activity;
    private final IamFromNetwork iamFromNetwork;

    public HomeModel(Activity activity, IamFromNetwork iamFromNetwork) {
        this.activity = activity;
        this.iamFromNetwork = iamFromNetwork;
    }

    public Observable<Details> getUserDetails(String username) {
        return iamFromNetwork.getUserDetails(username);
    }

    public void saveRepoListState(Details repoList) {
        RxSaveState.updateSaveState(activity, bundle -> {
            bundle.putParcelable(REPOLIST_STATE_KEY, repoList);
        });
    }

    public Observable<Details> getReposFromSaveState() {
        return RxSaveState.getSavedState(activity)
                .map(bundle -> bundle.getParcelable(REPOLIST_STATE_KEY));
    }

    public void startRepoActivity(Details repoList) {
        //ReposActivity.start(activity, repoList);
    }

}