package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.Model.QuestionModel;

/**
 * Created by quang on 04/19/2017.
 */

public class WritetestSQLite extends MyDatabase {
    public WritetestSQLite(Context con) {
        super(con);
    }

    public ArrayList<QuestionModel> getAllQuestion() {
        ArrayList<QuestionModel> listquestion = new ArrayList<QuestionModel>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Question", null);
            QuestionModel cate;
            while (cs.moveToNext()) {
                cate = new QuestionModel(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getInt(3));
                listquestion.add(cate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listquestion;
    }

}