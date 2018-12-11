/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.EgyptionArabic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 *
 * here we inherit from FragmentPagerAdapter to keep our fragment in memory
 * to swipe between them fast
 */
public class WordFragmentPagerAdapter extends FragmentPagerAdapter {





    //Context object in order to turn the string resource ID into an actual String.
    private Context mContext;


    // u accept the context object here in the constructor because we will get the
    // resource ID to the title by using this context
    public WordFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext= context;
    }

    /*
    * we need to Override getCount because it's refer to the number of fragment I have
    * */

    @Override
    public int getCount() {
        return 4;
    }

    /*
    * we override and implement this method because it's return a fragment associate
    * with each position
    *
    * we use if statement to selecting the right fragment for each position
    * */
@Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new FamilyFragment();
        }else if (position == 2){
            return new ColorFragment();
        }else{
            return new PhrasesFragment();
        }
    }


    // this is returning a string to describe each page
    //
    //why I would need this?
    // :- it will be helpful if I want to add tabs associated with a viewpager
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1){
            return mContext.getString(R.string.category_family);
        }else if (position == 2){
            return mContext.getString(R.string.category_colors);
        }else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}
