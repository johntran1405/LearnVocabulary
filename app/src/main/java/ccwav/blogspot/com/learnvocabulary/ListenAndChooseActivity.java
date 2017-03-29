package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

import static ccwav.blogspot.com.learnvocabulary.VocabularyActivity.listword;

public class ListenAndChooseActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

   // @Override
    ImageButton imageBTN1,imageBTN2,imageBTN3,imageBTN4;
    TextView txtNewWord;
    Button btnSpeak, btnNext;

    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate,ID_Word,i = 0,sizearr = 0;
    List<Words_Model> listword= new ArrayList<>();
    TextToSpeech finalMTts = null;
    String image;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listenchoose_layout_m);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");

        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);
        finalMTts= new TextToSpeech(this.getApplicationContext(),this);
        addControl();
        addEvent();

    }

    private void addControl() {
        imageBTN1 = (ImageButton) findViewById(R.id.imageBTN1);
        imageBTN2 = (ImageButton) findViewById(R.id.imageBTN2);
        imageBTN3 = (ImageButton) findViewById(R.id.imageBTN3);
        imageBTN4 = (ImageButton) findViewById(R.id.imageBTN4);
        txtNewWord = (TextView) findViewById(R.id.txtNewWord);
        btnSpeak = (Button) findViewById(R.id.btnSoundSpeak);
        btnNext = (Button) findViewById(R.id.btnNext);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addEvent() {
        txtNewWord.setText(listword.get(0).getEnglish().toString());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show();
                i++;
                getword(i);
                Log.d("aaaa","id: "+sizearr);
                btnNext.setVisibility(View.INVISIBLE);

            }
        });
    }
    public void checkAnswer(){
        int idWord = 0, idImage;
        // bắt sự kiện kiểm tra id của hình và id của chữ trùng nhau
        if(idWord == 0){
            btnNext.setVisibility(View.VISIBLE);
        }
        else{

        }
    }
    public String getword(int f){
        String text = listword.get(f).getEnglish();
        txtNewWord.setText(text);
        return  txtNewWord.toString();
    }
    @Override
    public void onInit(int i) {
        if(i != TextToSpeech.ERROR){
            finalMTts.setLanguage(Locale.US);
        }
    }
    @Override
    protected void onDestroy() {
        if (finalMTts != null)
        {
            finalMTts.stop();
            finalMTts.shutdown();
        }
        super.onDestroy();
    }
//    public Drawable getImage(int img)
//    {
//        int imgResId = getResources().getIdentifier(String.valueOf(img), "drawable",getApplicationContext().getPackageName());
//        Log.d("Hinh","TenHinh"+ imgResId);
//        Drawable dr;
//        if(imgResId!=0) {
//            dr = getApplicationContext().getResources().getDrawable(imgResId);
//        }else
//        {
//            dr = getApplicationContext().getResources().getDrawable(R.drawable.home);
//        }
//        return dr;
//    }
}
