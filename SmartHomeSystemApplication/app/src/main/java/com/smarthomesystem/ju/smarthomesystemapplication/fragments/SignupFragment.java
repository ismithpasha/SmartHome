package com.smarthomesystem.ju.smarthomesystemapplication.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.activities.MainActivity;
import com.smarthomesystem.ju.smarthomesystemapplication.animations.PageTransitions;
import com.smarthomesystem.ju.smarthomesystemapplication.connectivity.NetworkAvailability;

import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserEmail;
import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserLoggedIn;
import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserPassword;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {

    View rootView;
    boolean doubleBackToExitPressedOnce = false;

    EditText passwordEditText,emailEditText,nameEditText,phoneNumberEditText;
    String email, password, name, mobile;

    Button signUpButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        PageTransitions pageTransitions = new PageTransitions(getActivity(),rootView);
        pageTransitions.pageTransitionTopToBottom(700);

        mAuth = FirebaseAuth.getInstance();

        passwordEditText = (EditText) rootView.findViewById(R.id.passwordEditText);
        emailEditText = (EditText) rootView.findViewById(R.id.emailEditText);

        signUpButton = (Button) rootView.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

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

        if(ftId== R.id.signUpButton){
            if(!NetworkAvailability.isNetworkAvailable(this.getActivity())) {
                //ShowDialogs.noInternetDialog(this.getActivity());
                Toast.makeText(getActivity(), "Sorry!!! No Internet Connection!",
                        Toast.LENGTH_LONG).show();
            }
            else {
                SignUp();




            }
        }
    }


    public void SignUp()
    {
        passwordEditText = getActivity().findViewById(R.id.passwordEditText);
        emailEditText = getActivity().findViewById(R.id.emailEditText);
        phoneNumberEditText = getActivity().findViewById(R.id.phoneNumberEditText);
        nameEditText = getActivity().findViewById(R.id.nameEditText);

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        name = nameEditText.getText().toString();
        mobile = phoneNumberEditText.getText().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(mobile)||TextUtils.isEmpty(password))
        {
            Toast.makeText(getActivity(), "All Field Required",
                    Toast.LENGTH_LONG).show();
        }
        final String TAG = "SignUp";

        // Important Issue
        try {

            //*********** Volley ********* //
            String tvUrl = "http://smarthomesystem.ismithpasha.com/MobileAPI/registration.php?Name="+name.trim()+"&Email="+email.trim()+"&Password="+password.trim()+"&Phone="+mobile.trim();


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
                        Toast.makeText(getActivity(), "User successfully registered!",  Toast.LENGTH_LONG).show();

                        passwordEditText.setText("");
                        emailEditText.setText("");
                        phoneNumberEditText.setText("");
                        nameEditText.setText("");

                    }
                    else if(response.equals("EmailPhoneExist"))
                    {
                        Toast.makeText(getActivity(), "User already registered!",  Toast.LENGTH_LONG).show();
                    }
                    else if(response.equals("SignUpFailed"))
                    {
                        Toast.makeText(getActivity(), "Registration failed!",  Toast.LENGTH_LONG).show();
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
