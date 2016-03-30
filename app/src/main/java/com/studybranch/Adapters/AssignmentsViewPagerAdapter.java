package com.studybranch.Adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.studybranch.NestedFragments.AllListFragment;
import com.studybranch.NestedFragments.ClassExpandableListFragment;
import com.studybranch.NestedFragments.DateExpandableListFragment;
import com.studybranch.NestedFragments.PriorityExpandableListFragment;

/**
 * Created by ArnoldB on 1/4/2015.
 */
public class AssignmentsViewPagerAdapter extends FragmentPagerAdapter {
    //FragmentManager fragmentManager;
    static final String[] tabTitles = {"All", "Priority", "Class", "Date"};

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public AssignmentsViewPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        //this.fragmentManager = fragmentManager;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return AllListFragment.newInstance();
            case 1: return PriorityExpandableListFragment.newInstance();
            case 2: return ClassExpandableListFragment.newInstance();
            case 3: return DateExpandableListFragment.newInstance();
            default: return AllListFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
