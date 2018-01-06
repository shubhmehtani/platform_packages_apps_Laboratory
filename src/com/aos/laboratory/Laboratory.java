/*
 * Copyright (C) 2017 Team Darkness Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aos.laboratory;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.aos.laboratory.fragments.Lockscreen;
import com.aos.laboratory.fragments.Miscellaneous;
import com.aos.laboratory.fragments.Navigation;
import com.aos.laboratory.fragments.Statusbar;
import com.aos.laboratory.fragments.Panels;
import com.aos.laboratory.navigation.*;

public class Laboratory extends SettingsPreferenceFragment {

    BottomNavigationViewCustom navigationBar;
    MenuItem menuitem;
    ViewPager prefsViewpager;
    String titleString[];


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_laboratory, container, false);

        prefsViewpager = root.findViewById(R.id.prefsViewpager);
        navigationBar = root.findViewById(R.id.navigationBar);

        //navigationBar.inflateMenu(R.xml.bottom_navigation_items);

        prefsAdapter prefsAdapter = new prefsAdapter(getFragmentManager());
        prefsViewpager.setAdapter(prefsAdapter);

        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationViewCustom.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.action_statusbar:
                       prefsViewpager.setCurrentItem(0, true);
                        break;
                    case R.id.action_navigation:
                        prefsViewpager.setCurrentItem(1, true);
                        break;
                    case R.id.action_lockscreen:
                        prefsViewpager.setCurrentItem(2, true);
                        break;
                    case R.id.action_panels:
                        prefsViewpager.setCurrentItem(3, true);
                        break;
                    case R.id.action_misc:
                       prefsViewpager.setCurrentItem(4, true);
                        break;
                }

                return true;
            }
        });

	prefsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuitem != null) {
                    menuitem.setChecked(false);
                } else {
                    navigationBar.getMenu().getItem(0).setChecked(false);
                }
                navigationBar.getMenu().getItem(position).setChecked(true);
                menuitem = navigationBar.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return root;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.LABORATORY;
    }

    class prefsAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public prefsAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new Statusbar();
            frags[1] = new Navigation();
            frags[2] = new Lockscreen();
            frags[3] = new Panels();
            frags[4] = new Miscellaneous();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[]{
                getString(R.string.nav_statusbar),
                getString(R.string.nav_navigation),
                getString(R.string.nav_lockscreen),
                getString(R.string.nav_panels),
                getString(R.string.nav_misc)};
        return titleString;
    }

}
