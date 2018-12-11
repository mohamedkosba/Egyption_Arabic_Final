package com.example.android.EgyptionArabic;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

    // this inheritance also mean we expect that the data source will be list of word
public class WordAdapter extends ArrayAdapter<Word> {


    // we save the color come from specific activity via constructor to assign it later
    private int mActivityColorResource;



    // @param context this context for inflating the layout or in other word
    // where to start tendering

    //@pram wordObjects this is the list of objects which we will organize
    // and pass it to the list view element by element
    public WordAdapter(@NonNull Context context, @NonNull ArrayList<Word> wordObjects, int ActivityColorResource) {

        //=================ArrayAdapter Hint==========================
        // here we call the superclass (ArrayAdapter) constructor , and also it's have 3
        // input passes to it's constructor
        // first :-----is the context refer where the array will work,
        // second:----- is the shape of the single listItem,
        // third :-----is the]list of word itself which i want to add on the screen
        //    (her role to hold the data and show it on the list view )
        // ================Check the documentation=====================
        super(context, 0, wordObjects);
        // we assign the RESOuRCE to be 0 because we will rely on the GetView Method
        // to inflate the listItem layout for us as we need it to be

        mActivityColorResource = ActivityColorResource;

    }


    // here how the ListView talk to the ArrayList, the ArrayList ask for the current position
    // and then give the list the appropriate element

    //@pram Position:- refer to the position of the list item view
    //@pram convertView:- The recycled view to populate (other meaning we use it to know if there is a view
    // on the screen or not, to start from the beginning or continue).
    //@param parent The parent ViewGroup that is used as a container for inflation
        // or in other word the listItem will add to it a children

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // declare the WordObject to (start work on it) or (reuse old one was here before)
        // this ensure that we have a list itemView Even without data
        View WordObject = convertView;

        // Check if the existing view is being reused, otherwise start inflate the view
        // the view will be null when you  first open up the activity
        if(WordObject == null) {
            // LayoutInflater is turns the xml layout file into actual view object
            // this line responsible for inflating the <<<<ListView>>>
            // FALSE mean we won't attach the listItem to the parent just yet
            WordObject = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the location to top or buttom object acording to the scrolling
        // is upward or downward (or any giving position it may be in the middle)
        Word currentWordObject = getItem(position);
//----------------------------------------------------------------------------------------------------
        //add here your modification into specific view For ONE single listItem(wordObject)
//----------------------------------------------------------------------------------------------------
        // Find the TextView in the list_item.xml layout with the ID English_text_view
        TextView englishTextView = (TextView) WordObject.findViewById(R.id.English_text_view);

        // now set the text to the currentWordObject which you will get it
        // from the custom word class you have created
        englishTextView.setText(currentWordObject.getEnglishTranslation());


        // Find the TextView in the list_item.xml layout with the ID Arabic_text_view
        TextView arabicTextView = (TextView) WordObject.findViewById(R.id.Arabic_text_view);

        // now set the text to the currentWordObject which you will get it
        // from the custom word class you have created
        arabicTextView.setText(currentWordObject.getArabicTranslation());

        // find the image in the list_item.xml withe the id imageReference
        ImageView imageResourceID = (ImageView) WordObject.findViewById(R.id.imageReference);


        //here we check if the view containing image or not
        //and we getting help from the method hasImage which we was created inside our custom class
        //before
        if (currentWordObject.hasImage()) {

            // now we setting the appropriate image tho the image by using it's ID
            imageResourceID.setImageResource(currentWordObject.getImageResourceID());

            // actually this will be useful when we reuse a listItem contain image
            // after using one doesn't have (here u modifying the view itself)
            imageResourceID.setVisibility(View.VISIBLE);

        } else {

            //make the ImageView GONE (here u modifying the view itself)
            imageResourceID.setVisibility(View.GONE);

        }

        //              Activity Background Color Change                     //
        //getting reference to the view we want to change it's color
        LinearLayout containerView = (LinearLayout)WordObject.findViewById(R.id.text_Container);

        // find the color that match to specific activity
        int color = ContextCompat.getColor(getContext(), mActivityColorResource);

        //now we setting the background color for the linearLayout which holds the text
        containerView.setBackgroundColor(color);



//----------------------------------------------------------------------------------------------------
        // Return the whole single listItem (containing 2 TextViews) with the updated information
        return WordObject;

    }
}
