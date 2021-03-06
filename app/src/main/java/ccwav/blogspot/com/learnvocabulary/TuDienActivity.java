package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

import ccwav.blogspot.com.learnvocabulary.Adapter.TudienAdapter;
import ccwav.blogspot.com.learnvocabulary.Model.Tudien;

public class TuDienActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener{
    String DATABASE_NAME="anhviet.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;


    public SQLiteDatabase getDatabase()
    {
        return this.database;
    }

    ListView lvtudien;
    EditText editSearch;
    ArrayList<Tudien> dstudien;
    TudienAdapter tudienAdapter;
    Tudien tudien= new Tudien();


    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_dien);
        processCopy();
        addControl();
        addEvent();
        //showListtudien();
    }

    private void addEvent() {
        lvtudien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              xuLyChiTietTu(position);
            }
        });
    }
    private void xuLyChiTietTu(int position) {
        Intent intent = new Intent(TuDienActivity.this, Nghi_Tu_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("TU", tudienAdapter.getItem(position).getWord());
        bundle.putSerializable("YNGHIA", tudienAdapter.getItem(position).getMean());
        intent.putExtra("TTBH", bundle);
        startActivity(intent);
    }

    private void xuLyTim(String newText) {
        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor=database.query(
                "data",
                null,
                "word like ?",
                new String[]{newText+"%"},
                null,
                null,
                null,
                newText.trim().length() > 0 ? "" : "");
        //cursor.moveToNext();
        dstudien.clear();
        while (cursor.moveToNext())
        {
            int _id=cursor.getInt(0);
            String word=cursor.getString(1);
            String mean=cursor.getString(2);
            String History=cursor.getString(3);


            Tudien tudien=new Tudien();
            tudien.set_id(_id);
            tudien.setWord(word);
            tudien.setMean(mean);
            tudien.setHistory(History);

            dstudien.add(tudien);
        }
        cursor.close();
        tudienAdapter.notifyDataSetChanged();

    }
    private void addControl() {
        lvtudien= (ListView) findViewById(R.id.LvTudien);
        editSearch= (EditText) findViewById(R.id.editsearch);
        dstudien= new ArrayList<>();
        tudienAdapter= new TudienAdapter(
                TuDienActivity.this,
                R.layout.item_tudien_activity,
                dstudien
        );
        lvtudien.setAdapter(tudienAdapter);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                xuLyTim(editSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

//    private void showListtudien() {
//        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
//        Cursor cursor=database.query("data",null,null,null,null,null,null);
//        cursor.moveToNext();
//        dstudien.clear();
//        while (cursor.moveToNext())
//        {
//            int _id=cursor.getInt(0);
//            String word=cursor.getString(1);
//            String mean=cursor.getString(2);
//            String History=cursor.getString(3);
//
//
//            Tudien tudien=new Tudien();
//            tudien.set_id(_id);
//            tudien.setWord(word);
//            tudien.setMean(mean);
//            tudien.setHistory(History);
//
//            dstudien.add(tudien);
//        }
//        cursor.close();
//        tudienAdapter.notifyDataSetChanged();
//    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();

            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }


    }
    private void CopyDataBaseFromAsset() {

        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR)
        {
            tts.setLanguage(Locale.UK);
        }
    }
}
