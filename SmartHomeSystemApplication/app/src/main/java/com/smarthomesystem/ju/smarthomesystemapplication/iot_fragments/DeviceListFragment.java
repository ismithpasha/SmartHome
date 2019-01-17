package com.smarthomesystem.ju.smarthomesystemapplication.iot_fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.animations.PageTransitions;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.HomeControlFragment;

import static com.smarthomesystem.ju.smarthomesystemapplication.security.UtilityModel.EXTRA_ADDRESS;
import java.util.ArrayList;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceListFragment extends Fragment {

    View rootView;
    Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    private String lastFragment;
    Button btnPaired;
    ListView devicelist;
    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
   // public static String EXTRA_ADDRESS = "device_address";


    public DeviceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_device_list, container, false);

        PageTransitions pageTransitions = new PageTransitions(getActivity(),rootView);
        pageTransitions.pageTransitionTopToBottom(700);


        btnPaired = rootView.findViewById(R.id.findButton);
        devicelist = rootView.findViewById(R.id.bluetooth_list_view);

        //if the device has bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if(myBluetooth == null)
        {
            //Show a mensag. that the device has no bluetooth adapter
            Toast.makeText(getActivity(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

        }
        else if(!myBluetooth.isEnabled())
        {
            //Ask to the user turn the bluetooth on
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon,1);
        }
        pairedDevicesList();
        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pairedDevicesList();
            }
        });

        return rootView;
    }

    private void pairedDevicesList()
    {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getActivity(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            EXTRA_ADDRESS = address;

            fragmentManager = getFragmentManager();
            currentFragment = new DeviceControlFragment();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("DeviceControlFragment");
            transaction.commit();
            // Make an intent to start next activity.
    //       Intent i = new Intent(getActivity(), HomeControlActivity.class);
//
//            //Change the activity.
      //        i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
        //    startActivity(i);
        }
    };



}
