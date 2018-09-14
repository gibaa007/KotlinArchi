package com.g7.gibaa007.utils;

import android.content.SharedPreferences;

/**
 * Created by gibaa007 on 17/3/17.
 */

public class SingletonHolder {

    String accessToken;
    String refreshToken;
    String password;
    boolean authenticated;
    boolean loggedIn;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    private static SingletonHolder instance;

    public static SingletonHolder getInstance() {
        if (instance == null) {
            instance = new SingletonHolder();
        }
        return instance;
    }


//    public String getPassword() {
//        return getPrefs().getString(AppConfig.USER_PASSWORD, "");
//    }
//
//    public void setPassword(String password) {
//        getEditor().putString(AppConfig.USER_PASSWORD, password);
//        getEditor().commit();
//    }

    public boolean isAuthenticated() {
        boolean auth = getPrefs().getBoolean("authenticated", false);
        return auth;
    }

    public void setAuthenticated(boolean authenticated) {
        getEditor().putBoolean("authenticated", authenticated);
        getEditor().commit();

    }

    public boolean isLoggedIn() {
        boolean login = getPrefs().getBoolean("loggedIn", false);
        return login;
    }

    public void setLoggedIn(boolean loggedIn) {
        getEditor().putBoolean("loggedIn", loggedIn);
        getEditor().commit();
    }

//    public void setProfilePojo(ProfilePojo profilePojo) {
//        this.profilePojo = profilePojo;
//        getEditor().putString(AppConfig.USER_FULL_NAME, profilePojo.getUsername());
//        getEditor().putString(AppConfig.USER_PROFILE_PIC, profilePojo.getImage());
//        getEditor().commit();
//        setLoggedIn(true);
//    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        getEditor().putString("accessToken", accessToken);
        getEditor().commit();
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        getEditor().putString("refreshToken", refreshToken);
        getEditor().commit();
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public void setPrefs(SharedPreferences prefs) {
        this.prefs = prefs;
    }


    public String getAccessToken() {
        return getPrefs().getString("accessToken", "");
    }

    public void setToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        getEditor().putString("accessToken", accessToken);
        getEditor().putString("refreshToken", refreshToken);
        getEditor().commit();
    }

    public String getRefreshToken() {
        return getPrefs().getString("refreshToken", "");
    }
}
