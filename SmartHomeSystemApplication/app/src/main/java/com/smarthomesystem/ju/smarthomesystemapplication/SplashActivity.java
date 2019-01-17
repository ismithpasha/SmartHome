package com.smarthomesystem.ju.smarthomesystemapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.smarthomesystem.ju.smarthomesystemapplication.activities.HomeActivity;
import com.smarthomesystem.ju.smarthomesystemapplication.activities.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {



    int trackInternet = 0;

    ProgressBar progressBar;
    int progressStatus = 0;
    TextView progressPercentage;

    Handler handler = new Handler();

    private Timer autoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    private void updateOnCreate() {
        // your logic here
//        if (NetworkAvailability.isNetworkAvailable(SplashActivity.this)) {

        progressPercentage =  findViewById(R.id.load_per);
        progressBar =  findViewById(R.id.progressBar1);
        progressBar.setScaleY(3f);
        progressBar.getProgressDrawable().setColorFilter(Color.rgb(122, 187, 106), PorterDuff.Mode.SRC_IN);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    if (progressStatus == 10) {

                        // functions
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            progressPercentage.setText(progressStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //Log.d("userCodeN",SecurityInfo.getUserCode());
                if (progressStatus == 100) {

                    try {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Log.d("error", e + "");

                    }

                }
            }
        }).start();
//        } else {
//            if (trackInternet >= 1) {
//                finish();
//                startActivity(getIntent());
//
//            }
//            //    ShowDialogs.noInternetDialog(this);
//            trackInternet++;
//        }
    }


    @Override
    public void onResume() {
        super.onResume();
        autoUpdate = new Timer();

        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        updateOnCreate();
                    }
                });
            }
        }, 0, 5000); // updates each 40 secs
    }

    @Override
    public void onPause() {
        autoUpdate.cancel();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
        System.exit(0);

        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }



}
