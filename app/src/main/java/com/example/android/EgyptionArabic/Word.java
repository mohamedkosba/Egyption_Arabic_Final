package com.example.android.EgyptionArabic;
    // I have to create this custom class to can add two object in
    // the same element to my ArrayList
    // instead of one object in indexed element

public class Word {

    // this is my private state to store the information

    // this field for saving the English word
    private String EnglishTranslation;
    // this field for saving the Arabic word
    private String ArabicTranslation;
    // this field for saving the image resource ID
    // we must create an initial value and make sure not to match any ID number
    // so we here chose -1
    private int imageResourceID = NO_IMAGE_PROVIDE;
    // here we assign a number which will impossible to be generated as ID number
    private  static final int NO_IMAGE_PROVIDE = -1;

    //this field for storing Audio files resource ID
    private int itemAudioID;


                    // this constructor I use it instead the setter method
                    // because I will assign the data once
    // this for the phrases activity
    public Word(String englishTranslation, String arabicTranslation, int itemAudioID) {
        EnglishTranslation = englishTranslation;
        ArabicTranslation = arabicTranslation;
        this.itemAudioID = itemAudioID;
    }

    // We overloading this constructor because all the items have audio files
    public Word(String englishTranslation, String arabicTranslation, int imageResourceID, int itemAudioId) {
        EnglishTranslation = englishTranslation;
        ArabicTranslation = arabicTranslation;
        this.imageResourceID = imageResourceID;
        this.itemAudioID = itemAudioId;
    }


    //this is encapsulated getter method
    public String getEnglishTranslation() {
        return EnglishTranslation;
    }

    public String getArabicTranslation() {
        return ArabicTranslation;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    // this method check if their is an imageResourceID add or not
    //and the idea ---> here if the listItem doesn't have image so there is not change will be made
    // on the variable imageResourceID >>>but DO NOT forget to assign the imageResourceID with initial value<<<
    public boolean hasImage(){
        // return the imageResourceID if it not -1
        // NO_IMAGE_PROVIDE is just a CONSTANT number (-1)
        return imageResourceID != NO_IMAGE_PROVIDE; // if it return something then it TRUE
    }

    public int getItemAudioId() {
        return itemAudioID;
    }

}

