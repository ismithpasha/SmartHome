package com.smarthomesystem.ju.smarthomesystemapplication.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import android.Manifest;
import com.smarthomesystem.ju.smarthomesystemapplication.R;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.AboutUsFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.ContactFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.HomeFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.LoginFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.MapFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.MembersFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.fragments.SignupFragment;
import com.smarthomesystem.ju.smarthomesystemapplication.iot_fragments.DeviceListFragment;

import static com.smarthomesystem.ju.smarthomesystemapplication.security.UserInfo.UserLoggedIn;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {
    public static final int PERMISSIONS_REQUEST = 100;
    Fragment currentFragment;
    FragmentTransaction transaction;
    FragmentManager topupFragManager;
    boolean doubleBackToExitPressedOnce = false;
    private String lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.application_title);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Check whether GPS tracking is enabled//

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
           // finish();
         //   Toast.makeText(this, "Please enable GPS service", Toast.LENGTH_SHORT).show();

        }

        //Check whether this app has access to the location permission//
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

//If the location permission has been granted, then start the TrackerService//

        if (permission == PackageManager.PERMISSION_GRANTED) {
            // startTrackerService();
        } else {

//If the app doesn’t currently have access to the user’s location, then request access//

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }


        if(UserLoggedIn.equals("Y")||UserLoggedIn=="Y")
        {
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_login).setVisible(false);
            nav_Menu.findItem(R.id.nav_signup).setVisible(true);
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);

        }
        else{
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_login).setVisible(true);
            nav_Menu.findItem(R.id.nav_signup).setVisible(false);
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
        }

        if(UserLoggedIn=="Y")
        {
            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new HomeFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("HomeFragment");
            transaction.commit();
          // startTrackerService();
        }
        else
        {
            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new LoginFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("LoginFragment");
            transaction.commit();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {

//If the permission has been granted...//

        if (requestCode == PERMISSIONS_REQUEST && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            //...then start the GPS tracking service//

            //  startTrackerService();
        } else {

//If the user denies the permission request, then display a toast with some more information//

            Toast.makeText(this, "Please enable location services to allow GPS tracking", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            // Back Pressed Custom Start
            RequestQueue mRequestQueue = Volley.newRequestQueue(this);
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });

            int count = topupFragManager.getBackStackEntryCount();
            if (count > 0) {
                FragmentManager.BackStackEntry entry = topupFragManager.getBackStackEntryAt(count - 1);
                lastFragment = entry.getName();

                if(lastFragment.equals("HomeFragment")){
                    if (doubleBackToExitPressedOnce) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);

                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                        return;
                    }

                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, "Please click again to go Exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                }
                else if(lastFragment.equals("DeviceControlFragment")){
                    topupFragManager = getFragmentManager();
                    topupFragManager.addOnBackStackChangedListener(this);
                    currentFragment = new DeviceListFragment();
                    transaction = topupFragManager.beginTransaction();
                    transaction.replace(R.id.MainLayout, currentFragment);
                    transaction.addToBackStack("DeviceListFragment");
                    transaction.commit();
                }
                else {
                    topupFragManager = getFragmentManager();
                    topupFragManager.addOnBackStackChangedListener(this);
                    currentFragment = new HomeFragment();
                    transaction = topupFragManager.beginTransaction();
                    transaction.replace(R.id.MainLayout, currentFragment);
                    transaction.addToBackStack("HomeFragment");
                    transaction.commit();
                }

            } else {
                super.onBackPressed();
            }
            // Back Pressed Custom End

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_us) {
            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new AboutUsFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("AboutUsFragment");
            transaction.commit();
        } else if (id == R.id.nav_home) {
            if(UserLoggedIn=="Y")
            {
                topupFragManager = getFragmentManager();
                topupFragManager.addOnBackStackChangedListener(this);
                currentFragment = new HomeFragment();
                transaction = topupFragManager.beginTransaction();
                transaction.replace(R.id.MainLayout,currentFragment);
                transaction.addToBackStack("HomeFragment");
                transaction.commit();
            }
            else
            {
                Toast.makeText(this, "Please sign in first.", Toast.LENGTH_SHORT).show();
            }


        }  else if (id == R.id.nav_locations) {
            if(UserLoggedIn=="Y")
            {
            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new MapFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("MapFragment");
            transaction.commit();
            }
            else
            {
                Toast.makeText(this, "Please sign in first.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.nav_emergency) {

            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new ContactFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("ContactFragment");
            transaction.commit();
        }
//        else if (id == R.id.nav_house_control) {
//                if(UserLoggedIn=="Y")
//                {
//                    topupFragManager = getFragmentManager();
//                    topupFragManager.addOnBackStackChangedListener(this);
//                    currentFragment = new HomeControlFragment();
//                    transaction = topupFragManager.beginTransaction();
//                    transaction.replace(R.id.MainLayout,currentFragment);
//                    transaction.addToBackStack("HomeControlFragment");
//                    transaction.commit();
//                }
//                else
//                {
//                    Toast.makeText(this, "Please sign in first.", Toast.LENGTH_SHORT).show();
//                }
//        }
        else if (id == R.id.nav_other_members) {
            if(UserLoggedIn=="Y")
            {
                topupFragManager = getFragmentManager();
                topupFragManager.addOnBackStackChangedListener(this);
                currentFragment = new MembersFragment();
                transaction = topupFragManager.beginTransaction();
                transaction.replace(R.id.MainLayout,currentFragment);
                transaction.addToBackStack("MembersFragment");
                transaction.commit();
            }
            else
            {
                Toast.makeText(this, "Please sign in first.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.nav_house_control) {
            if(UserLoggedIn=="Y")
            {
                topupFragManager = getFragmentManager();
                topupFragManager.addOnBackStackChangedListener(this);
                currentFragment = new DeviceListFragment();
                transaction = topupFragManager.beginTransaction();
                transaction.replace(R.id.MainLayout,currentFragment);
                transaction.addToBackStack("DeviceListFragment");
                transaction.commit();
            }
            else
            {
                Toast.makeText(this, "Please sign in first.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.nav_login) {

            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new LoginFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("LoginFragment");
            transaction.commit();

        }
        else if (id == R.id.nav_logout) {
            UserLoggedIn = "N";
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_login).setVisible(true);
            nav_Menu.findItem(R.id.nav_signup).setVisible(false);
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_signup) {

            NavigationView navigationView =  findViewById(R.id.nav_view);
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_login).setVisible(false);
            nav_Menu.findItem(R.id.nav_signup).setVisible(true);
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);

            topupFragManager = getFragmentManager();
            topupFragManager.addOnBackStackChangedListener(this);
            currentFragment = new SignupFragment();
            transaction = topupFragManager.beginTransaction();
            transaction.replace(R.id.MainLayout,currentFragment);
            transaction.addToBackStack("SignupFragment");
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {
        int count = topupFragManager.getBackStackEntryCount();
        for(int i = count-1; i >= 0 ; i-- ){
            FragmentManager.BackStackEntry entry = topupFragManager.getBackStackEntryAt(i);
            lastFragment = entry.getName();
            //Toast.makeText(this,entry.getName(),Toast.LENGTH_SHORT).show();
            //  Log.i(TAG, "FoundFragment: "+i +" --> "+ entry.getName());
        }

    }
}
