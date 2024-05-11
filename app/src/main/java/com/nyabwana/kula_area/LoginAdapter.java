package com.nyabwana.kula_area;

import androidx.fragment.app.Fragment;
import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalItems;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context =  context;
        this.totalItems = totalTabs;
    }

    @Override
    public int getCount() {
        return totalItems   ;
    }

    public Fragment getItem(int position) {
        switch(position) {
            case 0 :
                return new LoginTabFragment();
            case 1 :
                return new SignUpTabFragment();
            default:
                return null;
        }
    }
}
