package com.g7.gatgo.api

import okhttp3.RequestBody
import com.g7.gatgo.utils.Print
import okhttp3.MediaType
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject



/**
 * Created by G7 on 1/8/18.
 */
class Util{

     fun getRequestBody(reqMap: HashMap<String, Any>): RequestBody {
        val reqJson = JSONObject()
        for (entry in reqMap.entries) {
            val key = entry.key
            val value = entry.value
            try {
                if (value is Int) {
                    reqJson.put(key, Integer.parseInt(value.toString()))
                } else if (value is Double) {
                    reqJson.put(key, java.lang.Double.parseDouble(value.toString()))
                } else if (value is Boolean) {
                    reqJson.put(key, java.lang.Boolean.parseBoolean(value.toString()))
                } else if (value is Long) {
                    reqJson.put(key, java.lang.Long.parseLong(value.toString()))
                } else if (value is Float) {
                    reqJson.put(key, java.lang.Float.parseFloat(value.toString()).toDouble())
                } else if (value is JSONArray) {
                    reqJson.put(key, value)
                } else {
                    reqJson.put(key, value.toString())
                }
            } catch (e: JSONException) {
                Print.exception(e)
            }

        }
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), reqJson.toString())
    }

    fun getPartMap(reqInMap: HashMap<String, Any>): Map<String, RequestBody> {
        val reqOutMap = HashMap<String, RequestBody>()
        for (entry in reqInMap.entries) {
            reqOutMap.put(entry.key, RequestBody.create(MediaType.parse("text/plain"), entry.value.toString()))
        }
        return reqOutMap
    }
    
}