package com.aos.laboratory;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.aos.laboratory.fragments.LockscreenFragment;
import com.aos.laboratory.fragments.MiscellaneousFragment;
import com.aos.laboratory.fragments.NavigationFragment;
import com.aos.laboratory.fragments.StatusbarFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationBar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBar = findViewById(R.id.navigationBar);
        navigationBar.inflateMenu(R.xml.bottom_navigation_items);
        fragmentManager = getSupportFragmentManager();

        changeFragment(new StatusbarFragment());

        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.action_statusbar:
                        changeFragment(new StatusbarFragment());
                        break;
                    case R.id.action_lockscreen:
                        changeFragment(new LockscreenFragment());
                        break;
                    case R.id.action_navigation:
                        changeFragment(new NavigationFragment());
                        break;
                    case R.id.action_mics:
                        changeFragment(new MiscellaneousFragment());
                        break;
                }

                return true;
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout, fragment).commit();

    }
}
