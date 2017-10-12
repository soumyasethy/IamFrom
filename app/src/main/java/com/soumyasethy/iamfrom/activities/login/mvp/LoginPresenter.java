package com.soumyasethy.iamfrom.activities.login.mvp;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.soumyasethy.iamfrom.activities.login.mvp.model.LoginModel;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 10/10/17.
 */
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
        compositeSubscription.add(validate());
        compositeSubscription.add(observeLoginBtn());
        compositeSubscription.add(loadSavedState());
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    private Subscription validate() {
        Observable<TextViewTextChangeEvent> emailChangeObservable = RxTextView.textChangeEvents(view.email_edt);
        Observable<TextViewTextChangeEvent> passwordChangeObservable = RxTextView.textChangeEvents(view.password_edt);
        // force-disable the button
        view.setLoginEnabled(false);
        return Observable.combineLatest(emailChangeObservable, passwordChangeObservable,
                (emailObservable, passwordObservable) -> {
                    boolean emailCheck = view.validateEmail(emailObservable.text().toString());
                    boolean passwordCheck = view.validatePassword(passwordObservable.text().toString());
                    return emailCheck && passwordCheck;
                })
                .subscribe(aBoolean -> {
                    view.setLoginEnabled(aBoolean);
                });

    }

    private Subscription observeLoginBtn() {
        return view.observeLoginButton()
                .doOnNext(__ -> view.showLoading(true))
                .observeOn(Schedulers.io())
                .switchMap(__ -> {
                    String email = view.getEmailEdit();
                    String password = view.getPasswordEdit();
                    return model.getLoggedUserDetails(email,password); // long enough
                })
                //.debounce(400, TimeUnit.MILLISECONDS)
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

    private Subscription loadSavedState() {
        return model.getReposFromSaveState()
                .subscribe(getLoginDetailsList -> view.setMessage("Login button clicked - " + getLoginDetailsList));
    }
}
