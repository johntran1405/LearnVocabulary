package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;

/**
 * Created by quang on 03/22/2017.
 */

public class CategorySQLite extends MyDatabase {
    public CategorySQLite(Context con) {
        super(con);
    }

    public ArrayList<Categories_Model> getAllCategory() {
        ArrayList<Categories_Model> listcate = new ArrayList<Categories_Model>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Categories", null);
            Categories_Model cate;
            while (cs.moveToNext()) {
                cate = new Categories_Model(cs.getInt(0), cs.getString(1), cs.getString(2));
                listcate.add(cate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listcate;
    }


}
