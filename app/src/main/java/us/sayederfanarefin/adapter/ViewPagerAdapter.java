package us.sayederfanarefin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import us.sayederfanarefin.fragment.ChatFragment;
import us.sayederfanarefin.fragment.FriendFragment;
import us.sayederfanarefin.fragment.HistoryFragment;
import us.sayederfanarefin.fragment.InfoFragment;


/**
 * Created by piashsarker on 7/10/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 3) {
            return new FriendFragment();
        } else if (position == 2) {
            return new ChatFragment();
        } else if (position == 1) {
            return new HistoryFragment();
        } else {
            return new InfoFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}