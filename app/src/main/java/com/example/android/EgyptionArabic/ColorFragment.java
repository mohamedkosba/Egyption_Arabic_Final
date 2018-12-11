package com.example.android.EgyptionArabic;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;


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
public class ColorFragment extends Fragment {

    //Start of global variables
//---------------------------------------------------------------------------------------------------
    // declare the MediaPlayer
    private MediaPlayer mMediaPlayer;

    // declare the AudioManger to get instance of it and restore it here
    private AudioManager mAudioManager;

    // this variable for saving OnCompletionListener CLASS which we use to release the audio
    // after it's done
    // we decide to assign it inside a variable because we don't want to create it every time
    // the audio done finishes. By putting it in a variable we now only create it once
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    // here we implement for the Audio Focus Change Listener instance
    // we need this as an input every time we request AudioFocus
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {



        /*
         * here in the instance of the listener we override the on audioFocusChange method, the one take
         * a single parameter (which is new focus state)
         *
         * inside this method we put our instructions when the system call this method
         * because the audio focus has to change inside our app
         * */
        @Override
        public void onAudioFocusChange(int focusChange) {

            switch (focusChange) {

                // so if the audio focus state is one of the following pause the song and seek
                // to the beginning
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;

                //if you get the focus start the music from the place you stopped
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;

                //if you lose the focus release the music
                case AudioManager.AUDIOFOCUS_LOSS:

                    releaseMediaPlayer();
                    break;
            }


//            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
//                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//
//                mMediaPlayer.pause();
//                mMediaPlayer.seekTo(0);
//            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                mMediaPlayer.start();
//            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                releaseMediaPlayer();
//            }
        }
    };

    //End of global variables
//---------------------------------------------------------------------------------------------------


    public ColorFragment() {
        // Required empty public constructor
    }


    /**
     *  this callback method is part from the fragment lifecycle and it's similar to the Activity
     *  onCreate callback Method
     *
     *  it should have a return state at the end of it
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.word_list_view, container, false);

        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */

        /*
         * NOW I am initializing the audio manger and getting instance of it.
         * and the reason we do that to requestAudioFocus on it
         *
         * SystemService .. mean It give you common functionality or let u
         * control a hardware component on the device.
         *
         * AudioManager .. give u access the the volume and ringer mode control.
         * */

