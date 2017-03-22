package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;

/**
 * Created by John on 3/18/2017.
 */

public class MyDatabase extends SQLiteOpenHelper{
    private static String DB_NAME ="dbVocabulary.db";
    private static String DB_PATH_SUFFIX = "";
    private static String DB_TABLE="Categories";
    private static String DB_TABLE2="Words";
    private SQLiteDatabase database=null;
    private Context mContext = null;


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH_SUFFIX = context.getApplicationInfo().dataDir + "/databases/";
        this.mContext = context;
    }



    public List<Categories_Model> getCategory()
    {
        List<Categories_Model> listCategory = new ArrayList<Categories_Model>();
        String query= "SELECT * FROM Categories";
        SQLiteDatabase db= this.getReadableDatabase();
        try {
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do {
                    int categori_Id = cursor.getInt(0);
                    String categori_Name = cursor.getString(1);
                    String categori_Icon = cursor.getString(2);
                    Categories_Model category = new Categories_Model(categori_Id,categori_Name,categori_Icon);
                    listCategory.add(category);
                }while (cursor.moveToNext());
                cursor.close();
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


        return listCategory;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
