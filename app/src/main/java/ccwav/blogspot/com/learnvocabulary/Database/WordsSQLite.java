package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

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
                words_model = new Words_Model(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getString(3),cs.getString(4), cs.getString(5), cs.getString(6));
                listwords.add(words_model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
//        System.out.println("id: "+listwords.get(1).getWordID()
//                +" Cate: "+listwords.get(1).getCategories_ID()
//                +" EN: "+listwords.get(1).getEnglish()
//                +" VN: "+listwords.get(1).getVietnamese()
//                +" SPe: "+listwords.get(1).getSpeech()
//                +" IM: "+listwords.get(1).getImage());
        return listwords;
    }

    public ArrayList<Words_Model> getAllWordsbyCategori(int id) {
        ArrayList<Words_Model> listwords = new ArrayList<Words_Model>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Word where Categori_ID = id ORDER BY ramdom()", null);
            Words_Model words_model;
            while (cs.moveToNext()) {
                words_model = new Words_Model(cs.getInt(0),cs.getInt(1),cs.getString(2), cs.getString(3),cs.getString(4), cs.getString(5), cs.getString(6));
                listwords.add(words_model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        Log.d("a","b" + getAllWordsbyCategori(3));


              ;
        return listwords;
    }
}
