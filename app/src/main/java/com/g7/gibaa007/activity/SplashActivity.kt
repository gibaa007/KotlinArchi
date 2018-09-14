package com.g7.gibaa007.activity

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.facebook.drawee.backends.pipeline.Fresco
import com.g7.gibaa007.R
import com.g7.gibaa007.utils.AppConfig
import com.g7.gibaa007.utils.CommonActions
import com.g7.gibaa007.utils.SingletonHolder
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

/**
 * Created by gibaa007 on 29/5/18.
 */

class SplashActivity : AppCompatActivity() {
    var commonAction: CommonActions? = null
    var rl_activity_splash: View? = null
    var sharedpreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var iv_splash: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonAction = CommonActions(this)
        if (!Fresco.hasBeenInitialized())
            Fresco.initialize(this)
        sharedpreferences = getSharedPreferences(AppConfig.SHARED_VALUE,
                Context.MODE_PRIVATE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        editor = sharedpreferences!!.edit()
        setContentView(R.layout.activity_splash)
        iv_splash = findViewById(R.id.iv_splash)
        //iv_splash.animate().alpha(1.0f).setDuration(2000);
        rl_activity_splash = findViewById(R.id.rl_activity_splash)

        Handler().postDelayed({
            if (SingletonHolder.getInstance().isLoggedIn) {
//                if (intent.extras.getString("type") != null) {
//                    Handler().postDelayed({
//                        val redirectionIntent: Intent
//                        val id: String
//                        val type = intent.extras.getString("type");
//                        if (type.equals("follow")) {
//                            id = intent.extras.getString("user_id")
//                            redirectionIntent = Intent(this, ProfileViewActivity::class.java)
//                            redirectionIntent.putExtra("id", id.toInt())
//                        } else {
//                            id = intent.extras.getString("quickee_id")
//                            redirectionIntent = Intent(this, QuickeeDetailActivity::class.java)
//                            redirectionIntent.putExtra("quickeeId", id.toInt())
//                        }
//                        startActivity(redirectionIntent)
//                        finish()
//                    }, 1000)
//                }
                startActivity(intentFor<HomeActivity>())
            } else {
               startActivity(intentFor<LoginActivity>().clearTop())
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    try {
//                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashActivity, iv_splash!!, getString(Repo.string.app_name))
//                        startActivity(intent, options.toBundle())
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                } else {
                startActivity(intent)
//                }
            }
            finish()


        }, AppConfig.SPLASH_TIME_OUT)
        SingletonHolder.getInstance().prefs = sharedpreferences
        SingletonHolder.getInstance().editor = editor
    }


}