        // because the fragment does not have access to the system service
        // so we have to get instance of an Activity Object by calling (getActivity)
        // this is the activity enclose the current fragment
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

// Create a list of words
        // we declare it Final so we can reference it within the onItemClick method
        // (or in other word to access it from a method inside inner class [because the variable scope])
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Dark Olive Green", "اخضر داكن", R.drawable.dark_olive_green, R.raw.dark_olive_green));
        words.add(new Word("Green", "اخضر", R.drawable.green, R.raw.color_green));
        words.add(new Word("Dusty Yellow", "اصفر باهت(بيچ)", R.drawable.dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("Mustard Yellow", "اصفر فاتج", R.drawable.mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("Orange", "برتقاني", R.drawable.orange, R.raw.color_orange));
        words.add(new Word("Pumpkin", "برتقاني قرع عسل (يقطين)", R.drawable.pumpkin, R.raw.color_pumpkin));
        words.add(new Word("Dark Brown", "بني داكن", R.drawable.dark_brown, R.raw.dark_brown));
        words.add(new Word("Brown", "بني", R.drawable.brown, R.raw.color_brown));
        words.add(new Word("Turquoise", "تركواز", R.drawable.turquoise, R.raw.color_turquoise));
        words.add(new Word("Red", "احمر", R.drawable.red, R.raw.color_red));
        words.add(new Word("Blue", "ازرق", R.drawable.blue, R.raw.color_blue));
        words.add(new Word("Azure", "ازرق سماوي", R.drawable.azure, R.raw.color_azure));
        words.add(new Word("Violet", "بنفسجي", R.drawable.violet, R.raw.color_violet));
        words.add(new Word("Dark Violet", "بنفسجي غامق", R.drawable.dark_violet, R.raw.dark_violet));
        words.add(new Word("Purple", "موف غامق (ارجواني)", R.drawable.purple, R.raw.color_purple));
        words.add(new Word("pink", "بمبي (وردي)", R.drawable.pink, R.raw.color_pink));
        words.add(new Word("Silver", "فضي", R.drawable.silver, R.raw.silver));
        words.add(new Word("Gray", "رمادي", R.drawable.gray, R.raw.color_gray));
        words.add(new Word("White", "ابيض", R.drawable.white, R.raw.color_white));
        words.add(new Word("Black", "اسود", R.drawable.black, R.raw.color_black));





        // we create this custom array adapter because the default work with the single (View) in one listItem
        //this adapter control how the list item should look like
        //for each item in the list
        //this input words is list which is the data source

        // we have to pass a valid context to the constructor and it can not be
        // a fragment so we pass an activity that enclose this fragment as the context
        WordAdapter adapter =new WordAdapter(getActivity(), words, R.color.category_colors);

//---------------------------------------------------------------------------------------------------
//the implementation of the ListView and the ArrayAdapter is here

        // getting reference to the whole container on the screen, to set the adapter to it
        //.
        //the fragment does not have the method findViewById but the activity have it
        //so we calling the rootView object which contains children views such as listView
        ListView listView = (ListView) rootView.findViewById(R.id.list);


        // setting the adapter to the list view
        listView.setAdapter(adapter);



        // we creating event handler to the list items if one of it clicked on
        // AdapterView reference to the whole ListView
        // view is the list itemView that we clicked on
        // Position is the position (like first or second object in the arrayList
        // and this is different from the order of the rows on the screen) of each item in the Adapter data source
        // once we get this word object by position we can retrieve the AudioResourceID
        // refer to the row (on the screen) ID in the Item we Clicked on, this can be specified inside the adapter

        //we defining he ClickListener INLINE as anonymous class,
        //hint --> it's method can only reference local variables
        //         if they are declared Final
        //         or Global Variable "In the class itself"
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // this is a variable store the current object to can get the proper audioResourceID from it

                //  WE WILL GET OUT OBJECT FROM THE ArrayList (words)

                //words is the ArrayList of words and get is a method used to get the ArrayList Element
                // as the Position refer to the current ListItem number in the adapter data source
                // we use it as index to the ArrayList
                Word word =  words.get(position);

                // we now releasing the MediaPlayer Resources (Free up the Memory) Before it's initialized to
                // have an audio file because we about to play different song
                releaseMediaPlayer();




                // here is the magic happens we Requesting audio focus for playback
                // we call this method directly after initializing the AudioManager
                //
                //
                // the result will be on of two things
                // 1- AUDIOFOCUS_REQUEST_GRANTED
                // 2- AUDIOFOCUS_REQUEST_FAILED
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request temporary focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);



                // here we check if you get the "Audio Focus"
                // execute the this code which is playing the sound
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback but now you have the ability to request Audio Focus

                    // create the MediaPlayer object using factory method
                    //.
                    //we need to pass a valid context and then the Fragment is not valid
                    //context we use activity enclose the fragment
                    mMediaPlayer= MediaPlayer.create(getActivity() , word.getItemAudioId());
                    mMediaPlayer.start();// we don't need prepare state; create does't that for us

                    //we setup a listener to the MediaPlayer to stop and release the MediaPlayer Resources
                    // (Free up the Memory)
                    // after the the audio finish playing
                    mMediaPlayer.setOnCompletionListener(onCompletionListener);
                }




            }
        });


        return rootView;
    }

    /**
     *
     * we override this method because if the user want to leave the activity in the middle
     * of the audio it will release the song
     *
     */

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * this called HELPER methods because it's perform a task that is meant to help
     * another part of our code
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //if u use audio focus you have to abandon it when you no longer need it
            // by putting this line of code here we abandon the audio focus in both cases
            //
            //1- when no longer opening the activity
            //2- when you loss the focus permanently
            //
            // (and this will done by calling this helper method in ONSTOP method in the activity life Cycle
            // and in the onFocusChangeListener interface ).
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
