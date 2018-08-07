package us.sayederfanarefin.ui.Main.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import us.sayederfanarefin.R;
import us.sayederfanarefin.adapter.DrawerListAdapter;
import us.sayederfanarefin.adapter.ViewPagerAdapter;
import us.sayederfanarefin.core.CoreActivity;
import us.sayederfanarefin.core.CoreFragment;
import us.sayederfanarefin.commons.Constants;
import us.sayederfanarefin.model.users;
import us.sayederfanarefin.utils.FontOverride;
import us.sayederfanarefin.utils.database;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static us.sayederfanarefin.commons.Constants.drawer_icons;


@EFragment(R.layout.fragment_main )
public class MainFragment extends CoreFragment {

    @ViewById
    private Toolbar toolbar;

    @ViewById
    private TabLayout tab_layout;

    @ViewById
    private ViewPager view_pager;

    @ViewById
    private TextView toolbar_title;

    @ViewById
    private TextView user_nav_name;

    @ViewById
    private TextView user_nav_status;

    @ViewById
    private ImageView nav_profile_image;

    @ViewById
    private ImageButton close_nav;

    @ViewById
    private ListView lv_drawer;

    @ViewById
    private DrawerLayout drawer_layout;

    @ViewById
    private RelativeLayout drawer_2;

    private ArrayList<String> navigation_items;
    private DrawerListAdapter drawerListAdapter;

    //Firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //databses
    private DatabaseReference rootRef;
    private FirebaseDatabase rootDb;
    private DatabaseReference userRef;

    public MainFragment() {
        //Mandatory default constructor
        mFirebaseAuth = FirebaseAuth.getInstance();
        rootDb = database.getDatabase();
        rootRef = rootDb.getReference();
        userRef = rootRef.child(Constants.firebaseUserNode);
        userRef.keepSynced(true);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                init_using_firebase_user(firebaseAuth.getCurrentUser());
            }
        };
        init_using_firebase_user(mFirebaseAuth.getCurrentUser());

    }

    @AfterViews
    void afterViews() {
       // ((CoreActivity) getActivity()).getSupportActionBar().setTitle(R.string.tag_choose_gnr_entry_method);

        view_pager.setCurrentItem(0);
        toolbar_title.setText("Home");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();
        drawer_layout.addDrawerListener(toggle);


        ((CoreActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((CoreActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        ((CoreActivity) getActivity()).setSupportActionBar(toolbar);

        ((CoreActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        FontOverride.setDefaultFont(getActivity(), "MONOSPACE", "font/Montserrat-Light.ttf");

        navigation_items = new ArrayList<>();

        //adding menu items for naviations
        navigation_items.add("Timeline");
        navigation_items.add("Message");
        navigation_items.add("Music");
        //navigation_items.add("Notifications");
        navigation_items.add("Profile");
        navigation_items.add("Settings");
        navigation_items.add("Logout");

        drawerListAdapter = new DrawerListAdapter(getActivity(), navigation_items, drawer_icons
        );
        lv_drawer.setAdapter(drawerListAdapter);

        lv_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(navigation_items.get(position).equalsIgnoreCase("Timeline")){
                    Intent intent = new Intent(MainActivity_.this, MainActivity_.class);
                    intent.putExtra("frag", "timeline");
                    startActivity(intent);
                }else if(navigation_items.get(position).equalsIgnoreCase("Message")){
                    Intent intent = new Intent(MainActivity_.this, MainActivity_.class);
                    intent.putExtra("frag", "message");
                    startActivity(intent);


                }else if(navigation_items.get(position).equalsIgnoreCase("Profile")){
//
//                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                    startActivitentty(intent);

                }else if(navigation_items.get(position).equalsIgnoreCase("Music")){

//                    Intent intent = new Intent(MainActivity.this, MusicSettings.class);
//                    startActivity(intent);

                }
                else if(navigation_items.get(position).equalsIgnoreCase("Logout")){
//
//                    AuthUI.getInstance().signOut(MainActivity.this);
//                    Intent intent = new Intent(MainActivity.this, FirstScreen.class);
//                    startActivity(intent);
                }
            }
        });




        for (int i = 0; i < Constants.pageIcon.length; i++) {
            tab_layout.addTab(tab_layout.newTab().setIcon(Constants.pageIcon[i]));
        }
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        //  viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(((CoreActivity) getActivity()).getSupportFragmentManager());
        view_pager.setAdapter(pagerAdapter);

        view_pager.setOffscreenPageLimit(1);


        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        toolbar_title.setText("Home");
                        break;
                    case 1:
                        toolbar_title.setText("Timeline");
                        break;
                    case 2:
                        toolbar_title.setText("Chat");
                        break;
                    case 3:
                        toolbar_title.setText("Friends");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


    }

    @Click
    void close_nav() {
        drawer_layout.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawer_layout.openDrawer(drawer_2);

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    private void init_using_firebase_user(FirebaseUser user){
        if (user != null) {
            userRef.child(user.getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            populate_user_info(snapshot.getValue(users.class));
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {}});

        } else {
//            Intent intent = new Intent(MainActivity.this, FirstScreen.class);
//            startActivity(intent);
//            finish();

            MainActivity_.intent(this).start();
        }
    }


    private void populate_user_info(users me){

        if(me!=null){


            if(me.getUsername() != null && !me.getUsername().equals("")){
                user_nav_name.setText(me.getUsername());
            }else{
                user_nav_name.setText("");
            }


            if(me.getMood() != null && me.getMood() != ""){
                user_nav_status.setText(me.getMood());
            }

            // String temp_propic = me.getProfilePicLocation();
            if (me.getProfilePicLocation() != null ){
                //if(isAttached){
                    Glide.with(nav_profile_image.getContext())
                            .load(me.getProfilePicLocation())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .bitmapTransform(new CropCircleTransformation(((CoreActivity) getActivity()).getApplicationContext()))
                            .into(nav_profile_image);
               // }

            }
        }else{
            Log.v("----------", "empty users");
        }
    }


}


