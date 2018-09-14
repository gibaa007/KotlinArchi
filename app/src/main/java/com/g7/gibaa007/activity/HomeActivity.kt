package com.g7.gibaa007.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.widget.TextView
import com.g7.gibaa007.R
import com.g7.gibaa007.fragment.*
import com.g7.gibaa007.utils.CommonActions
import java.util.ArrayList

/**
 * Created by gibaa007 on 29/5/18.
 */

class HomeActivity : BaseActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var commonActions: CommonActions? = null
    private var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        commonActions = CommonActions(this)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        isEdit = intent.getBooleanExtra("isedit", false)
        viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)

        tabLayout = findViewById(R.id.tabs)
        tabLayout!!.setupWithViewPager(viewPager)
        setupTabIcons()
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(false)
        setTitle("Home")
        viewPager!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> setTitle("Home")
                    1 -> setTitle("Profile")
                    2 -> setTitle("Messages")
                    3 -> setTitle("Search")
                    4 -> setTitle("Settings")
                    else -> title = ""
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
//        if (isEdit)
//            startActivity(intentFor<EditProfileActivity>())
    }


    private fun setupTabIcons() {
        val tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0)
        tabLayout!!.getTabAt(0)!!.customView = tabOne

        val tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0)
        tabLayout!!.getTabAt(1)!!.customView = tabTwo

        val tabThree = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0)
        tabLayout!!.getTabAt(2)!!.customView = tabThree

        val tabFour = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0)
        tabLayout!!.getTabAt(3)!!.customView = tabFour

        val tabFive = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher, 0, 0)
        tabLayout!!.getTabAt(4)!!.customView = tabFive
        viewPager!!.currentItem = 0
    }


    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(HomeFragment())
        adapter.addFrag(ProfileFragment())
        adapter.addFrag(MessagesFragment())
        adapter.addFrag(SearchFragment())
        adapter.addFrag(SettingsFragment())
        viewPager!!.adapter = adapter
    }

    private inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }
}