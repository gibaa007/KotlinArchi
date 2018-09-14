//package com.g7.gibaa007.api;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.util.Log;
//import android.view.ViewGroup;
//
//import com.g7.gibaa007.activity.BaseBTActivity;
//import com.g7.gibaa007.activity.CommentListActivity;
//import com.g7.gibaa007.activity.GroupDetailActivity;
//import com.g7.gibaa007.activity.HomeActivity;
//import com.g7.gibaa007.activity.ListLikersActivity;
//import com.g7.gibaa007.activity.NewImageQuickeeActivity;
//import com.g7.gibaa007.activity.NewVideoQuickeeActivity;
//import com.g7.gibaa007.activity.NotificationActivity;
//import com.g7.gibaa007.activity.ProfileViewActivity;
//import com.g7.gibaa007.activity.QClipsActivity;
//import com.g7.gibaa007.activity.QuickeeDetailActivity;
//import com.g7.gibaa007.activity.SearchActivity;
//import com.g7.gibaa007.adapter.MediaAdapter;
//import com.g7.gibaa007.adapter.QuickeesAdapter;
//import com.g7.gibaa007.adapter.QuickersAdapter;
//import com.g7.gibaa007.fragment.FavouriteFragment;
//import com.g7.gibaa007.fragment.GroupsFragment;
//import com.g7.gibaa007.fragment.NotificationFragment;
//import com.g7.gibaa007.fragment.QuickeesFragment;
//import com.g7.gibaa007.fragment.QuickersFragment;
//import com.g7.gibaa007.fragment.QuickingFragment;
//import com.g7.gibaa007.fragment.RepostFragment;
//import com.g7.gibaa007.modal.LiveDataViewModal;
//import com.g7.gibaa007.pojo.AdPojo;
//import com.g7.gibaa007.pojo.CommentPojo;
//import com.g7.gibaa007.pojo.CommonPojo;
//import com.g7.gibaa007.pojo.DashboardPojo;
//import com.g7.gibaa007.pojo.GroupPojo;
//import com.g7.gibaa007.pojo.NotifPojo;
//import com.g7.gibaa007.pojo.NotificationPojo;
//import com.g7.gibaa007.pojo.PostPojo;
//import com.g7.gibaa007.pojo.ProfilePojo;
//import com.g7.gibaa007.pojo.QuickerPojo;
//import com.g7.gibaa007.utils.AppConfig;
//import com.g7.gibaa007.utils.CallBack;
//import com.g7.gibaa007.utils.CommonActions;
//import com.g7.gibaa007.utils.Print;
//import com.g7.gibaa007.utils.SingletonHolder;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import kotlin.jvm.JvmStatic;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
//import static android.content.ContentValues.TAG;
//
///**
// * Created by gibaa007 on 1/3/18.
// */
//
//public class WebServices extends LiveDataViewModal {
//
//
//    private CommonActions commonActions = new CommonActions();
//    private APIService mAPIService = ApiUtils.getAPIService();
//
//
//    public WebServices() {
//    }
//
//    SharedPreferences prefs = SingletonHolder.getInstance().getPrefs();
//    String user_id = prefs.getString("id", "");
//    String access_token = prefs.getString("accessToken", "");
//    String refresh_token = prefs.getString("refreshToken", "");
//
//    Map<String, String> headers = new HashMap<>();
//
//
//    private void addHeader() {
//        headers.clear();
//        headers.put("x-access-token", access_token);
//    }
//
//
//    public WebServices(Context context) {
//        commonActions = new CommonActions(context);
//        if (commonActions.isNetworkConnected()) {
//            prefs = SingletonHolder.getInstance().getPrefs();
//            user_id = prefs.getString("id", "");
//            access_token = prefs.getString("accessToken", "");
//            refresh_token = prefs.getString("refreshToken", "");
//
//            mAPIService = ApiUtils.getAPIService();
//            headers.clear();
//            headers.put("x-access-token", access_token);
//
//        } else {
//            commonActions.showFailureSnackToast(((ViewGroup) ((Activity) context).findViewById(android.Repo.id.content)).getChildAt(0), "Please connect to internet");
//            return;
//
//        }
//    }
//
//    private void addRefreshToken() {
//        headers.put("refresh-token", refresh_token);
//    }
//
//
//    boolean isExpired() {
//        return !SingletonHolder.getInstance().isAuthenticated();
//    }
//
//
//    /**
//     * api for login
//     */
//    @JvmStatic
//    public void login(final String name, final String pass, final CallBack callBack) {
//        mAPIService.login(name, pass, "android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//                        callBack.onTaskCompleted(post, AppConfig.LOGIN, post.getStatus());
//                    }
//                });
//    }
//
//    /**
//     * api for social login
//     */
//    @JvmStatic
//    public void checkSocialLogin(final String id, final String name, final String email, final CallBack callBack) {
//        mAPIService.socialLogin(id, email, name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//                        callBack.onTaskCompleted(post, AppConfig.LOGIN, post.getStatus());
//                    }
//                });
//    }
//
//    /**
//     * api for forgot password
//     */
//    @JvmStatic
//    public void forgot(final String name, final CallBack callBack) {
//        mAPIService.forgot(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//                        callBack.onTaskCompleted(post, AppConfig.FORGOT, post.getStatus());
//                    }
//                });
//    }
//
//
//    /**
//     * api for change password
//     */
//    @JvmStatic
//    public void changePassword(final String password, final CallBack callBack) {
//        mAPIService.changePassword(password, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//
//
//                        if (isExpired()) {
//                            addRefreshToken();
//                            changePassword(password, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.FUNCTION, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for get profile
//     */
//    @JvmStatic
//    public void getProfile(final CallBack callBack) {
//        mAPIService.getProfile(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getProfile(callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.GET_PROFILES, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for get profile
//     */
//    @JvmStatic
//    public void getUserProfile(final CallBack callBack, final int userId) {
//        mAPIService.getProfile(headers, userId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getUserProfile(callBack, userId);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.GET_PROFILES, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to update device token
//     */
//    @JvmStatic
//    public void updateDeviceToken(String id) {
//        mAPIService.updateDeviceToken(id, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            updateDeviceToken(id);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for get quickee detail
//     */
//    @JvmStatic
//    public void getQuickeeDetail(final int q_id, final QuickeeDetailActivity callBack) {
//        mAPIService.getQuickeeDetail(q_id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<PostPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<PostPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getQuickeeDetail(q_id, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.DETAILS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for get quickee detail
//     */
//    @JvmStatic
//    public void updateImageCount(final int q_id) {
//        mAPIService.getQuickeeDetail(q_id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<PostPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<PostPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            updateImageCount(q_id);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for get dashboard
//     */
//    @JvmStatic
//    public void getDashboard(final HomeActivity callBack) {
//        mAPIService.getDashboard(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<DashboardPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<DashboardPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getDashboard(callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onNextRx(post, AppConfig.DASHBOARD, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to get notification settings
//     */
//    @JvmStatic
//    public void getNotification(final NotificationActivity callBack) {
//        mAPIService.getNotification(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<NotificationPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<NotificationPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getNotification(callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onNextRx(post, AppConfig.DASHBOARD, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to set Notification settings
//     *
//     * @param notificationPojo
//     */
//    @JvmStatic
//    public void setNotification(final NotificationPojo notificationPojo) {
//        mAPIService.setNotification(notificationPojo.isStartFollowingMe() + "", notificationPojo.isQuickeeIsReposted() + "", notificationPojo.isQuickeeAsFavorite() + "", notificationPojo.isCommentsOnQuickee() + "", notificationPojo.isPostQuickee() + "", notificationPojo.isNewsAndHappenings() + "", notificationPojo.isNewProductLaunches() + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            setNotification(notificationPojo);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for sign up
//     */
//    @JvmStatic
//    public void signUp(final String name, final String email, final String pass, final boolean verify, final CallBack callBack) {
//        mAPIService.signUp(name, email, pass, verify ? "Y" : "N", "android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//                        callBack.onTaskCompleted(post, AppConfig.SIGNUP, post.getStatus());
//                    }
//                });
//    }
//
//
//    /**
//     * api for quickees
//     */
//
//    @JvmStatic
//    public void quickees(final String pageNo, final String search, final QuickeesFragment callBack) {
//        mAPIService.quickees(pageNo, search, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            quickees(pageNo, search, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKEES, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for myQuickees
//     */
//
//    @JvmStatic
//    public void myQuickees(final String pageNo, final String search) {
//        mAPIService.myQuickees(pageNo, search, "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            myQuickees(pageNo, search);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            getList().setValue(post);
////                            callBack.onTaskCompleted(post, AppConfig.QUICKEES, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for favorites
//     */
//
//    @JvmStatic
//    public void favorites(final String pageNo, final String search, final FavouriteFragment callBack) {
//        mAPIService.favourites(pageNo, search, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            favorites(pageNo, search, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKEES, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for list_users
//     */
//
//    @JvmStatic
//    public void list_users(final int type, final String pageNo, final String bq_id, final ListLikersActivity callBack) {
//        String function = "";
//        switch (type) {
//            case 0:
//                function = "get_favoriteusers";
//                break;
//            case 1:
//                function = "get_repostedusers";
//                break;
//        }
//        mAPIService.functionUsers(function, bq_id + "", pageNo, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<ProfilePojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<ProfilePojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            list_users(type, pageNo, bq_id, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKERS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for user profile
//     */
//
//    @JvmStatic
//    public void userProfileAPIs(final int type, final int pageNo, final String u_id, final ProfileViewActivity callBack) {
//        String function = "";
//        switch (type) {
//            case 0:
//                function = "getProfileQuickees";
//                break;
//            case 1:
//                function = "getUserFavorites";
//                break;
//            case 2:
//                function = "getProfileQuickers";
//                break;
//            case 3:
//                function = "getProfileQuicking";
//                break;
//        }
//        if (type == 0 || type == 1)
//            mAPIService.userQuickeeAPIs(function, u_id + "", pageNo, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e(TAG, e.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                            if (isExpired()) {
//                                addRefreshToken();
//                                userProfileAPIs(type, pageNo, u_id, callBack);
//                                Print.e("Retry");
//                                return;
//                            } else {
//                                Print.e("Success");
//                                callBack.onTaskCompletedQuickees(post, AppConfig.QUICKEES, post.getStatus());
//                            }
//                        }
//                    });
//        else
//            mAPIService.userQuickerAPIs(function, u_id + "", pageNo, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<CommonPojo<ArrayList<QuickerPojo>>>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e(TAG, e.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(CommonPojo<ArrayList<QuickerPojo>> post) {
//                            if (isExpired()) {
//                                addRefreshToken();
//                                userProfileAPIs(type, pageNo, u_id, callBack);
//                                Print.e("Retry");
//                                return;
//                            } else {
//                                Print.e("Success");
//                                callBack.onTaskCompletedQuickers(post, AppConfig.QUICKERS, post.getStatus());
//                            }
//                        }
//                    });
//
//    }
//
//
//    /**
//     * api for search
//     */
//
//    @JvmStatic
//    public void searchAPIs(final int type, final int pageNo, final String search, final SearchActivity callBack) {
//        String function = "";
//        switch (type) {
//            case 1:
//                function = "searchQuickees";
//                break;
//            case 2:
//                function = "SearchQuickers";
//                break;
//        }
//        if (type == 1)
//            mAPIService.searchQuickeeAPIs(function, search, pageNo, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e(TAG, e.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                            if (isExpired()) {
//                                addRefreshToken();
//                                searchAPIs(type, pageNo, search, callBack);
//                                Print.e("Retry");
//                                return;
//                            } else {
//                                Print.e("Success");
//                                callBack.onTaskCompletedQuickees(post, AppConfig.QUICKEES, post.getStatus());
//                            }
//                        }
//                    });
//        else
//            mAPIService.searchQuickerAPIs(function, search, pageNo, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<CommonPojo<ArrayList<QuickerPojo>>>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e(TAG, e.getMessage());
//                        }
//
//                        @Override
//                        public void onNext(CommonPojo<ArrayList<QuickerPojo>> post) {
//                            if (isExpired()) {
//                                addRefreshToken();
//                                searchAPIs(type, pageNo, search, callBack);
//                                Print.e("Retry");
//                                return;
//                            } else {
//                                Print.e("Success");
//                                callBack.onTaskCompletedQuickers(post, AppConfig.QUICKERS, post.getStatus());
//                            }
//                        }
//                    });
//
//    }
//
//
//    /**
//     * api for reposted
//     */
//
//    @JvmStatic
//    public void reposted(final String pageNo, final String search, final RepostFragment callBack) {
//        mAPIService.myQuickees(pageNo, search, "repost", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            reposted(pageNo, search, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKEES, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for notification
//     */
//
//    @JvmStatic
//    public void notif(final String pageNo, final NotificationFragment callBack) {
//        mAPIService.notif(pageNo, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<NotifPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<NotifPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            notif(pageNo, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.NOTIFICATIONS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for quickers
//     */
//
//    @JvmStatic
//    public void quickers(final int pageNo, final String search, final QuickersFragment callBack) {
//        mAPIService.quickers(pageNo + "", search, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<QuickerPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<QuickerPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            quickers(pageNo, search, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKERS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for quicking
//     */
//
//    @JvmStatic
//    public void quicking(final int pageNo, final String search, final QuickingFragment callBack) {
//        mAPIService.quicking(pageNo + "", search, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<QuickerPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<QuickerPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            quicking(pageNo, search, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKERS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for group members
//     */
//
//    @JvmStatic
//    public void groupMembers(final int id, final GroupDetailActivity callBack) {
//        mAPIService.groupMembers(id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<QuickerPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<QuickerPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            groupMembers(id, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.QUICKERS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for get groups
//     */
//
//    @JvmStatic
//    public void groups(final int pageNo, final String id, final GroupsFragment callBack) {
//        mAPIService.getMyGroups(id, pageNo + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<GroupPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<GroupPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            groups(pageNo, id, callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.GROUPS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
////    //OTHER USERS
////
////    /**
////     * api for myQuickees
////     */
////
////    @JvmStatic
////    public void myQuickees(final String pageNo, final String search, final MyQuickeesFragment callBack) {
////        mAPIService.myQuickees(pageNo, search, "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
////                    @Override
////                    public void onCompleted() {
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.e(TAG, e.getMessage());
////                    }
////
////                    @Override
////                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
////                        if (isExpired()) {
////                            addRefreshToken();
////                            myQuickees(pageNo, search, callBack);
////                            Print.e("Retry");
////                            return;
////                        } else {
////                            Print.e("Success");
////                            callBack.onTaskCompleted(post, AppConfig.QUICKEES, post.getStatus());
////                        }
////                    }
////                });
////    }
////
////
////    /**
////     * api for favorites
////     */
////
////    @JvmStatic
////    public void favorites(final String pageNo, final String search, final FavouriteFragment callBack) {
////        mAPIService.favourites(pageNo, search, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
////                    @Override
////                    public void onCompleted() {
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.e(TAG, e.getMessage());
////                    }
////
////                    @Override
////                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
////                        if (isExpired()) {
////                            addRefreshToken();
////                            favorites(pageNo, search, callBack);
////                            Print.e("Retry");
////                            return;
////                        } else {
////                            Print.e("Success");
////                            callBack.onTaskCompleted(post, AppConfig.QUICKEES, post.getStatus());
////                        }
////                    }
////                });
////    }
//
//
//    /**
//     * api for update profile
//     */
//
//    @JvmStatic
//    public void updateProfile(final CallBack callBack, final ProfilePojo profilePojo) {
//        mAPIService.updateProfile(profilePojo.getEmail(), profilePojo.getNickname(), profilePojo.getAddress(), profilePojo.getAboutMe(), profilePojo.getVerifyMe() ? "Y" : "N", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//
//                        if (isExpired()) {
//                            addRefreshToken();
//                            updateProfile(callBack, profilePojo);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.UPDATE_PROFILE, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for update fb
//     */
//
//    @JvmStatic
//    public void updateFB(final ProfilePojo profilePojo) {
//        mAPIService.updateFb(profilePojo.getFacebookId(), profilePojo.getFacebookName(), headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ProfilePojo> post) {
//
//                        if (isExpired()) {
//                            addRefreshToken();
//                            updateFB(profilePojo);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api for post Quickee
//     */
//
//    @JvmStatic
//    public void postQuickee(final String privacy, final String ids, final String title, final String images, final String video, final String imageSize, final BaseBTActivity baseBTActivity) {
//        addHeader();
//        mAPIService.postQuickee(privacy.toLowerCase(), ids, title, images, video, imageSize, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//
//                        if (isExpired()) {
//                            addRefreshToken();
//                            postQuickee(privacy, ids, title, images, video, imageSize, baseBTActivity);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            commonActions.customAlertDialogFinish(post.getMessage(), baseBTActivity);
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api for update profile pic
//     */
//
//    @JvmStatic
//    public void updateProfilePic(final CallBack callBack) {
//        mAPIService.updateProfilePic(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//
//                        if (isExpired()) {
//                            addRefreshToken();
//                            updateProfilePic(callBack);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            callBack.onTaskCompleted(post, AppConfig.UPDATE_PROFILE, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to function like,repost,delete,report
//     * 1.like
//     * 2.repost
//     * 3.delete
//     * 4.report
//     */
//    @JvmStatic
//    public void function(final int bq_id, final int q_id, final int type, final QuickeesAdapter adapter) {
//        String function = "";
//        switch (type) {
//            case 0:
//                function = "toggle_favorite";
//                break;
//            case 1:
//                function = "repost_quickee";
//                break;
//            case 2:
//                function = "deleteQuickee";
//                break;
//            case 3:
//                function = "reportQuickee";
//                break;
//        }
//        mAPIService.function(function, bq_id + "", q_id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<PostPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<PostPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            function(bq_id, q_id, type, adapter);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            if (type == 1) {
////                                commonActions.showActionSnackToast(((ViewGroup) ((Activity) con).findViewById(android.Repo.id.content)).getChildAt(0), post.getMessage());
//                                commonActions.showToast(post.getMessage());
//                                if (adapter != null)
//                                    adapter.add(0, post.getData());
//                            } else if (type == 3) {
////                                commonActions.showActionSnackToast(((ViewGroup) ((Activity) con).findViewById(android.Repo.id.content)).getChildAt(0), post.getMessage());
//                                commonActions.showToast(post.getMessage());
//                            }
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to get ad url
//     */
//    @JvmStatic
//    public void adUrl(final int id, final String quickee_id, final QuickeesAdapter quickeesAdapter) {
//        mAPIService.adUrl(id + "", quickee_id, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<AdPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<AdPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            adUrl(id, quickee_id, quickeesAdapter);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            quickeesAdapter.playAd(post.getData().getVideoUrl());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to get ad url
//     */
//    @JvmStatic
//    public void adUrl(final int id, final String quickee_id, final MediaAdapter quickeesAdapter) {
//        mAPIService.adUrl(id + "", quickee_id, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<AdPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<AdPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            adUrl(id, quickee_id, quickeesAdapter);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            quickeesAdapter.playAd(post.getData().getVideoUrl());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to toggle follow
//     */
//    @JvmStatic
//    public void follow(final int id) {
//        mAPIService.follow(id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            follow(id);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to get groups
//     */
//    @JvmStatic
//    public void getGroups(final int id, final QuickersAdapter quickersAdapter) {
//        mAPIService.getMyGroups(id + "", "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<GroupPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<GroupPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getGroups(id, quickersAdapter);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            quickersAdapter.getGroups(post, AppConfig.GROUPS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to get my Quickee Videos
//     */
//    @JvmStatic
//    public void getQuickeeVideos(final QClipsActivity qClipsActivity) {
//        mAPIService.getQuickeeVideos(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<PostPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<PostPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getQuickeeVideos(qClipsActivity);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            qClipsActivity.getQuickeeVideos(post.getData());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to get groups
//     */
//    @JvmStatic
//    public void getGroups(final NewVideoQuickeeActivity con) {
//        mAPIService.getMyGroups(0 + "", "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<GroupPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<GroupPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getGroups(con);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            con.getGroups(post, AppConfig.GROUPS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to logout
//     */
//    @JvmStatic
//    public void logout() {
//        mAPIService.logout(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            logout();
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to get groups
//     */
//    @JvmStatic
//    public void getGroups(final NewImageQuickeeActivity con) {
//        mAPIService.getMyGroups(0 + "", "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<GroupPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<GroupPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getGroups(con);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            con.getGroups(post, AppConfig.GROUPS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to add to groups
//     */
//    @JvmStatic
//    public void addToGroups(final int id, final String groups, final QuickersAdapter quickersAdapter) {
//        mAPIService.addToGroups(id + "", groups, headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            addToGroups(id, groups, quickersAdapter);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * api to delete comment
//     */
//    @JvmStatic
//    public void deleteComment(final int id) {
//        mAPIService.deleteComment(id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            deleteComment(id);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to add comment
//     */
//    @JvmStatic
//    public void addComment(final int id, final String comment, final int c_id, final CommentListActivity commentListActivity) {
//        mAPIService.addComment(id + "", comment, c_id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<CommentPojo>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<CommentPojo> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            addComment(id, comment, c_id, commentListActivity);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            commentListActivity.onTaskCompletedAddComments(post, c_id == 0 ? AppConfig.ADD_COMMENTS : AppConfig.EDIT_COMMENT, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
//    /**
//     * api to get comment
//     */
//    @JvmStatic
//    public void getComments(final int post_id, final int pageNo, final CommentListActivity commentListActivity) {
//        mAPIService.comments(pageNo + "", post_id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<CommonPojo<ArrayList<CommentPojo>>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(CommonPojo<ArrayList<CommentPojo>> post) {
//                        if (isExpired()) {
//                            addRefreshToken();
//                            getComments(post_id, pageNo, commentListActivity);
//                            Print.e("Retry");
//                            return;
//                        } else {
//                            Print.e("Success");
//                            commentListActivity.onTaskCompletedComments(post, AppConfig.GET_COMMENTS, post.getStatus());
//                        }
//                    }
//                });
//    }
//
//
////    /**
////     * api to get media
////     */
////    @JvmStatic
////    public void getMedia(final int post_id, final QuickeeDetailActivity quickeeDetailActivity) {
////        mAPIService.media( post_id + "", headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<CommonPojo<ArrayList<ImagePojo>>>() {
////                    @Override
////                    public void onCompleted() {
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.e(TAG, e.getMessage());
////                    }
////
////                    @Override
////                    public void onNext(CommonPojo<ArrayList<ImagePojo>> post) {
////                        if (isExpired()) {
////                            addRefreshToken();
////                            getMedia(post_id,  quickeeDetailActivity);
////                            Print.e("Retry");
////                            return;
////                        } else {
////                            Print.e("Success");
////                            quickeeDetailActivity.onTaskCompletedComments(post, AppConfig.GET_COMMENTS, post.getStatus());
////                        }
////                    }
////                });
////    }
//
//
////    /**
////     * api for home
////     */
////    public String home() {
////        mAPIService.home(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Subscriber<CommonPojo<ProfilePojo>>() {
////                    @Override
////                    public void onCompleted() {
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Log.e(TAG, e.getMessage());
////                    }
////
////                    @Override
////                    public void onNext(CommonPojo<ProfilePojo> post) {
////                        if (isExpired()) {
////                            addRefreshToken();
////                            home();
////                            Print.e("Retry");
////                            return;
////                        } else Print.e("Success");
////                    }
////                });
////
////        return null;
////    }
//
//}
