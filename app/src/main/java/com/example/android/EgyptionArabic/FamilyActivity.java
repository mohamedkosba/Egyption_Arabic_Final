package com.example.android.EgyptionArabic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //here is the magic happens which replacing the current XML file
        // (activity_category) with the appropriate Fragment (NumbersFragment)

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FamilyFragment())
                .commit();
    }
}

//
//<!-- Small explanation to Fragment transaction -->
//
//
//<!--
//        here:
//        1-we create An Activity HOLDS Fragment
//        a-this activity should have XML file to host the fragment on it
//        in our case here Activity_category
//        b- the fragment also have it's own XML file to define how it she want
//        to look like in our case here Word_List_Layout and Word_grid_Layout
//        -->
