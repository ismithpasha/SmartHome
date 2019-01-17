package com.smarthomesystem.ju.smarthomesystemapplication.iot_fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.animations.PageTransitions;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.AboutUsFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.ContactFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.HomeControlFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.MapFragment;

import java.io.IOException;
import java.util.UUID;

import static com.smarthomesystem.ju.smarthomesystemapplication.security.UtilityModel.EXTRA_ADDRESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceControlFragment extends Fragment implements View.OnClickListener {

    View rootView;

    Button lightOneTurnOnBtn, lightOneTurnOffBtn, lightTwoTurnOnBtn, lightTwoTurnOffBtn,fanTurnOnBtn,fanTurnOffBtn,acTurnOnBtn,acTurnOffBtn;
    String address = "";
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;

    public DeviceControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_device_control, container, false);

        PageTransitions pageTransitions = new PageTransitions(getActivity(),rootView);
        pageTransitions.pageTransitionTopToBottom(700);

        address = EXTRA_ADDRESS;
        //call the widgets
        lightOneTurnOnBtn = rootView.findViewById(R.id.lightOneTurnOnBtn);
        lightOneTurnOnBtn.setOnClickListener(this);
        lightOneTurnOffBtn = rootView.findViewById(R.id.lightOneTurnOffBtn);
        lightOneTurnOffBtn.setOnClickListener(this);
        lightTwoTurnOnBtn = rootView.findViewById(R.id.lightTwoTurnOnBtn);
        lightTwoTurnOnBtn.setOnClickListener(this);
        lightTwoTurnOffBtn = rootView.findViewById(R.id.lightTwoTurnOffBtn);
        lightTwoTurnOffBtn.setOnClickListener(this);
        fanTurnOnBtn = rootView.findViewById(R.id.fanTurnOnBtn);
        fanTurnOnBtn.setOnClickListener(this);
        fanTurnOffBtn = rootView.findViewById(R.id.fanTurnOffBtn);
        fanTurnOffBtn.setOnClickListener(this);

        acTurnOnBtn = rootView.findViewById(R.id.acTurnOnBtn);
        acTurnOnBtn.setOnClickListener(this);
        acTurnOffBtn = rootView.findViewById(R.id.acTurnOffBtn);
        acTurnOffBtn.setOnClickListener(this);


       // Discnt = (ImageButton)findViewById(R.id.discnt);
     //   Abt = (ImageButton)findViewById(R.id.abt);

        new ConnectBT().execute(); //Call the class to connect



        return rootView;
    }

    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Connection Lost! Please go back and re-connect.");}
        }
       // finish(); //return to the first layout

    }

    private void turnOffLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("0".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Connection Lost! Please go back and re-connect.");
            }
        }
    }

    private void turnOnLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("1".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Connection Lost! Please go back and re-connect.");
            }
        }
    }

    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        int ftId = view.getId();

        if(ftId== R.id.lightOneTurnOnBtn){
            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("0".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.lightOneTurnOffBtn){
            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("1".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.lightTwoTurnOnBtn){

            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("2".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.lightTwoTurnOffBtn){

            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("3".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.fanTurnOnBtn){

            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("4".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.fanTurnOffBtn){

            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("5".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.acTurnOnBtn){

            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("6".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
        else if(ftId== R.id.acTurnOffBtn){

            if (btSocket!=null)
            {
                try
                {
                    btSocket.getOutputStream().write("7".toString().getBytes());
                }
                catch (IOException e)
                {
                    msg("Connection Lost! Please go back and re-connect.");
                }
            }
        }
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(getActivity(), "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
              // msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                msg("Connection Failed. Is the Bluetooth device is on? Please try again.");

                fragmentManager = getFragmentManager();
                currentFragment = new DeviceListFragment();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.MainLayout,currentFragment);
                transaction.addToBackStack("DeviceListFragment");
                transaction.commit();
               // finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();


        }
    }
}
