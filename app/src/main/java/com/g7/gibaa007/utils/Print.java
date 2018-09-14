package com.g7.gibaa007.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vinil Chandran on 4/15/2016.
 */

public class Print {
    private static String PREFIX = "DLog:";
    private static String TAG = Print.class.getSimpleName()+" : ";
    public static boolean isPrint = true;
    public static void e(String message){
        if (isPrint && message!=null && !message.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message);
        }
    }
    public static void e(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString());
        }
    }
    public static void exception(Exception e){
        if (isPrint && e!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            if (e!=null) {
                Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Exception:"+e.getMessage());
                int lineNumber = e.getStackTrace()[0].getLineNumber();
                Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Exception:L:"+ Integer.toString(lineNumber));
            } else {
                Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Exception: Unknown");
            }
        }
    }
    public static void exception(String e){
        if (isPrint && e!=null && !e.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            if (e!=null) {
                Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Exception:"+e);
            } else {
                Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Exception: Unknown");
            }
        }
    }
    public static void w(String message){
        if (isPrint && message!=null && !message.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.w(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message);
        }
    }
    public static void w(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.w(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString());
        }
    }
    public static void i(String message){
        if (isPrint && message!=null && !message.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.i(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message);
        }
    }
    public static void i(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.i(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString());
        }
    }
    public static void d(String message){
        if (isPrint && message!=null && !message.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.d(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message);
        }
    }
    public static void d(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.d(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString());
        }
    }
    public static void request(String request){
        if (isPrint) {
            if (request!=null && !request.equals("null") && !request.equals("")) {
                Log.d(PREFIX+"Server",request);
            }
            else{
                Log.d(PREFIX+"Server","Invalid");
            }
        }
    }
    public static void request(Object response){
        if (isPrint) {
            if (response!=null && !response.toString().equals("null") && !response.toString().equals("")) {
                Log.d(PREFIX+"Server",response.toString());
            }
            else{
                Log.d(PREFIX+"Server","Invalid");
            }
        }
    }
    public static void request(JSONObject response){
        if (isPrint) {
            if (response!=null && !response.toString().equals("null") && !response.toString().equals("")) {
                try {
                    Log.d(PREFIX+"Server Request",response.toString(4));
                } catch (JSONException e) {
                    Log.d(PREFIX+"Server Request",response.toString());
                }
            }
            else{
                Log.d(PREFIX+"Server Request","Invalid");
            }
        }
    }
    public static void request(String name, JSONObject response){
        if (isPrint) {
            if (response!=null && !response.toString().equals("null") && !response.toString().equals("")) {
                try {
                    Log.d(PREFIX+"Server:"+name,response.toString(4));
                } catch (JSONException e) {
                    Log.d(PREFIX+"Server:"+name,response.toString());
                }
            }
            else{
                Log.d(PREFIX+"Server:"+name,"Invalid");
            }
        }
    }

    public static void response(String response){
        response= response.replaceFirst("\n","");
        response= response.replaceFirst("\n","");
        response= response.replaceFirst("\n","");
        response= response.replaceFirst("\n","");
        if (isPrint) {
            if (response!=null && !response.equals("null") && !response.equals("")) {
                boolean isFirst = true;
                int maxLogStringSize = 1000;
                for(int i = 0; i <= response.length() / maxLogStringSize; i++) {
                    int start = i * maxLogStringSize;
                    int end = (i+1) * maxLogStringSize;
                    end = end > response.length() ? response.length() : end;
                    if (isFirst) {
                        Log.d(PREFIX+"Server", response.substring(start, end));
                        isFirst=false;
                    } else {
                        Log.d(PREFIX+"Server", response.substring(start, end));
                    }
                }
            }
            else{
                Log.d(PREFIX+"Server","Invalid");
            }
        }
    }
    public static void response(Object response){
        if (isPrint) {
            if (response!=null && !response.toString().equals("null") && !response.toString().equals("")) {
                boolean isFirst = true;
                int maxLogStringSize = 4000;
                for(int i = 0; i <= response.toString().length() / maxLogStringSize; i++) {
                    int start = i * maxLogStringSize;
                    int end = (i+1) * maxLogStringSize;
                    end = end > response.toString().length() ? response.toString().length() : end;
                    if (isFirst) {
                        Log.d(PREFIX+"Server", response.toString().substring(start, end));
                        isFirst=false;
                    } else {
                        Log.d(PREFIX+"Server", response.toString().substring(start, end));
                    }
                }
            }
            else{
                Log.d(PREFIX+"Server","Invalid");
            }
        }
    }
    public static void response(JSONObject response){
        if (isPrint) {
            if (response!=null && !response.toString().equals("null") && !response.toString().equals("")) {
                try {
                    Log.d(PREFIX+"Server Response", response.toString(4));
                } catch (JSONException e) {
                    Log.d(PREFIX+"Server Response", response.toString());
                }
            }
            else{
                Log.d(PREFIX+"Server Response","Invalid");
            }
        }
    }
    public static void v(String message){
        if (isPrint && message!=null && !message.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.v(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message);
        }
    }
    public static void v(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.v(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString());
        }
    }
    public static void wtf(String message){
        if (isPrint && message!=null && !message.equals("")) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.wtf(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message);
        }
    }
    public static void wtf(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.wtf(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString());
        }
    }

    public static void check(){
        if (isPrint) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Executed");
        }
    }

    public static void d(){
        if (isPrint) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.d(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Executed");
        }
    }

    public static void e(){
        if (isPrint) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :","Executed");
        }
    }

    public static void check(Object message){
        if (isPrint && message!=null) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.e(PREFIX+TAG+out.getFileName()+out.getMethodName()+out.getLineNumber()+" :",message.toString()+" executed");
        }
    }

    public static void page(){
        if (isPrint) {
            Out out = getInfo(Thread.currentThread().getStackTrace(), 3);
            Log.i(PREFIX+"Page",out.getFileName());
        }
    }
    private static Out getInfo(final StackTraceElement e[], final int level) {

        if (e != null && e.length >= level) {
            final StackTraceElement s = e[level];
            if (s != null) {
                Out out = new Out();
                out.setClassName(s.getClassName());
                out.setFileName(s.getFileName());
                out.setMethodName(s.getMethodName());
                out.setLineNumber(Integer.toString(s.getLineNumber()));


                if(!out.getClassName().equals("")){
                    out.setClassName(out.getClassName()+"");
                }
                if(!out.getFileName().equals("")){
                    out.setFileName(out.getFileName()+"");
                }
                if(!out.getMethodName().equals("")){
                    out.setMethodName(" => "+out.getMethodName()+"()");
                }
                if(!out.getLineNumber().equals("")){
                    out.setLineNumber(" => L-"+out.getLineNumber());
                }
                return out;
            }
        }
        return null;
    }
    static class Out{
        private String className = "";
        private String fileName = "";
        private String methodName = "";
        private String lineNumber = "";

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }
    }
}
