//package com.g7.gatgo.api
//
//import android.arch.lifecycle.MutableLiveData
//
//import com.g7.gatgo.pojo.CommonPojo
//import com.g7.gatgo.pojo.ProfilePojo
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//import com.g7.gatgo.utils.GatGoApp.getApiService
//
///**
// * Created by gibaa007 on 31/5/18.
// */
//
//class Repo : MutableLiveData<*>() {
//
//
//    internal var mutableLiveData: MutableLiveData<CommonPojo<*>>? = null
//
//
//    fun signUpLiveData(uname: String, fname: String, email: String, pass: String): MutableLiveData<CommonPojo<*>> {
//        mutableLiveData = MutableLiveData<CommonPojo>()
//        getApiService().signUp(uname, fname, email, pass).enqueue(object : Callback<CommonPojo<ProfilePojo>> {
//            override fun onResponse(call: Call<CommonPojo<ProfilePojo>>, response: Response<CommonPojo<ProfilePojo>>) {
//                mutableLiveData!!.setValue(response.body())
//            }
//
//            override fun onFailure(call: Call<CommonPojo<ProfilePojo>>, t: Throwable) {
//                mutableLiveData!!.setValue(null)
//            }
//        })
//        return mutableLiveData
//    }
//
//    fun verifyCodeLiveData(email: String): MutableLiveData<CommonPojo<*>> {
//        mutableLiveData = MutableLiveData<CommonPojo>()
//        getApiService().verifyCode(email).enqueue(object : Callback<CommonPojo<ProfilePojo>> {
//            override fun onResponse(call: Call<CommonPojo<ProfilePojo>>, response: Response<CommonPojo<ProfilePojo>>) {
//                mutableLiveData!!.setValue(response.body())
//            }
//
//            override fun onFailure(call: Call<CommonPojo<ProfilePojo>>, t: Throwable) {
//                mutableLiveData!!.setValue(null)
//            }
//        })
//        return mutableLiveData
//    }
//
//    fun loginLiveData(uname: String, pass: String, deviceId: String): MutableLiveData<CommonPojo<*>> {
//        mutableLiveData = MutableLiveData<CommonPojo>()
//        getApiService().login(uname, pass, deviceId, "Android").enqueue(object : Callback<CommonPojo<ProfilePojo>> {
//            override fun onResponse(call: Call<CommonPojo<ProfilePojo>>, response: Response<CommonPojo<ProfilePojo>>) {
//                mutableLiveData!!.setValue(response.body())
//            }
//
//            override fun onFailure(call: Call<CommonPojo<ProfilePojo>>, t: Throwable) {
//                mutableLiveData!!.setValue(null)
//            }
//        })
//        return mutableLiveData
//    }
//
//    fun forgotLiveData(uname: String): MutableLiveData<CommonPojo<*>> {
//        mutableLiveData = MutableLiveData<CommonPojo>()
//        getApiService().forgot(uname).enqueue(object : Callback<CommonPojo<ProfilePojo>> {
//            override fun onResponse(call: Call<CommonPojo<ProfilePojo>>, response: Response<CommonPojo<ProfilePojo>>) {
//                mutableLiveData!!.setValue(response.body())
//            }
//
//            override fun onFailure(call: Call<CommonPojo<ProfilePojo>>, t: Throwable) {
//                mutableLiveData!!.setValue(null)
//            }
//        })
//        return mutableLiveData
//    }
//}
