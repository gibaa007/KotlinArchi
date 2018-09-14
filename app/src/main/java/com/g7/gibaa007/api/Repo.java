package com.g7.gibaa007.api;

import android.arch.lifecycle.MutableLiveData;

import com.g7.gibaa007.pojo.CommonPojo;
import com.g7.gibaa007.pojo.ProfilePojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.g7.gibaa007.utils.Gibaa007App.getApiService;

/**
 * Created by gibaa007 on 31/5/18.
 */

public class Repo extends MutableLiveData {


    final MutableLiveData<CommonPojo> mutableLiveData = new MutableLiveData<>();


    public MutableLiveData<CommonPojo> loginLiveData(String uname, String pass, String deviceId) {
        getApiService().login(uname, pass, deviceId,"Android").enqueue(new Callback<CommonPojo<ProfilePojo>>() {
            @Override
            public void onResponse(Call<CommonPojo<ProfilePojo>> call, Response<CommonPojo<ProfilePojo>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CommonPojo<ProfilePojo>> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
