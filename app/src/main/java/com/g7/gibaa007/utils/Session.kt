package com.g7.gibaa007.utils

import com.g7.gibaa007.pojo.SessionPojo

/**
 * Created by gibaa007 on 1/6/18.
 */

interface Session {

    var credentials: SessionPojo
    var authenticated: Boolean

    fun isAuthenticated(): Boolean

    fun clearCredentials()
}