package com.sweetitech.expensemanager.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sweetitech.expensemanager.R;
import com.sweetitech.expensemanager.core.CoreActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

@EActivity(R.layout.content_splash_screen)
@Fullscreen
public class SplashScreenActivity extends CoreActivity {

    @AfterViews
    void afterViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openFirstActivity();
                finish();
            }
        }, 1000);
    }

    private void openFirstActivity() {
        FirstActivity_.intent(this).start();
    }

}
