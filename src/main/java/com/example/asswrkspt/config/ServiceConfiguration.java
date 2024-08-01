package com.example.asswrkspt.config;

public class ServiceConfiguration {
    private String activeProfile;
    private String appName;
    private static String ACTIVE_PROFILE;
    private static String APP_NAME;

    public static String getProfile() {
        return ACTIVE_PROFILE;
    }

    public static String getApplicationName() {
        return APP_NAME;
    }

    public static void init(String activeProfile, String appName) {
        ACTIVE_PROFILE = activeProfile;
        APP_NAME = appName;
    }

    public String getActiveProfile() {
        return this.activeProfile;
    }

    public String getAppName() {
        return this.appName;
    }

    public ServiceConfiguration(final String activeProfile, final String appName) {
        this.activeProfile = activeProfile;
        this.appName = appName;
    }
}
