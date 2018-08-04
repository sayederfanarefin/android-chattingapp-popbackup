package com.sweetitech.expensemanager.ui;

import android.os.Bundle;

import com.sweetitech.expensemanager.R;
import com.sweetitech.expensemanager.core.CoreActivity;
import com.sweetitech.expensemanager.ui.firstFragment.FirstFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by tasnimankonmanzur on 10/5/18.
 */

@EActivity(R.layout.activity_first)
public class FirstActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViews() {
        loadFragment(FirstFragment_.builder().build());
    }
}
