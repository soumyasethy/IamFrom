package com.soumyasethy.iamfrom.activities.home.mvp;

import rx.subscriptions.CompositeSubscription;

@SuppressWarnings("Convert2MethodRef")
public class HomePresenter {

    private final HomeView view;
    private final HomeModel model;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public HomePresenter(HomeView view, HomeModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        // compositeSubscription.add(observeLookupButton());
        //  compositeSubscription.add(loadSavedState());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }



   /* private Subscription observeLookupButton() {
        return view.observeButton()
                // .doOnNext(__ -> Timber.d("Button is clicked......."))
                .doOnNext(__ -> view.showLoading(true))
                .map(__ -> view.getUsernameEdit())
                .observeOn(Schedulers.io())
                .switchMap(username -> model.getUserDetails(username))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(gitHubRepoList -> model.saveRepoListState(gitHubRepoList))
                .doOnEach(__ -> view.showLoading(false))
                .retry()
                .subscribe(gitHubRepoList -> {
                    //model.startRepoActivity(gitHubRepoList);
                    //model.getUserDetails();
                    view.ShowToast(gitHubRepoList);
                });
    }*/
}
