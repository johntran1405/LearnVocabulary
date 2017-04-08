package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class ChooseActivity extends AppCompatActivity {
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;


    static int NUM_ITEMS =0;
    ImageView imageView;
    Button btnA,btnB,btnC,btnD;

    ArrayList<Words_Model> listword= new ArrayList<>();
    ArrayList<Words_Model> arrayList = new ArrayList<>();
    TextToSpeech finalMTts = null;
    private int RESULT_CHOSEN = -1,RESULT_FAILED = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout_m);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");
        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);

        addControl();
        addEvent();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addEvent() {
        RandomImage();
    }

    private void addControl() {
        imageView= (ImageView) findViewById(R.id.imagecate);
        btnA= (Button) findViewById(R.id.dapan1);
        btnB= (Button) findViewById(R.id.dapan2);
        btnC= (Button) findViewById(R.id.dapan3);
        btnD= (Button) findViewById(R.id.dapan4);

    }
    public void RandomImage(){
        Random random = new Random();

        for(int i = 0; i< 4;i++){
            int n = listword.size();
            int x = random.nextInt(n);
            arrayList.add(listword.get(x));
            listword.remove(x);
        }
        RESULT_CHOSEN = random.nextInt(arrayList.size());
        imageView.setBackgroundResource(getResources().getIdentifier(arrayList.get(RESULT_CHOSEN).getImage(),"drawable",getApplicationContext().getPackageName()));
        btnA.setText(arrayList.get(0).getEnglish().toString());
        btnB.setText(arrayList.get(1).getEnglish().toString());
        btnC.setText(arrayList.get(2).getEnglish().toString());
        btnD.setText(arrayList.get(3).getEnglish().toString());

    }
    //Check Result


}
