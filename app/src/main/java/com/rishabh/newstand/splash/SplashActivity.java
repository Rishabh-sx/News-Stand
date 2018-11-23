package com.rishabh.newstand.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.crashlytics.android.Crashlytics;
import com.rishabh.newstand.R;
import com.rishabh.newstand.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enterFullScreenMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //causeCrash();
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        };
        handler.postDelayed(runnable, 1500);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    public void enterFullScreenMode() {
        //Here activity will come in to full screen mode as to show splash view.
        if (getWindow() != null) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    private void causeCrash() {
     //   throw new NullPointerException("Fake null pointer exception");
        Crashlytics.getInstance().crash();
    }
}
