package com.g7.gibaa007.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.g7.gibaa007.R;
import com.g7.gibaa007.activity.HomeActivity;
import com.g7.gibaa007.activity.LoginActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CommonActions {
    //    // We only need one instance of the clients and credentials provider
//    private  AmazonS3Client sS3Client;
//    private  CognitoCachingCredentialsProvider sCredProvider;
//    private  TransferUtility sTransferUtility;


    //For Notification
    public  NotificationManager mNotificationManager;
     NotificationCompat.Builder builder;
     int NOTIFICATION_ID = 111;


    Context con;

    public CommonActions(Context con) {
        // TODO Auto-generated constructor stub
        this.con = con;

    }

    public CommonActions() {
    }


    public  String dateConverter(String rawFormat, String requiredFormat, String rawDate) {
        SimpleDateFormat dt = new SimpleDateFormat(rawFormat);
        Date newStartDate = null;

        try {
            newStartDate = dt.parse(rawDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat date = new SimpleDateFormat(requiredFormat);
        return date.format(newStartDate);
    }


    public  String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        Formatter mFormatter = new Formatter();
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    public  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public  void customAlertDialog(String message, final Context activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        ;

        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public  boolean customAlertDialogreturn(String message, final Context activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        ;

        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.startActivity(new Intent(activity, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                }).show();
        return true;
    }


    public  final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


//    public static String random() {
//        return GenerateRandomString.randomString(16);
//    }


    public  boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }



    public String getString(int id) {
        return con.getResources().getString(id);
    }



//    public  String getCurrentTimeStamp() {
//        try {
//
//            long time = System.currentTimeMillis();
//            String currentDateTime = time + "";
//
//            return "a_" + currentDateTime + "_" + HomeActivity.userProfilePojo.getUserId();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            return null;
//        }
//    }
//
//    public void setImage(SimpleDraweeView view, String original, String thumb) {
//
////       .setImageURI(Uri.parse());
//    }

    public class GenerateRandomString {

        public  final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public  Random RANDOM = new Random();

        public String randomString(int len) {
            StringBuilder sb = new StringBuilder(len);

            for (int i = 0; i < len; i++) {
                sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
            }

            return sb.toString();
        }

    }

//    private void customPopUp(String title, String content, final JSONObject resposnseJSON, String accept) {
//        final Dialog dialog = new Dialog(FanSignUpActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View view = getLayoutInflater().inflate(Repo.layout.dialog_custom_popup, null);
//
//        TextView tv_title = (TextView) view.findViewById(Repo.id.tv_title);
//        TextView tv_content = (TextView) view.findViewById(Repo.id.tv_content);
//        TextView tv_accept = (TextView) view.findViewById(Repo.id.tv_accept);
//        TextView tv_reject = (TextView) view.findViewById(Repo.id.tv_reject);
//        tv_title.setText(title);
//        tv_content.setText(content);
//        tv_accept.setText(accept);
//        tv_reject.setText("cancel");
//        view.findViewById(Repo.id.tv_accept).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                JSONObject mergeData = null;
//                try {
//                    mergeData = resposnseJSON.getJSONObject("merge_data");
//                    JSONObject userData = resposnseJSON.getJSONObject("user_data");
//                    WebServices.mergeLogin(FanSignUpActivity.this, asyncCallBack, AppConfig.MERGE_ACCOUNTS, userData.getString("member_id"), mergeData.getString("media"), mergeData.getString("media_id"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        view.findViewById(Repo.id.tv_reject).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setContentView(view);
//        dialog.setCancelable(false);
//        dialog.show();
//    }


//    /**
//     * Gets an instance of CognitoCachingCredentialsProvider which is
//     * constructed using the given Context.
//     *
//     * @param context An Context instance.
//     * @return A default credential provider.
//     */
//    private  CognitoCachingCredentialsProvider getCredProvider(Context context) {
//        if (sCredProvider == null) {
//            sCredProvider = new CognitoCachingCredentialsProvider(
//                    context.getApplicationContext(),
//                    AppConfig.COGNITO_POOL_ID,
//                    Regions.fromName(AppConfig.COGNITO_POOL_REGION));
//        }
//        return sCredProvider;
//    }
//
//    /**
//     * Gets an instance of a S3 client which is constructed using the given
//     * Context.
//     *
//     * @param context An Context instance.
//     * @return A default S3 client.
//     */
//    public  AmazonS3Client getS3Client(Context context) {
//        if (sS3Client == null) {
//            sS3Client = new AmazonS3Client(getCredProvider(context.getApplicationContext()));
//            sS3Client.setRegion(Region.getRegion(Regions.fromName(AppConfig.BUCKET_REGION)));
//        }
//        return sS3Client;
//    }
//
//    /**
//     * Gets an instance of the TransferUtility which is constructed using the
//     * given Context
//     *
//     * @param context
//     * @return a TransferUtility instance
//     */
//    public  TransferUtility getTransferUtility(Context context) {
//        if (sTransferUtility == null) {
//            sTransferUtility = new TransferUtility(getS3Client(context.getApplicationContext()),
//                    context.getApplicationContext());
//        }
//
//        return sTransferUtility;
//    }


//    /*
//     * Begins to upload the file specified by the file path.
//     */
//    public void beginUpload(final File file, final String key) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                TransferUtility transferUtility = getTransferUtility(con);
//                // Starts a upload
//                TransferObserver observer = transferUtility.upload(AppConfig.BUCKET_NAME, key, file, CannedAccessControlList.PublicRead);
//                observer.setTransferListener(new TransferListener() {
//                    @Override
//                    public void onError(int id, Exception e) {
//                        Log.e(AppConfig.TAG, "Error during upload: " + id, e);
//                    }
//
//                    @Override
//                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                        Log.d(AppConfig.TAG, String.format("onProgressChanged: %d, total: %d, current: %d", id, bytesTotal, bytesCurrent));
//                        int progress = (int) ((double) bytesCurrent * 100 / bytesTotal);
//                        updateNotification(progress + "", "uploading", key);
//                    }
//
//                    @Override
//                    public void onStateChanged(int id, TransferState newState) {
//                        Log.e(AppConfig.TAG, "File Name: " + key);
//                        Log.d(AppConfig.TAG, "onStateChanged: " + id + ", " + newState);
//                        if (newState.toString().equals("IN_PROGRESS"))
//                            FileUploadNotification(key);
//                        else if (newState.toString().equals("FAILED"))
//                            failUploadNotification();
//                        else if (newState.toString().equals("COMPLETED")) {
//                            try {
//                                if (file.exists()) {
//                                    if (file.delete()) {
////                                        showToast("file Deleted");
//                                        System.out.println("file Deleted :" + file.getAbsolutePath());
//                                    } else {
//                                        System.out.println("file not Deleted :" + file.getAbsolutePath());
//                                        showToast("File Not Deleted!!!");
//                                    }
//                                }
////                                showToast("Upload Completed");
//                            } catch (Exception e) {
//                                showToast("Upload Completed with exception");
//                            }
//                        }
//                    }
//                });
//
//            }
//        }).start();
//
//    }


    /**
     * Converts number of bytes into proper scale.
     *
     * @param bytes number of bytes to be converted.
     * @return A string that represents the bytes in a proper scale.
     */
    public  String getBytesString(long bytes) {
        String[] quantifiers = new String[]{
                "KB", "MB", "GB", "TB"
        };
        double speedNum = bytes;
        for (int i = 0; ; i++) {
            if (i >= quantifiers.length) {
                return "";
            }
            speedNum /= 1024;
            if (speedNum < 512) {
                return String.format("%.2f", speedNum) + " " + quantifiers[i];
            }
        }
    }

    /**
     * Copies the data from the passed in Uri, to a new file for use with the
     * Transfer Service
     *
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    public  File copyContentUriToFile(Context context, Uri uri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(uri);
        File copiedData = new File(context.getDir("SampleImagesDir", Context.MODE_PRIVATE), UUID
                .randomUUID().toString());
        copiedData.createNewFile();

        FileOutputStream fos = new FileOutputStream(copiedData);
        byte[] buf = new byte[2046];
        int read = -1;
        while ((read = is.read(buf)) != -1) {
            fos.write(buf, 0, read);
        }

        fos.flush();
        fos.close();

        return copiedData;
    }

//    /*
//     * Fills in the map with information in the observer so that it can be used
//     * with a SimpleAdapter to populate the UI
//     */
//    public  void fillMap(Map<String, Object> map, TransferObserver observer, boolean isChecked) {
//        int progress = (int) ((double) observer.getBytesTransferred() * 100 / observer
//                .getBytesTotal());
//        map.put("id", observer.getId());
//        map.put("checked", isChecked);
//        map.put("fileName", observer.getAbsoluteFilePath());
//        map.put("progress", progress);
//        map.put("bytes",
//                getBytesString(observer.getBytesTransferred()) + "/"
//                        + getBytesString(observer.getBytesTotal()));
//        map.put("state", observer.getState());
//        map.put("percentage", progress + "%");
//    }

//    public  void customAlertDialogFinish(String message, final Activity activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
//        alertDialogBuilder.setMessage(message);
//
//        alertDialogBuilder.setPositiveButton("Ok", (arg0, arg1) -> {
//            activity.setResult(AppConfig.REFRESH_ACTIVITY, new Intent());
//            activity.finish();
//        });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.show();
//    }

//    public void customAlertDialogPop(String message, final Fragment activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con, R.style.AlertDialogCustom);
//        alertDialogBuilder.setMessage(message);
//
//        alertDialogBuilder.setPositiveButton("Ok", (arg0, arg1) -> {
//            activity.onResume();
//            activity.getFragmentManager().popBackStack();
//        });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.show();
//    }


//    public void customAlertDialog(String message, final Activity activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
//        alertDialogBuilder.setMessage(message);
//
//        alertDialogBuilder.setPositiveButton("Ok", (arg0, arg1) -> {
//            arg0.dismiss();
//        });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.show();
//    }
//
//    public  void customAlertDialogFinishCancel(String message, final Activity activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
//
//        alertDialogBuilder.setMessage(message);
//        alertDialogBuilder.setCancelable(false)
//                .setPositiveButton("OK", (dialog, id) -> {
//                    activity.setResult(AppConfig.REFRESH_ACTIVITY, new Intent());
//                    activity.finish();
//                }).setNegativeButton("Cancel", (arg0, arg1) -> arg0.dismiss()).show();
//
//    }

    public  void callLoginActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        activity.finish();
////                activity.finish();
    }


//    public  void customAlertDialogFinishHome(String message, final Activity activity) {
////        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, Repo.style.AlertDialogCustom);
////        alertDialogBuilder.setMessage(message);
//
////        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface arg0, int arg1) {
////                activity.startActivity(new Intent(activity, HomeActivity.class));
////                activity.finish();
//            }
////        });
////        AlertDialog alertDialog = alertDialogBuilder.create();
////        alertDialog.setCanceledOnTouchOutside(false);
////        alertDialog.show();
//    }

//    public  void showAd(Activity activity) {
//        Appodeal.show(activity, Appodeal.BANNER_BOTTOM);
//    }
//
//    public  void dismissAd(Activity activity) {
//        Appodeal.hide(activity, Appodeal.BANNER_BOTTOM);
//    }

    public  String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

//    public  void customAlertDialog(String message, final Context activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, Repo.style.AlertDialogCustom);
//        ;
//
//        alertDialogBuilder.setMessage(message);
//        alertDialogBuilder.setCancelable(false)
//                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                }).show();
//    }
//
//    public  boolean customAlertDialogreturn(String message, final Context activity) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, Repo.style.AlertDialogCustom);
//        ;
//
//        alertDialogBuilder.setMessage(message);
//        alertDialogBuilder.setCancelable(false)
//                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                }).show();
//        return true;
//    }

    public  boolean checkForAlpahbet(String str1) {
        if (!Pattern.matches(".*[a-zA-Z]+.*", str1))
            return true;
        else return false;
    }


//    public void call(String number) {
//        if (SingletonHolder.getInstance().getSettingsPojo().getCallKol().equals("Y")) {
//            Intent intent = new Intent(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse("tel:" + number));
//            con.startActivity(intent);
//        } else showToast("No permission to call");
//    }

    public  boolean isLollipop() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }

    public  String timeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        String timeZone = new SimpleDateFormat("Z").format(calendar.getTime());
        return timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5);
    }

    public  int timeZoneOffsetinSeconds() {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return tz.getOffset(System.currentTimeMillis()) / 1000;
    }

    public  String getBuildVersion(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            long time = ze.getTime();
            String s = SimpleDateFormat.getInstance().format(new Date(time));
            zf.close();
            return s;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * To hide keyboard
     *
     * @param activity
     */
    public void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    public  HashMap<String, String> getQueryString(String url) {
        Uri uri = Uri.parse(url);

        HashMap<String, String> map = new HashMap<>();
        for (String paramName : uri.getQueryParameterNames()) {
            if (paramName != null) {
                String paramValue = uri.getQueryParameter(paramName);
                if (paramValue != null) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

//    public String GetCountryZipCode(Activity activity) {
//        String CountryID = "";
//        String CountryZipCode = "";
//
//        TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
//        //getNetworkCountryIso
//        CountryID = manager.getSimCountryIso().toUpperCase();
//        String[] rl = activity.getResources().getStringArray(Repo.array.CountryCodes);
//        for (int i = 0; i < rl.length; i++) {
//            String[] g = rl[i].split(",");
//            if (g[1].trim().equals(CountryID.trim())) {
//                CountryZipCode = g[0];
//                break;
//            }
//        }
//        return CountryZipCode;
//    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public void checkWriteExternalPermission(Activity mActivity) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        AppConfig.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The CALL_BACK method gets the
                // result of the request.
            }
        } else {

//            Toast.makeText(mActivity, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }


//    private void customPopUp(String title, String content, final JSONObject resposnseJSON, String accept) {
//        final Dialog dialog = new Dialog(FanSignUpActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View view = getLayoutInflater().inflate(Repo.layout.dialog_custom_popup, null);
//
//        TextView tv_title = (TextView) view.findViewById(Repo.id.tv_title);
//        TextView tv_content = (TextView) view.findViewById(Repo.id.tv_content);
//        TextView tv_accept = (TextView) view.findViewById(Repo.id.tv_accept);
//        TextView tv_reject = (TextView) view.findViewById(Repo.id.tv_reject);
//        tv_title.setText(title);
//        tv_content.setText(content);
//        tv_accept.setText(accept);
//        tv_reject.setText("cancel");
//        view.findViewById(Repo.id.tv_accept).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                JSONObject mergeData = null;
//                try {
//                    mergeData = resposnseJSON.getJSONObject("merge_data");
//                    JSONObject userData = resposnseJSON.getJSONObject("user_data");
//                    WebServices.mergeLogin(FanSignUpActivity.this, asyncCallBack, AppConfig.MERGE_ACCOUNTS, userData.getString("member_id"), mergeData.getString("media"), mergeData.getString("media_id"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        view.findViewById(Repo.id.tv_reject).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setContentView(view);
//        dialog.setCancelable(false);
//        dialog.show();
//    }

    /**
     * Method to show snack message
     *
     * @param view
     * @param message
     */
    public void showSuccessSnackToast(View view, String message) {
//        Snackbar snackbar = Snackbar.make(view, "☺ " + message, Snackbar.LENGTH_LONG);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.BLACK);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#FDDE50"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextSize(18);
        snackbar.show();

    }


    /**
     * Method to show snack message
     *
     * @param view
     * @param message
     */
    public void showActionSnackToast(View view, String message) {
//        Snackbar snackbar = Snackbar.make(view, "☺ " + message, Snackbar.LENGTH_LONG);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.BLACK);
        View sbView = snackbar.getView();
        sbView.setBackground(con.getResources().getDrawable(R.drawable.bg_gradient));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextSize(18);
//        snackbar.show();

    }


    public boolean isTablet() {
        boolean xlarge = ((con.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((con.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    /**
     * Method to show snack message
     *
     * @param view
     * @param message
     */
    public void showFailureSnackToast(View view, String message) {
//        Snackbar snackbar = Snackbar.make(view, "\uD83D\uDE22 " + message, Snackbar.LENGTH_LONG);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.parseColor("#EA4747"));
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#EA4747"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(18);
        textView.setTypeface(null, Typeface.NORMAL);
        snackbar.show();

    }


    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                con.getResources().getDisplayMetrics());
    }


    public void setupUI(View view, final Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }

            });
        }

        // If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView, activity);
            }
        }
    }


    public void showFullScreenUserImageDialog(String bmp, Context context) {
//        final Dialog builder = new Dialog(context, android.Repo.style.Theme_Black_NoTitleBar_Fullscreen);
//        LayoutInflater factory = LayoutInflater.from(context);
//        final View view = factory.inflate(Repo.layout.alert_imageview, null);
//        final PhotoDraweeView iv_large = (PhotoDraweeView) view.findViewById(Repo.id.iv_large);
//        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
//        controller.setUri(URI);
//        controller.setOldController(iv_large.getController());
//        controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
//            @Override
//            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                super.onFinalImageSet(id, imageInfo, animatable);
//                if (imageInfo == null || iv_large == null) {
//                    return;
//                }
//                iv_large.update(imageInfo.getWidth(), imageInfo.getHeight());
//            }
//        });
//        iv_large.setController(controller.build());
//        iv_large.setPhotoUri(Uri.parse(bmp));
//        iv_large.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                builder.cancel();
//            }
//        });
//        builder.setContentView(view);
//        builder.show();
    }

    public void FileUploadNotification(String key) {
        mNotificationManager = (NotificationManager) con.getSystemService(con.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(con);
        builder.setContentTitle("start uploading...")
                .setContentText(key)
                .setSmallIcon(android.R.drawable.stat_sys_upload)
                .setProgress(100, 0, false)
                .setAutoCancel(false);
    }

    public  void updateNotification(String percent, String fileName, String contentText) {
        try {
            builder.setContentText(contentText)
                    .setContentTitle(fileName)
                    //.setSmallIcon(android.Repo.drawable.stat_sys_download)
                    .setOngoing(true)
                    .setContentInfo(percent + "%")
                    .setProgress(100, Integer.parseInt(percent), false);

            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
            if (Integer.parseInt(percent) == 100)
                deleteNotification();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("Error...Notification.", e.getMessage() + ".....");
            e.printStackTrace();
        }
    }

    public  void failUploadNotification(/*int percentage, String fileName*/) {
        Log.e("downloadsize", "failed notification...");

        if (builder != null) {
            /* if (percentage < 100) {*/
            builder.setContentText("Uploading Failed")
                    //.setContentTitle(fileName)
                    .setSmallIcon(android.R.drawable.stat_sys_upload_done)
                    .setOngoing(false);
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        /*} else {
            mNotificationManager.cancel(NOTIFICATION_ID);
            builder = null;
        }*/
        } else {
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    }

    public  void deleteNotification() {
        mNotificationManager.cancel(NOTIFICATION_ID);
        builder = null;
    }

//    /*
//     * Begins to upload the file specified by the file path.
//     */
//    public void singleUpload(final String bucket_name, final String key, final File file, String gibaa007_video, final String content) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                TransferUtility transferUtility = getTransferUtility(con);
//                // Starts a upload
//                TransferObserver observer = transferUtility.upload(bucket_name, key, file, CannedAccessControlList.PublicRead);
//                observer.setTransferListener(new TransferListener() {
//                    @Override
//                    public void onError(int id, Exception e) {
//                        Log.e(AppConfig.TAG, "Error during upload: " + id, e);
//                    }
//
//                    @Override
//                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                        Log.d(AppConfig.TAG, String.format("onProgressChanged: %d, total: %d, current: %d", id, bytesTotal, bytesCurrent));
//                        int progress = (int) ((double) bytesCurrent * 100 / bytesTotal);
//                        updateNotification(progress + "", content, key);
//                    }
//
//                    @Override
//                    public void onStateChanged(int id, TransferState newState) {
//                        Log.e(AppConfig.TAG, "File Name: " + key);
//                        Log.d(AppConfig.TAG, "onStateChanged: " + id + ", " + newState);
//                        if (newState.toString().equals("IN_PROGRESS"))
//                            FileUploadNotification(key);
//                        else if (newState.toString().equals("FAILED"))
//                            failUploadNotification();
//                        else if (newState.toString().equals("COMPLETED")) {
//                            try {
//                                if (file.exists()) {
//                                    if (file.delete()) {
//                                        System.out.println("file Deleted :" + file.getAbsolutePath());
//                                    } else {
//                                        System.out.println("file not Deleted :" + file.getAbsolutePath());
//                                        showToast("File Not Deleted!!!");
//                                    }
//                                }
////                                showToast("Upload Completed");
//                            } catch (Exception e) {
//                                showToast("Upload Completed with exception");
//                            }
//                        }
//                    }
//                });
//
//            }
//        }).start();
//
//    }


    /**
     * Method for showing toast
     *
     * @param Message
     */
    public void showToast(String Message) {
        Log.e("G7", "showToast: " + Message);
        Toast.makeText(con, Message, Toast.LENGTH_LONG).show();
    }

    /**
     * Method for showing toast
     *
     * @param Message
     */
    public void showMesage(String Message) {
        Toast.makeText(con, Message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Returns the extracted text from the edit text given
     *
     * @param editText
     * @return
     */
    public String getTextFrom(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * Method for checking valid email id
     *
     * @param target
     * @return
     */
    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }

    /**
     * Returns true if Internet is available
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return (cm.getActiveNetworkInfo() != null);
    }

    public String textToKM(long value) {

        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return textToKM(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + textToKM(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    private  final NavigableMap<Long, String> suffixes = new TreeMap<>();

     {
        suffixes.put(1_000L, " K");
        suffixes.put(1_000_000L, " M");
        suffixes.put(1_000_000_000L, " G");
        suffixes.put(1_000_000_000_000L, " T");
        suffixes.put(1_000_000_000_000_000L, " P");
        suffixes.put(1_000_000_000_000_000_000L, " E");
    }


//    public String getCurrentTimeStamp() {
//        try {
//
//            long time = System.currentTimeMillis();
//            String currentDateTime = time + "";
//
//            return "a_" + currentDateTime + "_" + SingletonHolder.getInstance().profile.getUserId();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            return null;
//        }
//    }

    //fresco image support
    /*fun setImage(ori:Uri, view:SimpleDraweeView?, thumb:String) {

        //        DraweeController controller = Fresco.newDraweeControllerBuilder()
        //                .setUri(Uri.parse())
        //                .setAutoPlayAnimations(true)
        //                .build();
        //        view.setController(controller);

        val controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequest.fromUri(Uri.parse("https://seeklogo.com/images/A/Audi-logo-ED84DFE2E3-seeklogo.com.png")))
                .setImageRequest(ImageRequest.fromUri("https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/audi-rs7_1.jpg?itok=IbKvRTO6"))
                .setOldController(view !!.controller)
                .build()

        //        Fresco.getImagePipeline().prefetchToBitmapCache(
        //                ImageRequest.fromUri(ori), view.getContext());

        view.controller = controller

        //        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(ori)
        //                .setResizeOptions(new ResizeOptions(75, 75))
        //                .build();
        //        view.setController(Fresco.newDraweeControllerBuilder()
        //                .setLowResImageRequest(ImageRequest.fromUri(Uri.parse(thumb)))
        //                .setOldController(view.getController())
        //                .setImageRequest(ImageRequest.fromUri(ori)).build());

        //        DraweeController ctrl = Fresco.newDraweeControllerBuilder()
        //                .setUri(ori)
        //                .setOldController(view.getController())
        //                .build();
        //
        //        view.setController(ctrl);
        //        view.setImageURI(ori);

    }*/

    //Smiley Support
    //    compile 'org.apache.commons:commons-lang3:3.4'


//    public String unicodeConversion(String content) {
//
//        final CharSequenceTranslator ESCAPE_JAVA =
//                new LookupTranslator(
//                        new String[][]{
//                                {"\"", "\""},
//                                {"\\", "\\\\"},
//                        }).with(
//                        new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())
//                ).with(JavaUnicodeEscaper.outsideOf(32, 0x7f));
//
//        return ESCAPE_JAVA.translate(content);
//
//    }

}
