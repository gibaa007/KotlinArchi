package com.g7.gibaa007.api

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.g7.gibaa007.pojo.CommonPojo

/**
 * Created by gibaa007 on 31/5/18.
 */

class VM(application: Application) : AndroidViewModel(application) {
    private var mRepository: Repo? = null

    init {
        mRepository = Repo()
    }

    fun login(uname: String, pass: String, deviceId: String): LiveData<CommonPojo<*>> {
        return mRepository!!.loginLiveData(uname, pass, deviceId)
    }


}