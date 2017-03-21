package ccwav.blogspot.com.learnvocabulary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Skill_Model;

public class VocabularyActivity extends AppCompatActivity {

    MyDatabase myDatabase;
    List<Categories_Model> categories_models = new ArrayList<>();
    List<Skill_Model> topic_models = new ArrayList<>();

   // @Override
    Button btnSoundSpeak, btnBookmark,btnShowContext;
    ImageView imageView;
    TextView txtWord,txtSpelling;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_layout_m);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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


}
