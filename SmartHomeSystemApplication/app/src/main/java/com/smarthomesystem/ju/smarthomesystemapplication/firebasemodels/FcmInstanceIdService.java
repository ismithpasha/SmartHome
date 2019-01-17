package com.smarthomesystem.ju.smarthomesystemapplication.firebasemodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.smarthomesystem.ju.smarthomesystemapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by ismith-fsl on 3/13/2018.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String recentToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + recentToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_Pref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_Token),recentToken);
        editor.commit();
       // sendRegistrationToServer(refreshedToken);
        SaveFcmTokenId(recentToken);
    }

    private void SaveFcmTokenId(String token){

        final String TAG = "SplashScreen";
        String finalVerifyInstanceId = "http://smarthomesystem.ismithpasha.com/MobileAPI/saveFcmToken.php?fcmtoken="+token;

        try {
            final RequestQueue mRequestQueue = Volley.newRequestQueue(this);
            final StringRequest strReq = new StringRequest(Request.Method.POST, finalVerifyInstanceId, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //  response = response.replaceAll("\\\\","");
                    //  response= response.substring(1,response.length()-1);

                    try {

                        Log.d("Instance_verify", response);
                        JSONObject user = new JSONObject(response);

                        // Log.d("userCode1N",userCode);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG,"Error --->    " + error.getMessage());
                    try {

                        //   ShowDialogs.networkErrorException(SplashActivity.this,"Connection timed out!","Sorry!","Exit","Try again",getIntent());
                        //  Log.d(TAG,"Error --->    " + error.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            strReq.setRetryPolicy(new DefaultRetryPolicy(40 * 1000,1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(strReq);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
