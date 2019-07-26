package com.example.study;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.study.fragment.AndroidFragment;
import com.example.study.fragment.JavaFragment;
import com.example.study.fragment.OtherFragment;
import com.example.study.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private NoScrollViewPager vp;
    private List<Fragment> fragmentList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    vp.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    vp.setCurrentItem(2);
                    return true;
                default:
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        vp = findViewById(R.id.vp);
        vp.setNoScroll(true);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentList = new ArrayList<>();
        fragmentList.add(AndroidFragment.newInstance());
        fragmentList.add(JavaFragment.newInstance());
        fragmentList.add(OtherFragment.newInstance());
        vp.setAdapter(new MyVPAdapter(getSupportFragmentManager()));
    }


    public class MyVPAdapter extends FragmentPagerAdapter {
        public MyVPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
