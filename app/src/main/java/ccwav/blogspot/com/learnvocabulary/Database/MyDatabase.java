package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by John on 3/18/2017.
 */


public class MyDatabase extends SQLiteOpenHelper{
    public String DB_PATH = "//data//data//%s//databases//";
    // đường dẫn nơi chứa database
    private static String DB_NAME = "dbVocabulary.db";
    public SQLiteDatabase database;
    private final Context mContext;

    public static final String TABLE_NAME = "favorite";
    public static final String COLUMN_ENGLISH="english";
    public static final String COLUMN_VIETNAMESE="vietnamese";
    public static final String COLUMN_IMAGE = "image";
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " ( " + BaseColumns._ID + " integer primary key autoincrement, " + COLUMN_ENGLISH + " text not null,"+ COLUMN_VIETNAMESE +" text not null," + COLUMN_IMAGE + " text not null);";


    public MyDatabase(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = String.format(DB_PATH, context.getPackageName());
        this.mContext = context;
    }

    /**
     * copy database from assets to the device if not existed
     *
     * @return true if not exist and create database success
     * @throws IOException
     */
    public boolean isCreatedDatabase() throws IOException {
        boolean result = true;
        if (!checkExistDataBase()) {
            this.getReadableDatabase();
            try {
                copyDataBase();
                result = false;
            } catch (Exception ex) {
                ex.toString();
            }
        }

        return result;
    }

    /**
     * check whether database exist on the device?
     *
     * @return true if existed
     */
    private boolean checkExistDataBase() {

        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * copy database from assets folder to the device
     *
     * @throws IOException
     */
    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * delete database file
     *
     * @return
     */
    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * open database
     *
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // do nothing
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
        Log.w(MyDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int deleteData_From_Table(String tbName) {

        int result = 0;
        try {
            openDataBase();
            database.beginTransaction();
            result = database.delete(tbName, null, null);
            if (result >= 0) {
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            database.endTransaction();
            close();
        } finally {
            database.endTransaction();
            close();
        }

        return result;
    }

}
