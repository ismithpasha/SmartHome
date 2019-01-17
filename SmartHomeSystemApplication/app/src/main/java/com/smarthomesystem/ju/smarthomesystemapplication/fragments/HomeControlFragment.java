package com.smarthomesystem.ju.smarthomesystemapplication.fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarthomesystem.ju.smarthomesystemapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeControlFragment extends Fragment {
    View rootView;
    android.app.Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;

    public HomeControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home_control, container, false);

        return rootView;

    }

}
