package com.g7.gibaa007.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gibaa007 on 26/2/18.
 */

class CommonPojo<T> {

    @SerializedName("function")
    @Expose
    var function: String? = null
    @SerializedName("status")
    @Expose
    var status: Boolean = false
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: T? = null
}
