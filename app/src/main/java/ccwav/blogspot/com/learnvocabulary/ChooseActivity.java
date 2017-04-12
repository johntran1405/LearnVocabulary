package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class ChooseActivity extends AppCompatActivity {
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;
    int idimg,idword1,idword2,idword3,idword4;


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
        listword=wordsSQLite.getWordsbyCategori(idcate);

        addControl();
        addEvent();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addEvent() {
        RandomImage();
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idimg==idword1)
                {
                    btnA.setBackgroundResource(R.drawable.colorbutton);
                    arrayList.removeAll(arrayList);
                    RandomImage();



                }else{
                    btnA.setBackgroundResource(R.drawable.falsebutton);
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idimg==idword2)
                {
                    btnB.setBackgroundResource(R.drawable.colorbutton);
                    arrayList.removeAll(arrayList);
                    RandomImage();

                }else{
                    btnB.setBackgroundResource(R.drawable.falsebutton);
                }
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idimg==idword3)
                {
                    btnC.setBackgroundResource(R.drawable.colorbutton);
                    arrayList.removeAll(arrayList);
                    RandomImage();
                }else{
                    btnC.setBackgroundResource(R.drawable.falsebutton);
                }
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idimg==idword4)
                {
                    btnD.setBackgroundResource(R.drawable.colorbutton);
                    arrayList.removeAll(arrayList);
                    RandomImage();
                }else{
                    btnD.setBackgroundResource(R.drawable.falsebutton);
                }
            }
        });
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
        ArrayList<Integer> l = new ArrayList<>();
        l.add(-1);
        int si = 4;
        int n = listword.size();
        for(int i = 0; i< si;i++){
            int x = random.nextInt(n);
            if(!l.contains(x)){
                arrayList.add(listword.get(x));
                l.add(x);
            }else{
                si++;
            }


        }
        Collections.shuffle(arrayList);
        RESULT_CHOSEN = random.nextInt(arrayList.size());
        imageView.setBackgroundResource(getResources().getIdentifier(arrayList.get(RESULT_CHOSEN).getImage(),"drawable",getApplicationContext().getPackageName()));
        idimg=arrayList.get(RESULT_CHOSEN).getWordID();

        btnA.setText(arrayList.get(0).getEnglish().toString());
        idword1=arrayList.get(0).getWordID();

        btnB.setText(arrayList.get(1).getEnglish().toString());
        idword2=arrayList.get(1).getWordID();

        btnC.setText(arrayList.get(2).getEnglish().toString());
        idword3=arrayList.get(2).getWordID();

        btnD.setText(arrayList.get(3).getEnglish().toString());
        idword4=arrayList.get(3).getWordID();

    }



}
