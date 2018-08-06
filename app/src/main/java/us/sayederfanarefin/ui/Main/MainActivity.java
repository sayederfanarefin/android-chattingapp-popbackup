package us.sayederfanarefin.ui.Main;

import android.os.Bundle;
import android.os.Handler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

import us.sayederfanarefin.R;
import us.sayederfanarefin.core.CoreActivity;
import us.sayederfanarefin.ui.Main.Fragments.MainFragment;

@EActivity(R.layout.activity_main)
@Fullscreen
public class MainActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews() {
        loadFragment(MainFragment_.builder().build());
    }

}
