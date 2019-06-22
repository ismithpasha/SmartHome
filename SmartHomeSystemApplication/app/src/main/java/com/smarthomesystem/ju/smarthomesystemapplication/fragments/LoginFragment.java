package com.smarthomesystem.ju.smarthomesystemapplication.fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.activities.MainActivity;
import com.smarthomesystem.ju.smarthomesystemapplication.adaptermodels.ContactInfo;
import com.smarthomesystem.ju.smarthomesystemapplication.connectivity.NetworkAvailability;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserEmail;
import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserLoggedIn;
import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserPassword;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    View rootView;
    boolean doubleBackToExitPressedOnce = false;
    Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    private String lastFragment;
    EditText passwordEditText,emailEditText;
    Button loginButton,signUpBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    String email;
    String password;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();

        passwordEditText =  rootView.findViewById(R.id.passwordEditText);
        emailEditText =  rootView.findViewById(R.id.emailEditText);

        loginButton =  rootView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null)
                {
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }

            }
        };

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthStateListener);
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    @Override
    public void onClick(View view) {
        int ftId = view.getId();

        if(ftId== R.id.loginButton){
            passwordEditText = getActivity().findViewById(R.id.passwordEditText);
            emailEditText = getActivity().findViewById(R.id.emailEditText);

            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();
            if(email.equals("user@mail.com")&&password.equals("123456")){
                UserEmail = email;
                UserPassword = password;
                UserLoggedIn = "Y";
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
            else{
                if(!NetworkAvailability.isNetworkAvailable(this.getActivity())) {
                    //ShowDialogs.noInternetDialog(this.getActivity());
                    Toast.makeText(getActivity(), "Sorry!!! No Internet Connection!",  Toast.LENGTH_LONG).show();
                }
                else {
                    Login();
                }
            }

        }
    }

    public void Login()
    {
        passwordEditText = getActivity().findViewById(R.id.passwordEditText);
        emailEditText = getActivity().findViewById(R.id.emailEditText);

         email = emailEditText.getText().toString();
         password = passwordEditText.getText().toString();

        final String TAG = "Login";

        // Important Issue
        try {

            //*********** Volley ********* //
            String tvUrl = "http://smarthomesystem.ismithpasha.com/MobileAPI/login.php?email="+email.trim()+"&password="+password.trim();


            RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            StringRequest strReq = new StringRequest(Request.Method.GET, tvUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //*********** Volley ********* //

                    // Log.d("ResponseInTry",response);
//
//                    String treamedResp = response.replaceAll("\\\\","");
//                    String trimedResponse = treamedResp.substring(1,treamedResp.length()-2);

                    Log.d("LoginResponse",response);

                    if(response.equals("success"))
                    {
                        UserEmail = email;
                        UserPassword = password;
                        UserLoggedIn = "Y";
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                    else if(response.equals("invaliduser"))
                    {
                        Toast.makeText(getActivity(), "User is not registered!",  Toast.LENGTH_LONG).show();
                    }
                    else if(response.equals("wrongpassword"))
                    {
                        Toast.makeText(getActivity(), "Wrong password!",  Toast.LENGTH_LONG).show();
                    }
                    //*********** Volley ********* //
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onErrorResponse", "onErrorResponse: " + error);
                    Toast.makeText(getActivity(), "Please Check Your Internet Connection!", Toast.LENGTH_LONG).show();
                }
            });

            strReq.setShouldCache(false);
            mRequestQueue.add(strReq);

            //*********** Volley ********* //

        } catch (Exception e){
            e.printStackTrace();
            Log.d("Exception 1",e.toString());
        }


    }


}
