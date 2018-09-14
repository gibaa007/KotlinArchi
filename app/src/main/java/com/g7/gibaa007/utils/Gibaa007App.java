package com.g7.gibaa007.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.g7.gibaa007.api.APIService;
import com.g7.gibaa007.api.AuthorizationInterceptor;
import com.g7.gibaa007.pojo.SessionPojo;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by gibaa007 on 10/7/17.
 */

public class Gibaa007App extends MultiDexApplication {
    private Context context;
//    private Socket mSocket;

    private static Session session;
    private static APIService apiService;
    private static AuthenticationListener authenticationListener;

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;


    private static String PREF_KEY_IS_LOGIN = "authenticated";

    private static String PREF_KEY_ID = "id";
    private static String PREF_KEY_FULL_NAME = "fname";
//    private static String PREF_KEY_PROFILE_IMAGE = "d";
//    private static String PREF_KEY_EMAIL = "e";
//    private static String PREF_KEY_COUNTRY_CODE = "f";
//    private static String PREF_KEY_PHONE = "g";
//    private static String PREF_KEY_ADDRESS = "h";
//    private static String PREF_KEY_LATITUDE = "i";
//    private static String PREF_KEY_LONGITUDE = "j";
//    private static String PREF_KEY_EMAIL_VERIFIED = "k";
//    private static String PREF_KEY_PHONE_VERIFIED = "l";

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
        context = getApplicationContext();
        pref = SingletonHolder.getInstance().getPrefs();
        editor = SingletonHolder.getInstance().getEditor();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }


    public static Session getSession() {
//        if (session == null) {
//            session = new Session() {
//
//
//                @Override
//                public void setAuthenticated(boolean b) {
//
//                }
//
//                @Override
//                public boolean getAuthenticated() {
//                    return SingletonHolder.getInstance().isLoggedIn();
//                }
//
//                @Override
//                public void setCredentials(SessionPojo session) {
//                    editor = pref.edit();
//                    editor.putBoolean(PREF_KEY_IS_LOGIN, true);
//                    editor.putInt(PREF_KEY_ID, session.getId());
//                    editor.putString(PREF_KEY_FULL_NAME, session.getFirstName());
////                    editor.putString(PREF_KEY_FIRST_NAME, session.getFirstName());
////                    editor.putString(PREF_KEY_LAST_NAME, session.getLastName());
////                    editor.putString(PREF_KEY_PROFILE_IMAGE, session.getProfileImage());
////                    editor.putString(PREF_KEY_EMAIL, session.getEmail());
////                    editor.putString(PREF_KEY_COUNTRY_CODE, session.getCountryCode());
////                    editor.putString(PREF_KEY_PHONE, session.getPhone());
////                    editor.putString(PREF_KEY_ADDRESS, session.getAddress());
////                    editor.putString(PREF_KEY_LATITUDE, Double.toString(session.getLatitude()));
////                    editor.putString(PREF_KEY_LONGITUDE, Double.toString(session.getLongitude()));
////                    editor.putBoolean(PREF_KEY_EMAIL_VERIFIED, session.isEmailVerified());
////                    editor.putBoolean(PREF_KEY_PHONE_VERIFIED, session.isPhoneVerified());
//                    editor.apply();
//                }
//
//                @Override
//                public SessionPojo getCredentials() {
//                    SessionPojo session = new SessionPojo();
//                    session.setId(pref.getInt(PREF_KEY_ID, 0));
//                    session.setFirstName(pref.getString(PREF_KEY_FULL_NAME, ""));
////                    session.setLastName(pref.getString(PREF_KEY_LAST_NAME, ""));
////                    session.setProfileImage(pref.getString(PREF_KEY_PROFILE_IMAGE, ""));
////                    session.setEmail(pref.getString(PREF_KEY_EMAIL, ""));
////                    session.setCountryCode(pref.getString(PREF_KEY_COUNTRY_CODE, ""));
////                    session.setPhone(pref.getString(PREF_KEY_PHONE, ""));
////                    session.setAddress(pref.getString(PREF_KEY_ADDRESS, ""));
////                    try {
////                        session.setLatitude(Double.parseDouble(pref.getString(PREF_KEY_LATITUDE, "0.0")));
////                        session.setLongitude(Double.parseDouble(pref.getString(PREF_KEY_LONGITUDE, "0.0")));
////                    } catch (Exception e) {
////                        session.setLatitude(0.0);
////                        session.setLongitude(0.0);
////                    }
////                    session.setEmailVerified(pref.getBoolean(PREF_KEY_EMAIL_VERIFIED, false));
////                    session.setPhoneVerified(pref.getBoolean(PREF_KEY_PHONE_VERIFIED, false));
//                    return session;
//                }
//
//                @Override
//                public void clearCredentials() {
///*                    editor = pref.edit();
//                    editor.clear();
//                    editor.apply();*/
//                    if (authenticationListener != null) {
//                        setAuthenticated(false);
////                        con.startActivity(new Intent(con, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                    }
//                }
//            };
//        }
        return session;
    }

    public interface AuthenticationListener {
        void onUserLoggedOut();
    }

    public static void setAuthenticationListener(AuthenticationListener listener) {
        authenticationListener = listener;
    }

    public static APIService getClientApiService() {
        if (apiService == null) {
            apiService = provideRetrofit(AppConfig.BASE_URL_CLIENT).create(APIService.class);
        }
        return apiService;
    }

    public static APIService getBaseApiService() {
        if (apiService == null) {
            apiService = provideRetrofit(AppConfig.BASE_URL).create(APIService.class);
        }
        return apiService;
    }

    public static APIService getGoogleMapsApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build().create(APIService.class);
    }

    private static Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(provideOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);


        okHttpClientBuilder.addInterceptor(new AuthorizationInterceptor(getSession()));
        //okHttpClientBuilder.addInterceptor(new LogInterceptor());
        return okHttpClientBuilder.build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context = base;
//        connectSocket();
    }


//    public Socket connectSocket() {
//        try {
//            mSocket = IO.socket(AppConfig.BASE_URL_CLIENT);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return mSocket;
//    }
}
