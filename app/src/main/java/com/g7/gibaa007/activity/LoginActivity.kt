package com.g7.gibaa007.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.g7.gibaa007.R
import com.g7.gibaa007.api.VM
import com.g7.gibaa007.pojo.CommonPojo
import com.g7.gibaa007.utils.CommonActions
import com.g7.gibaa007.utils.SingletonHolder
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    var commonAction: CommonActions? = null


    private var networkViewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        commonAction = CommonActions(this)
        setContentView(R.layout.activity_login)
        // Set up the login form.
//        populateAutoComplete()
        networkViewModel = ViewModelProviders.of(this).get(VM::class.java)
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        bt_login.setOnClickListener { attemptLogin() }
        tv_forgot.setOnClickListener { startActivity(intentFor<ForgotActivity>()) }
        tv_create.setOnClickListener { startActivity(intentFor<SignUpActivity>()) }
    }

//    private fun populateAutoComplete() {
//        if (!mayRequestContacts()) {
//            return
//        }
//
//        loaderManager.initLoader(0, null, this)
//    }

//    private fun mayRequestContacts(): Boolean {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true
//        }
//        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            Snackbar.make(email, Repo.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.Repo.string.ok,
//                            { requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS) })
//        } else {
//            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
//        }
//        return false
//    }

//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
//                                            grantResults: IntArray) {
//        if (requestCode == REQUEST_READ_CONTACTS) {
//            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete()
//            }
//        }
//    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {
        // Reset errors.
        email.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        }

//        else if (!isEmailValid(emailStr)) {
//            email.error = getString(Repo.string.error_invalid_email)
//            focusView = email
//            cancel = true
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            commonAction!!.hideSoftKeyboard(this)
            networkViewModel!!.login(emailStr, passwordStr, "").observe(
                    this,
                    Observer<CommonPojo<*>?> { t ->
                        if (t!!.status) {
                            commonAction!!.showSuccessSnackToast(tv_forgot, t.message)
                            SingletonHolder.getInstance().isLoggedIn = true
                            startActivity(intentFor<HomeActivity>())
                        } else commonAction!!.showFailureSnackToast(tv_forgot, t.message)
                    }
            )
        }
//        else if (login(emailStr, passwordStr)) {
//        }
//
//        else
//            commonAction!!.showFailureSnackToast(tv_forgot, "Invalid email or password")
    }

    private fun login(mEmail: String, mPassword: String): Boolean {

        return DUMMY_CREDENTIALS.map { it.split(":") }.firstOrNull { it[0] == mEmail }
                ?.let {
                    // Account exists, return true if the password matches.
                    it[1] == mPassword
                }
                ?: false
    }

    companion object {

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("gibin@gibaa007.com:123456", "surya@gibaa007.com:qwerty")
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

}
