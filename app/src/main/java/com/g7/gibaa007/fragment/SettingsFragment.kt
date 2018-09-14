package com.g7.gibaa007.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.TextView
import com.g7.gibaa007.R
import com.g7.gibaa007.activity.ChangePasswordActivity
import com.g7.gibaa007.activity.EditProfileActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor

/**
 * Created by gibaa007 on 30/5/18.
 */


class SettingsFragment : Fragment() {


    var _rootView: View? = null
    var tv_edit: TextView? = null
    var tv_change: TextView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        init(_rootView)
        return _rootView
    }

    fun newInstance(): SettingsFragment {
        return newInstance()
    }

    private fun init(_rootView: View?) {
        tv_edit = _rootView!!.find(R.id.tv_edit_profile)
        tv_change = _rootView!!.find(R.id.tv_changePassword)
        tv_edit!!.setOnClickListener { startActivity(intentFor<EditProfileActivity>()) }
        tv_change!!.setOnClickListener { startActivity(intentFor<ChangePasswordActivity>()) }

//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        // Do something that differs the Activity's menu hereMenuItem myActionMenuItem = menu.findItem( Repo.id.action_search);
        activity!!.menuInflater.inflate(R.menu.home, menu)
        val myActionMenuItem = menu!!.findItem(R.id.home)
//        searchView = myActionMenuItem.actionView as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                //                myActionMenuItem.collapseActionView();
//                searchText = query
//                GetPostTask()
//                return false
//            }
//
//            override fun onQueryTextChange(s: String): Boolean {
//                searchText = s
//                return false
//            }
//
//
//        })
//
//        // do something for phones running an SDK before froyo
//        searchView.setOnCloseListener(SearchView.OnCloseListener {
//            searchText = ""
//            GetPostTask()
//            false
//        })

    }
}