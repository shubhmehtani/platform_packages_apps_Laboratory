/*Copyright (C) 2017 DarkNess reDefined

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
*/

package com.atomicos.laboratory.fragments;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.content.ContentResolver;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.ListPreference;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.internal.logging.MetricsProto.MetricsEvent;

public class StatusbarGeneral extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String STATUS_BAR_SHOW_TICKER = "status_bar_show_ticker";

    private SwitchPreference mShowTicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.statusbar_general);
        ContentResolver resolver = getActivity().getContentResolver();

	mShowTicker = (SwitchPreference) findPreference(STATUS_BAR_SHOW_TICKER);
 	mShowTicker.setOnPreferenceChangeListener(this);
	int ShowTicker = Settings.System.getInt(getContentResolver(),
		STATUS_BAR_SHOW_TICKER, 0);
	mShowTicker.setChecked(ShowTicker != 0);

    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.LABORATORY;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
	if (preference == mShowTicker) {
	boolean value = (Boolean) objValue;
	Settings.Global.putInt(getContentResolver(), STATUS_BAR_SHOW_TICKER,
	value ? 1 : 0);
        }
        return true;
    }
}
