package com.appmetrica.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.lang.Thread;

import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

import android.content.Context;
import android.util.Log;

public class AppMetricaPlugin extends CordovaPlugin {
    @Override
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if("activate".equals(action))
        {
            activate(args, callbackContext);
            return true;
        } else if("reportEvent".equals(action))
        {
            reportEvent(args, callbackContext);
            return true;
        } else if("reportEventJson".equals(action))
        {
            reportEventJson(args, callbackContext);
            return true;
        }
        return false;
    }
    
    private void activate(JSONArray parameters, final CallbackContext callbackContext) {
        try
        {
            final String devKey = parameters.getString(0);
            if(devKey != null) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        YandexMetrica.activate(cordova.getActivity().getApplicationContext(), devKey);
                        YandexMetrica.enableActivityAutoTracking(cordova.getActivity().getApplication());
                    }
                };
                this.cordova.getActivity().runOnUiThread(runnable);
                callbackContext.success();
            } else {
                callbackContext.error("Wrong arguments");
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
            return;
        }
    }
    
    private void reportEvent(JSONArray parameters, final CallbackContext callbackContext) {
        try
        {
            final String event = parameters.getString(0);
            if(event != null) {
                this.cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        YandexMetrica.reportEvent(event);
                    }
                });
                callbackContext.success();
            } else {
                callbackContext.error("Wrong arguments");
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
            return;
        }
    }
    
    private void reportEventJson(JSONArray parameters, final CallbackContext callbackContext) {
        try
        {
            final String event = parameters.getString(0);
            final JSONObject jObject = parameters.getJSONObject(1);
            if(event != null && jObject != null) {
                this.cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        YandexMetrica.reportEvent(event, jObject.toString());
                    }
                });
                callbackContext.success();
            } else {
                callbackContext.error("Wrong arguments");
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
            return;
        }
    }
    
}
