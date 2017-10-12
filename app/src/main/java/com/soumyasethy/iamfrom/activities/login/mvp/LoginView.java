package com.soumyasethy.iamfrom.activities.login.mvp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.soumyasethy.iamfrom.R;
import com.soumyasethy.iamfrom.app.network.model.Details;

import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;

import static com.soumyasethy.iamfrom.ext.Constants.PASSWORD_PATTERN;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 10/10/17.
 */
@SuppressLint("ViewConstructor")
public class LoginView extends FrameLayout {

    private final ProgressDialog progressDialog = new ProgressDialog(getContext());
    @BindView(R.id.email)
    EditText email_edt;
    @BindView(R.id.password)
    EditText password_edt;
    @BindView(R.id.email_sign_in_button)
    Button login_btn;
    @Nullable
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    private Subscription subscription;

    private Pattern pattern;
    private Matcher matcher;


    public LoginView(Activity activity) {
        super(activity);
        pattern = Pattern.compile(PASSWORD_PATTERN);
        inflate(getContext(), R.layout.login, this);

        progressDialog.setMessage("Logging...");
        ButterKnife.bind(this);
    }

    public void setMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public Observable<Void> observeLoginButton() {
        return RxView.clicks(login_btn);
    }

    public String getEmailEdit() {
        return email_edt.getText().toString();
    }

    public String getPasswordEdit() {
        return password_edt.getText().toString();
    }

    public boolean validateEmail(String email) {
        email_edt.setError(null);
        email = getEmailEdit();
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_edt.setError("Email ID is Empty");
            return false;

        } else if (!isEmailValid(email)) {
            email_edt.setError("Invalid Email");
            return false;
        } else {
            return true;
        }

    }

    public boolean validatePassword(String password) {

        password_edt.setError(null);
        password = getPasswordEdit();
        // Check for a valid password, if the user entered one.
       /* if (!TextUtils.isEmpty(password)) {
            password_edt.setError("Empty Password");
            return false;
        } *//*else*/
        if (isPasswordValid(password)) {
            return true;
        } else {
            password_edt.setError("Invalid Password Pattern");
            return false;
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        matcher = pattern.matcher(password);
        return matcher.matches();


    }


    public void showLoading(boolean loading) {
        if (loading) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    public void ShowToast(Details message) {
        Toast.makeText(getContext(), "Login :" + message.status + "\nHello :" + message.userDetails.firstName, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "Response :" + message.toString(), Toast.LENGTH_SHORT).show();
    }


    public void setLoginEnabled(Boolean aBoolean) {
        login_btn.setEnabled(aBoolean);
        if (aBoolean) {
            login_btn.setAlpha(1);
        } else {
            login_btn.setAlpha(0.3f);
        }

    }
}
