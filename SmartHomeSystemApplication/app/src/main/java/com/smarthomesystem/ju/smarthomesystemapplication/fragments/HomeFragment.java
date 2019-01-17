package com.smarthomesystem.ju.smarthomesystemapplication.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.animations.PageTransitions;
import com.smarthomesystem.ju.smarthomesystemapplication.iot_fragments.DeviceListFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, FragmentManager.OnBackStackChangedListener {

    View rootView;
    Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    private String lastFragment;
    ImageView home_control_image,locationsImage,messagesImage,contactusImage;
    TextView home_company_name_tv,home_company_moto_tv,home_contactus_tv,home_about_tv,home_order_tv,home_menu_tv;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        home_company_name_tv = rootView.findViewById(R.id.home_company_name_tv);
        home_company_moto_tv = rootView.findViewById(R.id.home_company_moto_tv);
        home_menu_tv = rootView.findViewById(R.id.home_dashboard_tv);
        home_order_tv = rootView.findViewById(R.id.home_location_tv);
        home_about_tv = rootView.findViewById(R.id.home_about_tv);
        home_contactus_tv = rootView.findViewById(R.id.home_contactus_tv);


        home_control_image =  rootView.findViewById(R.id.home_control_image);
        locationsImage =  rootView.findViewById(R.id.locationsImage);
        messagesImage =  rootView.findViewById(R.id.messagesImage);
        contactusImage =  rootView.findViewById(R.id.contactusImage);

        home_control_image.setImageResource(R.drawable.home_control_icon);
        locationsImage.setImageResource(R.drawable.locations_icon);
        messagesImage.setImageResource(R.drawable.message_icon);
        contactusImage.setImageResource(R.drawable.contact_us_icon);

        home_control_image.setOnClickListener(this);
        locationsImage.setOnClickListener(this);
        messagesImage.setOnClickListener(this);
        contactusImage.setOnClickListener(this);

        PageTransitions pageTransitions = new PageTransitions(getActivity(),rootView);
        pageTransitions.pageTransitionTopToBottom(700);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        int ftId = view.getId();

        if(ftId== R.id.home_control_image){
            fragmentManager = getFragmentManager();
            fragmentManager.addOnBackStackChangedListener(this);
            currentFragment = new DeviceListFragment();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("DeviceListFragment");
            transaction.commit();
        }
        else if(ftId== R.id.locationsImage){

            fragmentManager = getFragmentManager();
            fragmentManager.addOnBackStackChangedListener(this);
            currentFragment = new MapFragment();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("MapFragment");
            transaction.commit();
        }
        else if(ftId== R.id.messagesImage){

            fragmentManager = getFragmentManager();
            fragmentManager.addOnBackStackChangedListener(this);
            currentFragment = new MembersFragment();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("MembersFragment");
            transaction.commit();
        }
        else if(ftId== R.id.contactusImage){

            fragmentManager = getFragmentManager();
            fragmentManager.addOnBackStackChangedListener(this);
            currentFragment = new ContactFragment();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("ContactFragment");
            transaction.commit();
        }

    }

    @Override
    public void onBackStackChanged() {
        int count = fragmentManager.getBackStackEntryCount();
        for(int i = count-1; i >= 0 ; i-- ){
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            lastFragment = entry.getName();
            //Toast.makeText(this,entry.getName(),Toast.LENGTH_SHORT).show();
            //  Log.i(TAG, "FoundFragment: "+i +" --> "+ entry.getName());
        }
    }
}
