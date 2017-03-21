package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ccwav.blogspot.com.learnvocabulary.Common.Common;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Skill_Model;

/**
 * Created by John on 3/18/2017.
 */

public class MyDatabase extends SQLiteOpenHelper{
    private static String DB_NAME ="MyDB.db";
    private static String DB_PATH ="";
    private SQLiteDatabase mDatabase;
    private Context mContext = null;


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir+"/database";
        File file = new File(DB_PATH+"MyDatabase");
        if(file.exists()){
            openDatabase();
        }
        mContext=context;


    }
    // This function open Database
    private void openDatabase() {
        String path = DB_PATH + DB_NAME;
        mDatabase = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
    }
    //This function is check database null or not
    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);

        }
        catch (SQLiteException e){
            e.printStackTrace();
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    //This function will be get all topic from Categories Model
    public List<Categories_Model> getCategories(String mode){
        List<Categories_Model> listCategories = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        int choose = 0;
        if(mode.equals(Common.Categories.HOME.toString())){

        }
        else if(mode.equals(Common.Categories.ANIMAL.toString()))   {

        }
        else if(mode.equals(Common.Categories.TRAVEL.toString()))   {

        }
        else if(mode.equals(Common.Categories.FOOD.toString()))   {

        }
        else if(mode.equals(Common.Categories.PEOPLE.toString())){

        }
        else if(mode.equals(Common.Categories.VEGETABLE.toString())){

        }
        else if(mode.equals(Common.Categories.SPORT.toString())){

        }
        else if(mode.equals(Common.Categories.FRUIT.toString())){

        }



        try{
            cursor = db.rawQuery(String.format("SELECT * FROM Categories_Model"),null);
            if(cursor == null){
                return null;
            }
            do{
                int Categories_ID = cursor.getInt(cursor.getColumnIndex("Categories_ID"));
                String Home = cursor.getString(cursor.getColumnIndex("Home"));
                String People = cursor.getString(cursor.getColumnIndex("People"));
                String Animal = cursor.getString(cursor.getColumnIndex("Animal"));
                String Fruit = cursor.getString(cursor.getColumnIndex("Fruit"));
                String Travel = cursor.getString(cursor.getColumnIndex("Travel"));
                String Sport = cursor.getString(cursor.getColumnIndex("Sport"));
                String Vegetable = cursor.getString(cursor.getColumnIndex("Vegetable"));
                String Food = cursor.getString(cursor.getColumnIndex("Food"));

                Categories_Model categories_model = new Categories_Model(Categories_ID,Home,People,Animal,Fruit,Travel,Sport,Vegetable,Food);
                listCategories.add(categories_model);
            }while (cursor.moveToNext());
            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listCategories;
    }

    //This function get each context of topic with key from categories
    public List<Skill_Model> getTopic(String mode){
        List<Skill_Model> topic_models = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        int choose = 0;
        if(mode.equals(Common.Skill.NEW_WORD.toString())){

        }
        else if(mode.equals(Common.Skill.SPEECH.toString()))   {

        }
        else if(mode.equals(Common.Skill.LISTEN_CHOOSE.toString()))   {

        }
        else if(mode.equals(Common.Skill.LISTEN_WRITE.toString()))   {

        }
        else if(mode.equals(Common.Skill.CHOOSE_WORD.toString())){

        }

        try{
            cursor = db.rawQuery(String.format("SELECT * FROM Topic_Model"),null);
            if( cursor == null)
            {
                return null;
            }
            do{
                int wordID = cursor.getInt(cursor.getColumnIndex("wordID"));
                int Categories_ID = cursor.getInt(cursor.getColumnIndex("Categories_ID"));
                String English = cursor.getString(cursor.getColumnIndex("English"));
                String Vietnamese = cursor.getString(cursor.getColumnIndex("Vietnamese"));
                String Speech = cursor.getString(cursor.getColumnIndex("Speech"));
                String Image = cursor.getString(cursor.getColumnIndex("Image"));
                String Context = cursor.getString(cursor.getColumnIndex("Context"));

                Skill_Model topic = new Skill_Model(wordID,Categories_ID,English,Vietnamese,Speech,Image,Context);
                topic_models.add(topic);
            }while(cursor.moveToNext());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return topic_models;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
