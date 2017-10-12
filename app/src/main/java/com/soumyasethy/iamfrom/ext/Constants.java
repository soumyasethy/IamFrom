package com.soumyasethy.iamfrom.ext;

public final class Constants {

    public static final String HTTP_CACHE_DIR = "okhttp_cache";
    public static final String LOGTAG = "IamFrom";
    public static final int HTTP_CACHE_SIZE = 10 * 1024 * 1024; //10MB
    public static final String BASE_URL = "http://85.25.196.222:6013/";
    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    private Constants() {
    }
}
