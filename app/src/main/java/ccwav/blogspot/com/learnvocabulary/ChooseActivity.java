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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate,correctAnswer,index = -1;
    int idimg, idword1, idword2, idword3, idword4;


    static int NUM_ITEMS = 0;
    ImageView imageView;
    Button btnA, btnB, btnC, btnD;

    ArrayList<Words_Model> listword;
    ArrayList<Button> arrButton;
    ArrayList<Words_Model> currentWord;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout_m);
        Intent getIntent = getIntent();
        bundle = getIntent.getBundleExtra("IDCate");
        idcate = bundle.getInt("id");
        Init();

    }


    private void loadQuestion() {
        wordsSQLite = new WordsSQLite(this);
        listword = wordsSQLite.getWordsbyCategori(idcate);
    }

    public void showQuestion() {
        clearUI();
        Random random = new Random();
        if (listword.size() >= 4) {

            currentWord.clear();
            int position;
            while (currentWord.size() < 4) {

                position = random.nextInt(listword.size());
                Boolean check = false;
                //check = true
                for (int i = 0; i < currentWord.size(); i++) {
                    if (currentWord.get(i).getWordID() == listword.get(i).getWordID()) {
//                        listword.remove(i);
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    currentWord.add(listword.get(position));
                }

            }
            correctAnswer = random.nextInt(4);
            imageView.setBackgroundResource(getResources().getIdentifier(currentWord.get(correctAnswer).getImage(),
                    "drawable", getApplicationContext().getPackageName()));
            for(int i = 0;i<4 ;i++){
                arrButton.get(i).setText(currentWord.get(i).getEnglish());
            }

        } else {
            index = -1;
            clearUI();
            Toast.makeText(this, " đợi cập nhật thêm database", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dapan1 :{
                processAnswer(0);
                break;
            }
            case R.id.dapan2 :{
                processAnswer(1);
                break;
            }
            case R.id.dapan3 :{
                processAnswer(2);
                break;
            }
            case R.id.dapan4 :{
                processAnswer(3);
                break;
            }
        }

    }
    private void processAnswer(int answer){
        if(answer == correctAnswer){
            //Remove correct answer
            for (int i = 0; i < listword.size(); i++)
            {
                if (listword.get(i).getWordID() == currentWord.get(correctAnswer).getWordID())
                {
                    listword.remove(i);
                    break;
                }
            }
        }
        else {
            Toast.makeText(this, "Ban da tra loi sai!", Toast.LENGTH_LONG).show();
        }
        showQuestion();
    }
    private void Init() {
        currentWord = new ArrayList<>();
        addControl();
        loadQuestion();
        showQuestion();

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void clearUI(){
//        imageView.setBackground(null);
        btnA.setText("");
        btnB.setText("");
        btnC.setText("");
        btnD.setText("");
    }


    private void addControl() {
        arrButton = new ArrayList<>();
        imageView = (ImageView) findViewById(R.id.imagecate);
        btnA = (Button) findViewById(R.id.dapan1);
        btnA.setOnClickListener(this);
        btnB = (Button) findViewById(R.id.dapan2);
        btnB.setOnClickListener(this);
        btnC = (Button) findViewById(R.id.dapan3);
        btnC.setOnClickListener(this);
        btnD = (Button) findViewById(R.id.dapan4);
        btnD.setOnClickListener(this);
        arrButton.add(btnA);
        arrButton.add(btnB);
        arrButton.add(btnC);
        arrButton.add(btnD);
    }



//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private void addEvent() {
//        RandomImage();
//        btnA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(idimg==idword1)
//                {
//                    btnA.setBackgroundResource(R.drawable.truecolorbutton);
//                    arrayList.removeAll(arrayList);
////                    RandomImage();
//
//
//
//                }else{
//                    btnA.setBackgroundResource(R.drawable.falsecolorbutton);
//                }
//            }
//        });
//        btnB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(idimg==idword2)
//                {
//                    btnB.setBackgroundResource(R.drawable.truecolorbutton);
//                    arrayList.removeAll(arrayList);
////                    RandomImage();
//
//                }else{
//                    btnB.setBackgroundResource(R.drawable.falsecolorbutton);
//                }
//            }
//        });
//        btnC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(idimg==idword3)
//                {
//                    btnC.setBackgroundResource(R.drawable.truecolorbutton);
//                    arrayList.removeAll(arrayList);
////                    RandomImage();
//                }else{
//                    btnC.setBackgroundResource(R.drawable.falsecolorbutton);
//                }
//            }
//        });
//        btnD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(idimg==idword4)
//                {
//                    btnD.setBackgroundResource(R.drawable.truecolorbutton);
//                    arrayList.removeAll(arrayList);
////                    RandomImage();
//                }else{
//                    btnD.setBackgroundResource(R.drawable.falsecolorbutton);
//                }
//            }
//        });
//    }


//    public void RandomImage(){
//        Random random = new Random();
//        ArrayList<Integer> l = new ArrayList<>();
//        l.add(-1);
//        int si = 4;
//        int n = listword.size();
//        for(int i = 0; i< si;i++){
//            int x = random.nextInt(n);
//            if(!l.contains(x)){
//                arrayList.add(listword.get(x));
//                l.add(x);
//            }else{
//                si++;
//            }
//
//
//        }
//        Collections.shuffle(arrayList);
//        RESULT_CHOSEN = random.nextInt(arrayList.size());
//        imageView.setBackgroundResource(getResources().getIdentifier(arrayList.get(RESULT_CHOSEN).getImage(),"drawable",getApplicationContext().getPackageName()));
//        idimg=arrayList.get(RESULT_CHOSEN).getWordID();
//
//        btnA.setText(arrayList.get(0).getEnglish().toString());
//        idword1=arrayList.get(0).getWordID();
//
//        btnB.setText(arrayList.get(1).getEnglish().toString());
//        idword2=arrayList.get(1).getWordID();
//
//        btnC.setText(arrayList.get(2).getEnglish().toString());
//        idword3=arrayList.get(2).getWordID();
//
//        btnD.setText(arrayList.get(3).getEnglish().toString());
//        idword4=arrayList.get(3).getWordID();
//
//    }


}
