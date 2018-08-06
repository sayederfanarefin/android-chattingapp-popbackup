package us.sayederfanarefin.ui;

import android.content.Intent;
import android.os.Handler;

import us.sayederfanarefin.core.CoreActivity;
import us.sayederfanarefin.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


//@EActivity(R.layout.content_splash_screen)
//@Fullscreen
public class SplashScreenActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

			/* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(800); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.logoneworange); //or any other drawable
        configSplash.setAnimLogoSplashDuration(800); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeInDown); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash.setTitleSplash(this.getResources().getString(R.string.app_name));
        configSplash.setTitleTextColor(R.color.profilePictureBorder);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(800);
        configSplash.setAnimTitleTechnique(Techniques.FadeInUp);
        // configSplash.setTitleFont("fonts/Montserrat-Light.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {

//        Intent i = new Intent(SplashScreenActivity.this, FirstActivity.class);
//        startActivity(i);
//        finish();
    }

}
