package com.songjachin.wanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.songjachin.base.activity.MvvmActivity;
import com.songjachin.base.viewmodel.MvvmBaseViewModel;
import com.songjachin.wanandroid.databinding.ActivityMainBinding;
import com.songjachin.wanandroid.homefragment.HomeFragment;
import com.songjachin.wanandroid.textfragment.TextFragment;

import java.lang.reflect.Field;

public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel > {
    private Fragment mHomeFragment;
    private TextFragment textFragment = new TextFragment();
    private TextFragment textFragment2 = new TextFragment();
    private TextFragment textFragment3 = new TextFragment();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        CCResult result = CC.obtainBuilder("Article").setActionName("getWanArticleFragment").build().call();
        mHomeFragment = (Fragment)result.getDataMap().get("fragment");
        fromFragment = mHomeFragment;
        //Set Toolbar
        setSupportActionBar(viewDataBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.menu_home));

        /**
         * Disable shift method require for to prevent shifting icon.
         * When you select any icon then remain all icon shift
        * @param view
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            disableShiftMode(viewDataBinding.bottomView);
        }
        viewDataBinding.bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment toFragment = null;
                switch (item.getItemId()){
                    case R.id.menu_home:
                        toFragment = mHomeFragment;
                    break;
                    case R.id.menu_navigation:
                        toFragment = textFragment;
                        break;
                    case R.id.menu_question:
                        toFragment = textFragment2;
                        break;
                    case R.id.menu_mine:
                        toFragment = textFragment3;
                        break;
                }
                if(getSupportActionBar() != null){
                    getSupportActionBar().setTitle(item.getTitle());
                }
                switchFragment(fromFragment,toFragment);
                fromFragment = toFragment;
                return true;
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewDataBinding.container.getId(),mHomeFragment);
        transaction.commit();
    }

    Fragment fromFragment;
    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            FragmentManager manger = getSupportFragmentManager();
            FragmentTransaction transaction = manger.beginTransaction();
            if (!to.isAdded()) {
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.add(R.id.container, to, to.getClass().getName()).commit();
                }

            } else {
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.show(to).commit();
                }

            }
        }
    }
    @Override
    protected void onRetryBtnClick() {

    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
            //case blocks for other MemuItems(If any)
        }
        return true;
    }
}
