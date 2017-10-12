package com.soumyasethy.iamfrom.activities.login.mvp.model;

/**
 * Created by Soumya Ranjan Sethy <sethy.soumyaranjan@gmail.com> on 10/10/17.
 */

public class Credentials {
    String emailAddress;
    String password;

    public Credentials(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
}