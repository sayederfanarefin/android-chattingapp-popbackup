package us.sayederfanarefin.ui.Main.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import us.sayederfanarefin.R;
import us.sayederfanarefin.adapter.DrawerListAdapter;
import us.sayederfanarefin.core.CoreActivity;
import us.sayederfanarefin.core.CoreFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EFragment(R.layout.fragment_main )
public class MainFragment extends CoreFragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView toolbarText;
    private TextView user_nav_name, user_nav_status;
    private ImageView user_nav_pro_pic;

    @ViewById
    private ImageButton close_nav;
    @ViewById
    private ListView lv_drawer;
    @ViewById
    private DrawerLayout drawer_layout;


    private ArrayList<String> navigation_items;
    private DrawerListAdapter drawerListAdapter;



    private DrawerLayout drawer;
    private RelativeLayout drawer_2;

    //Firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //databses
    private DatabaseReference rootRef;
    private FirebaseDatabase rootDb;
    private DatabaseReference userRef;

    public MainFragment() {
        //Mandatory default constructor
    }

    @AfterViews
    void afterViews() {
       // ((CoreActivity) getActivity()).getSupportActionBar().setTitle(R.string.tag_choose_gnr_entry_method);
    }

    @Click
    void close_nav() {
        drawer_layout.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawer.openDrawer(drawer_2);

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}


