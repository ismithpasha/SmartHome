package com.smarthomesystem.ju.smarthomesystemapplication.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.adaptermodels.MemberInfo;
import com.smarthomesystem.ju.smarthomesystemapplication.adaptermodels.MemberInfoAdapter;
import com.smarthomesystem.ju.smarthomesystemapplication.animations.PageTransitions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MembersFragment extends Fragment {

    View rootView;
    Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    private String lastFragment;
    Dialog dialog;
    Dialog emailDialog;
    private static final int REQUEST_PHONE_CALL = 1;

    String contact_branch_name;
    String contact_address;
    String contact_email;
    String contact_phone;

    String TAG = "Members";

    ArrayList<MemberInfo> memberList;
    private MemberInfoAdapter adapter;
    private ListView listView;

    public MembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_members, container, false);

        PageTransitions pageTransitions = new PageTransitions(getActivity(),rootView);
        pageTransitions.pageTransitionTopToBottom(700);
        ViewContactList();
        return rootView;
    }


    public void ViewContactList()
    {
        memberList =new ArrayList<MemberInfo>();

        listView  = rootView.findViewById(R.id.member_list_view);


        adapter = new MemberInfoAdapter(getActivity(), memberList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView contact_branch_name_text =  view.findViewById(R.id.memberName);
                TextView contact_address_text =  view.findViewById(R.id.memberId);
                TextView contact_email_text =  view.findViewById(R.id.memberEmail);
                TextView contact_phone_text =  view.findViewById(R.id.memberMobileNo);


                contact_branch_name = contact_branch_name_text.getText().toString();
                contact_address = contact_address_text.getText().toString();
                contact_email = contact_email_text.getText().toString();
                contact_phone = contact_phone_text.getText().toString();

                ContactDialog();
            }
        });

        try {

            //*********** Volley ********* //
            String tvUrl = "http://smarthomesystem.ismithpasha.com/MobileAPI/getUsers.php";


            RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            StringRequest strReq = new StringRequest(Request.Method.GET, tvUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //*********** Volley ********* //

                    String treamedResp = response.replaceAll("\\\\","");
                    String trimedResponse = treamedResp.substring(1,treamedResp.length()-2);

                    Log.d("treamedSesponse",trimedResponse);

                    try{
                        Log.d("inTry12","Entered");


                        JSONArray arrayResponse = new JSONArray(trimedResponse);

                        Log.d("inTry2","Entered");
                        for (int i = 0; i < arrayResponse.length(); i++)
                        {

                            JSONObject obj= arrayResponse.getJSONObject(i);
                            Log.d("f212f","E2");

                            String bId = obj.getString("id");
                            String bName = obj.getString("name");
                            String phoneNo = obj.getString("phone");
                            String emailId = obj.getString("email");

                            MemberInfo memberInfo=new MemberInfo(bId,bName,emailId,phoneNo);

                            memberList.add(memberInfo);

                        }

                    }
                    catch (JSONException e){
                        Log.d("JSONException",e.getMessage().toString());
                        e.printStackTrace();
                    }

                    adapter.notifyDataSetChanged();

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

    public void ContactDialog()
    {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.member_dialog);
//        dialog.setTitle("Select End Date:");

        TextView contact_branch_name_text = (TextView) dialog.findViewById(R.id.member_name);
        TextView contact_email_text = (TextView) dialog.findViewById(R.id.contact_email);
        TextView contact_phone_text = (TextView) dialog.findViewById(R.id.contact_phone);

        contact_branch_name_text.setText(contact_branch_name);

        contact_email_text.setText(contact_email);
        contact_phone_text.setText(contact_phone);

        LinearLayout contact_phone_layout = (LinearLayout) dialog.findViewById(R.id.contact_phone_layout);
        LinearLayout contact_email_layout = (LinearLayout) dialog.findViewById(R.id.contact_email_layout);
        LinearLayout contact_chat_layout = (LinearLayout) dialog.findViewById(R.id.contact_chat_layout);
        Button cancelContactButton = (Button) dialog.findViewById(R.id.cancelContactButton);


        contact_phone_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if (Build.VERSION.SDK_INT < 23){
                        call();
                    } else {
                        checkPermissions();
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity(), "Can not able to make a Phone Call", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });

        contact_email_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{contact_email});

                //     emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                //   emailIntent.putExtra(Intent.EXTRA_TEXT, "Content: ");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });

        contact_chat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Toast.makeText(getActivity(), "Sorry, This feature will be available soon.", Toast.LENGTH_SHORT).show();
//                    fragmentManager = getFragmentManager();
//                    currentFragment = new MessageFragment();
//                    transaction = fragmentManager.beginTransaction();
//                    transaction.replace(R.id.MainLayout,currentFragment);
//                    transaction.addToBackStack("MessageFragment");
//                    transaction.commit();

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });



        cancelContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void checkPermissions() {
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            call();
        } else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                    Toast.makeText(getActivity(),"Call permission is needed to make this call",Toast.LENGTH_LONG).show();
                }

                requestPermissions(new  String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PHONE_CALL){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                call();
            } else {
                Toast.makeText(getActivity(),"Call Permission was not granted",Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @SuppressLint("MissingPermission")
    public  void call(){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+contact_phone));
        //noinspection MissingPermission
        startActivity(callIntent);
    }


}
