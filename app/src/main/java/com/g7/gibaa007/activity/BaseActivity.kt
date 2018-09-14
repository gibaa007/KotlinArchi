package com.g7.gibaa007.activity

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by gibaa007 on 29/5/18.
 */


open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        active = false
    }


    override fun onDestroy() {
        super.onDestroy()
        active = false

    }

    override fun onRestart() {
        super.onRestart()
        active = true
    }

    override fun onResume() {
        super.onResume()
        active = true
    }

    override fun onClick(view: View) {

    }

    companion object {
        var active: Boolean = false


        //RxJava


        //    private final CompositeDisposable disposables = new CompositeDisposable();
        //
        //    void onRunSchedulerExampleButtonClicked() {
        //        disposables.add(sampleObservable()
        //                // Run on a background thread
        //                .subscribeOn(Schedulers.io())
        //                // Be notified on the main thread
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribeWith(new DisposableObserver<String>() {
        //                    @Override
        //                    public void onComplete() {
        //                        commonActions.showToast("onComplete()");
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        commonActions.showToast("onError()");
        //                    }
        //
        //                    @Override
        //                    public void onNext(String string) {
        //                        commonActions.showToast("onNext(" + string + ")");
        //                    }
        //                }));
        //    }


        /**
         * Get the value of the data column for this Uri. This is useful for
         * MediaStore Uris, and other file-based ContentProviders.
         *
         * @param context       The context.
         * @param uri           The Uri to query.
         * @param selection     (Optional) Filter used in the query.
         * @param selectionArgs (Optional) Selection arguments used in the query.
         * @return The value of the _data column, which is typically a file path.
         */
        fun getDataColumn(context: Context, uri: Uri, selection: String,
                          selectionArgs: Array<String>): String? {

            var cursor: Cursor? = null
            val column = "_data"
            val projection = arrayOf(column)

            try {
                cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val column_index = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(column_index)
                }
            } finally {
                if (cursor != null)
                    cursor.close()
            }
            return null
        }

        /**
         * @param uri The Uri to isAuthenticated.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        /**
         * @param uri The Uri to isAuthenticated.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

        /**
         * @param uri The Uri to isAuthenticated.
         * @return Whether the Uri authority is MediaProvider.
         */
        fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }
    }
}

