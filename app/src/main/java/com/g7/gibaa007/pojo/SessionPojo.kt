package com.g7.gibaa007.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by gibaa007 on 1/6/18.
 */

class SessionPojo {
    @SerializedName("member_id")
    var id: Int = 0
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("last_name")
    var lastName: String? = null
    @SerializedName("country_code")
    var countryCode: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("profile_image")
    var profileImage: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("date_of_birth")
    var dob: String? = null
    @SerializedName("latitude")
    var latitude: Double = 0.toDouble()
    @SerializedName("longitude")
    var longitude: Double = 0.toDouble()
    @SerializedName("address1")
    var address: String? = null
    @SerializedName("email_verified")
    var isEmailVerified: Boolean = false
    @SerializedName("phone_verified")
    var isPhoneVerified: Boolean = false
}
