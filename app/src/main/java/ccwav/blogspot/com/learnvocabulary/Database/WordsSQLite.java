package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

/**
 * Created by quang on 03/23/2017.
 */

public class WordsSQLite extends MyDatabase {
    public WordsSQLite(Context con) {
        super(con);
    }
    public ArrayList<Words_Model> getAllWords() {
        ArrayList<Words_Model> listwords = new ArrayList<Words_Model>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Word", null);
            Words_Model words_model;
            while (cs.moveToNext()) {
                words_model = new Words_Model(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getString(3),cs.getString(4), cs.getString(5), cs.getString(6),cs.getInt(7));
                listwords.add(words_model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listwords;
    }



    public ArrayList<Words_Model> getAllWordsbyCategori(int id) {
        ArrayList<Words_Model> listwords = new ArrayList<Words_Model>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Word where Categori_ID =" + id + "  ORDER BY RANDOM()", null);
            Words_Model words_model;
            while (cs.moveToNext()) {
                words_model = new Words_Model(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getString(3),cs.getString(4), cs.getString(5), cs.getString(6),cs.getInt(7));
                listwords.add(words_model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }


        return listwords;
    }
    public ArrayList<Words_Model> getWordsbyCategori(int id) {
        ArrayList<Words_Model> listwords = new ArrayList<Words_Model>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Word where Categori_ID =" + id, null);
            Words_Model words_model;
            while (cs.moveToNext()) {
                words_model = new Words_Model(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getString(3),cs.getString(4), cs.getString(5), cs.getString(6),cs.getInt(7));
                listwords.add(words_model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }


        return listwords;
    }
    public ArrayList<Words_Model> getWordsbyCategoriFavorite() {
        ArrayList<Words_Model> listwords = new ArrayList<Words_Model>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Word where Bookmark =" + 1, null);
            Words_Model words_model;
            while (cs.moveToNext()) {
                words_model = new Words_Model(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getString(3),cs.getString(4), cs.getString(5), cs.getString(6),cs.getInt(7));
                listwords.add(words_model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }


        return listwords;
    }
    public void updatebBookmark(int rowId,int giatri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Bookmark",giatri);
        db.update("Word",values,"Word_ID="+rowId,null);
        db.close();
    }
}
