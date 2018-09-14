//  Created by Gibin  on 14/09/2015.
//  Copyright (c) 2015 gibaa007. All rights reserved.
package com.g7.gibaa007.utils;


public class AppConfig {

    public static final String INTENTDATA = "_data";
    public static final String TAG = "G7";
    //                public static final String BASE_URL = BuildConfig.DEBUG ? "http://newagesme.com:5540/" : "http://10.10.10.91:5550/";
    public static final String BASE_URL = "http://10.10.10.86:7000/";
    public static final String BASE_URL_CLIENT = BASE_URL + "users/";
    public static final String BASE_CMS_URL = BASE_URL + "/cms/";


    //PERMISSION CONSTANTS
    public final static int MY_PERMISSIONS_REQUEST_CAMERA = 80;
    public final static int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 81;

    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;
    public static final int REFRESH_ACTIVITY = 0x4;
    public static final int UPLOAD_VIDEO = 0x5;
    public static final int MULTISELECTOR = 0x6;
    public static final int SINGLESELECTOR = 0x7;


    //GCM CONSTANTS

    public static final String PROPERTY_REG_ID = "registration_id";

    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    //Splash
    public final static long SPLASH_TIME_OUT = 3000;
    public static String DEVICE_ID = "device_id";
    public static String LOGGED_VIA = "logged_via";

    //SHARED PREFERENCE
    public static String SHARED_VALUE = "ss_pref";

    //USER CREDENTIALS
    //API CONSTANTS
    public static int GET = 0;
    public static int POST_WITH_DATA = 1;//Files
    public static int POST_WITH_JSON = 2;//JSON
    public static int DELETE = 3;//DELETE
    public static int ACCESS_TOKEN = 4;//TOKEN API

    public static String TEMP_PROFILE_PHOTO_FILE_NAME = "temp_profile_photo.jpg";

    //Webservice status
    public static int FAIL = 0;
    public static int SUCCESS = 1;
    public static int FAILURE_INTERNET = 2;
    public static int SESSION_EXPIRED = 3;
    public static int FAILURE_OTHER = 4;



    //WEB SERVICE METHOD CONSTANTS
    public final static int LOGIN = 100;
    public final static int FORGOT = 101;
    public final static int SIGNUP = 102;

    //CMS CONSTANTS
    public static int TERMS_PAGE = 01;
    public static int PRIVACY_PAGE = 02;
    public static int FAQ = 03;


    //IMAGE CHANGE
    public static int NO_CHANGE = 0;
    public static int DEFAULT_PIC_ADDED = 1;
    public static int NEW_PIC_ADDED = 2;

    public final static String legalDisclaimers = "eula";
    public final static String termsOfUse = "terms?device=app";
    public final static String privacyPolicy = "privacy?device=app";


    /*
     * You should replace these values with your own. See the README for details
     * on what to fill in.
     */
    public static final String COGNITO_POOL_ID = "";

    /*
     * Region of your Cognito identity pool ID.
     */
    public static final String COGNITO_POOL_REGION = "";

    /*
     * Note, you must first create a bucket using the S3 console before running
     * the sample (https://console.aws.amazon.com/s3/). After creating a bucket,
     * put it's name in the field below.
     */
    public static final String BUCKET_NAME = "";

    /*
     * Region of your bucket.
     */
    public static final String BUCKET_REGION = "";

}
