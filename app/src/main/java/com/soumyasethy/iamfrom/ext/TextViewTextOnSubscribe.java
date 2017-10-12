package com.soumyasethy.iamfrom.ext;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 12/10/17.
 */

public final class TextViewTextOnSubscribe implements Observable.OnSubscribe<CharSequence> {
    final EditText view;

    public TextViewTextOnSubscribe(EditText view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super CharSequence> subscriber) {
        // checkUiThread();

        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        view.addTextChangedListener(watcher);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.removeTextChangedListener(watcher);
            }
        });

        // Emit initial value.
        subscriber.onNext(view.getText());
    }
}
