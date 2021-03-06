package ccwav.blogspot.com.learnvocabulary.Model;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;

/**
 * Created by John on 3/20/2017.
 */

public class Words_Model {
    private WordsSQLite wordsSQLite;
    private int wordID;
    private int Categories_ID;
    private String English;
    private String Vietnamese;
    private String Speech;
    private String Image;
    private String Context;
    private int Bookmark;


    public int getBookmark() {
        return Bookmark;
    }

    public void setBookmark(int bookmark) {
        Bookmark = bookmark;
    }


    public Words_Model(Words_Model m1){
        this.wordID = m1.wordID;
        this.Categories_ID = m1.Categories_ID;
        this.English = m1.English;
        this.Vietnamese = m1.Vietnamese;
        this.Speech = m1.Speech;
        this.Image = m1.Image;
        this.Context = m1.Context;
    }

    public Words_Model(int wordID, int categories_ID, String english, String vietnamese, String speech, String image, String context, int bookmark) {
        this.wordID = wordID;
        Categories_ID = categories_ID;
        English = english;
        Vietnamese = vietnamese;
        Speech = speech;
        Image = image;
        Context = context;
        Bookmark = bookmark;
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public int getCategories_ID() {
        return Categories_ID;
    }

    public void setCategories_ID(int categories_ID) {
        Categories_ID = categories_ID;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getVietnamese() {
        return Vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        Vietnamese = vietnamese;
    }

    public String getSpeech() {
        return Speech;
    }

    public void setSpeech(String speech) {
        Speech = speech;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }




}
