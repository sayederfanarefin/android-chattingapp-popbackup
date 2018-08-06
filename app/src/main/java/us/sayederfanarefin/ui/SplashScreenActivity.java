package us.sayederfanarefin.ui;

import android.os.Handler;

import us.sayederfanarefin.core.CoreActivity;
import us.sayederfanarefin.R;

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
        MainActivity_.intent(this).start();
    }


}
