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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    // variable declaration
//    TextView btnViewFamily, btnViewPhrases, btnViewColors ,btnViewNumbers;
//    Intent intent;

    // this method will executed when you open the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // get reference to the viewPager view to make it as a container
        // that hold 1- the fragments to slide right or left to change
        //              them
        //           2- and also hold the tabLayout so when u click on
        //              it move you to the proper fragment
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        WordFragmentPagerAdapter adapter = new WordFragmentPagerAdapter(getSupportFragmentManager(), getBaseContext());

        // Set the adapter (which mange the display of the fragments) onto the view pager
        viewPager.setAdapter(adapter);


        // get reference to the TabLayout view to make it when u click on
        // it move you to the proper fragment
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // attach the tabLayout with the viewPager or in other word
        // sync them together
        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

    }


}