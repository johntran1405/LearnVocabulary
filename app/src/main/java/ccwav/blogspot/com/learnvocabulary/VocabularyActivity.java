package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ccwav.blogspot.com.learnvocabulary.Adapter.CustomVocabularyAdapter;
import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class VocabularyActivity extends AppCompatActivity implements View.OnClickListener{

    MyDatabase myDatabase;
    List<Categories_Model> categories_models = new ArrayList<>();
    List<Words_Model> topic_models = new ArrayList<>();

    // @Override
    Button btnSoundSpeak, btnBookmark,btnShowContext;
    ImageView imageView;
    TextView txtWord,txtSpelling;

    ViewPager viewPager;
    CustomVocabularyAdapter vocabularyAdapter;
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_layout_m);
        // getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");
        Log.d("IDCATE","id: "+idcate);
        List<Words_Model> listword= new ArrayList<>();
        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);
        addControl();
        addEvent();




    }
    private void addControl() {
        btnSoundSpeak = (Button) findViewById(R.id.btn_soundSpeak);
        btnBookmark = (Button) findViewById(R.id.btn_bookmark);
        btnShowContext = (Button) findViewById(R.id.btn_showContext);

        imageView = (ImageView) findViewById(R.id.imageView);

        txtWord = (TextView) findViewById(R.id.txtWord);
        txtSpelling = (TextView) findViewById(R.id.txtSpell);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        vocabularyAdapter = new CustomVocabularyAdapter(this);
        viewPager.setAdapter(vocabularyAdapter);


    }
    private void addEvent() {

        btnSoundSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnShowContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}
