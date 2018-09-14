package com.g7.gibaa007.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.g7.gibaa007.R
import com.g7.gibaa007.utils.CommonActions
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    var commonAction: CommonActions? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        commonAction = CommonActions(this)
        setContentView(R.layout.activity_signup)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Set up the login form.
//        populateAutoComplete()
        et_password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptSignUp()
                return@OnEditorActionListener true
            }
            false
        })

        bt_create.setOnClickListener { attemptSignUp() }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptSignUp() {
        // Reset errors.
        et_email.error = null
        et_password.error = null
        et_uname.error = null

        // Store values at the time of the login attempt.
        val emailStr = et_email.text.toString()
        val passwordStr = et_password.text.toString()
        val uname = et_uname.text.toString()
        val fname = et_fname.text.toString()
        val phone = et_phone.text.toString()
        val zipcode = et_zipcode.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.


        // Check for a valid email address.
        if (TextUtils.isEmpty(fname)) {
            et_fname.error = "Full Name is required"
            focusView = et_fname
            cancel = true
        } else if (TextUtils.isEmpty(emailStr)) {
            et_email.error = "Email is required"
            focusView = et_email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            et_email.error = getString(R.string.error_invalid_email)
            focusView = et_email
            cancel = true
        } else if (TextUtils.isEmpty(uname)) {
            et_uname.error = "Username is required"
            focusView = et_uname
            cancel = true
        } else if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            et_password.error = getString(R.string.error_invalid_password)
            focusView = et_password
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else if (login(emailStr, passwordStr)) {
            commonAction!!.hideSoftKeyboard(this)
//            startActivity(intentFor<HomeActivity>())
            commonAction!!.showSuccessSnackToast(et_email, "Success")

        } else
            commonAction!!.showFailureSnackToast(et_email, "Invalid email or password")
    }

    private fun login(mEmail: String, mPassword: String): Boolean {

        return true
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
