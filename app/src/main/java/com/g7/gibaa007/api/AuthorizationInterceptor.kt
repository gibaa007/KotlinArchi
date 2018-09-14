package com.g7.gibaa007.api

import android.util.Log
import com.g7.gibaa007.utils.Print
import com.g7.gibaa007.utils.Session
import com.g7.gibaa007.utils.SingletonHolder

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

/**
 * Created by gibaa007 on 31/5/18.
 */

class AuthorizationInterceptor(private val session: Session) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var firstRequest = chain.request()
        if (session.authenticated) {
            firstRequest = chain.request().newBuilder().addHeader("x-access-token", SingletonHolder.getInstance().accessToken).build()
        }
        requestLog(firstRequest)
        var response = chain.proceed(firstRequest)
        responseLog(response)
        Log.d("Server Log: Request", response.request().toString())
        Log.d("Server Log: Response", firstRequest.url().toString())
        var authentication = response.header("authorization")
        if (session.authenticated && authentication != null && authentication == "false") {
            val builder = firstRequest.newBuilder().header("refresh-token", SingletonHolder.getInstance().refreshToken).method(firstRequest.method(), firstRequest.body())
            val secondRequest = builder.build()
            requestLog(secondRequest)
            response = chain.proceed(secondRequest)
            authentication = response.header("authorization")
            if (session.authenticated && authentication != null && authentication == "false") {
                session.clearCredentials()
            }
        }

        val authToken = response.header("x-access-token")
        if (authToken != null && authToken != "") {
            SingletonHolder.getInstance().accessToken = authToken
            session.authenticated = true
        }
        val refreshToken = response.header("refresh-token")
        if (refreshToken != null && refreshToken != "") {
            SingletonHolder.getInstance().refreshToken = refreshToken
            session.authenticated = true
        }
        return response
    }


//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var firstRequest = chain.request()
//        if (session.check()) {
//            firstRequest = chain.request().newBuilder().addHeader("AuthToken", session.getAuthToken()).build()
//        }
//        requestLog(firstRequest)
//        var response = chain.proceed(firstRequest)
//        responseLog(response)
//
//        var authentication = response.header("Authentication")
//        if (authentication != null && authentication == "false") {
//            val builder = firstRequest.newBuilder().header("RefreshToken", session.getRefreshToken()).method(firstRequest.method(), firstRequest.body())
//            val secondRequest = builder.build()
//
//            requestLog(secondRequest)
//            response = chain.proceed(secondRequest)
//            responseLog(response)
//
//            authentication = response.header("Authentication")
//            if (authentication != null && authentication == "false") {
//                //session.clearCredentials();
//            }
//        }
//
//        val authToken = response.header("AuthToken")
//        if (authToken != null) {
//            session.setAuthToken(authToken)
//        }
//        val refreshToken = response.header("RefreshToken")
//        if (refreshToken != null) {
//            session.setRefreshToken(refreshToken)
//        }
//        return response
//    }

    private fun requestLog(request: Request) {
        val log = StringBuilder()

        log.append("URL {")
        log.append(" " + String.format("%s", request.url()))
        log.append("}\n")


        val header = StringBuilder()
        val headers = request.headers()
        var size = headers.size()
        for (i in 0 until size) {
            header.append("\n  " + headers.name(i) + ":" + headers.value(i))
        }

        if (header.length > 0) {
            log.append("Header {")
            log.append(header)
            log.append("\n}\n")
        }

        try {
            val body = StringBuilder()
            val buffer = Buffer()
            if (body.toString() != "") {
                request.newBuilder().build().body()!!.writeTo(buffer)
                val req = buffer.readUtf8()
                val reqArray = req.split("&".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                size = reqArray.size
                for (i in 0 until size) {
                    body.append("\n  " + reqArray[i].replace("=".toRegex(), ":"))
                }

                if (body.isNotEmpty()) {
                    log.append("Body {")
                    log.append(body)
                    log.append("\n}\n")
                }
            }
        } catch (e: IOException) {
            log.append("Body {")
            log.append("\n Invalid Body")
            log.append("\n}\n\n")
        }

        Print.request(log)
    }


    private fun responseLog(response: Response) {
        val log = StringBuffer("\n")
        log.append("Header {")
        val headers = response.headers()
        val size = headers.size()
        for (i in 0 until size) {
            log.append("\n  " + headers.name(i) + ":" + headers.value(i))
        }
        log.append("\n}\n")

        try {

            val responseBody = response.body()
            val contentLength = responseBody!!.contentLength()

            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()

            var charset = Charset.forName("UTF-8")
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"))
            }

            if (contentLength != 0L) {
                log.append("Body {")
                log.append("\n  " + buffer.clone().readString(charset))
                log.append("\n}")
            }
        } catch (e: Exception) {
            log.append("\nBody {")
            log.append("\n Invalid Body")
            log.append("\n}\n\n")
        }

        log.append("\n_________________________")
        Print.response(log)
    }
}