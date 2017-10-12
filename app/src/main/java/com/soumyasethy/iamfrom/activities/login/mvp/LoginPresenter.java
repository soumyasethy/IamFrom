package com.soumyasethy.iamfrom.activities.login.mvp;

import android.text.Editable;
import android.text.TextWatcher;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.soumyasethy.iamfrom.activities.login.mvp.model.Credentials;
import com.soumyasethy.iamfrom.activities.login.mvp.model.LoginModel;
import com.soumyasethy.iamfrom.app.network.model.Details;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@SuppressWarnings("Convert2MethodRef")
public class LoginPresenter {

    private final LoginView view;
    private final LoginModel model;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public LoginPresenter(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {


       // compositeSubscription.add(validateEDT());
        compositeSubscription.add(observeLoginBtn());
        compositeSubscription.add(loadSavedState());
    }



    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Subscription loadSavedState() {
        return model.getReposFromSaveState()
                .subscribe(getLoginDetailsList -> view.setMessage("Login button clicked - " + getLoginDetailsList));
    }

    private Subscription observeLoginBtn() {
        return view.observeLoginButton()
                //.doOnNext(__ -> Timber.d("Login Button is clicked"))
                .doOnNext(__ -> view.showLoading(true))

                //.map(__ -> view.validate(view.getEmailEdit(),view.getPasswordEdit()))
                .observeOn(Schedulers.io())
                .switchMap(__ -> {
                    String email = view.getEmailEdit();
                    String password = view.getPasswordEdit();
                    //view.validate( email, password);
                    return model.getLoggedUserDetails(email,password); // long enough
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(getLoginDetailsList -> model.saveRepoListState(getLoginDetailsList))
                .doOnEach(__ -> view.showLoading(false))
                .retry()
                .subscribe(getLoginDetailsList -> {
                   // model.startRepoActivity(getLoginDetailsList);
                    Timber.d(getLoginDetailsList.toString());
                    view.ShowToast(getLoginDetailsList);
                });
    }
}
