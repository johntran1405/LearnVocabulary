package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Common.DialogEx;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class ListenAndChooseActivity extends AppCompatActivity
        implements TextToSpeech.OnInitListener,
        View.OnClickListener {

    // @Override
    ImageButton imageBTN1, imageBTN2, imageBTN3, imageBTN4;
    TextView txtNewWord;
    Button btnSpeak, btnNext;

    WordsSQLite wordsSQLite;
    Bundle bundle;
    private int idcate, index = -1, correctAnswer;

    ArrayList<Words_Model> listword;
    ArrayList<Words_Model> curentWords;
    ArrayList<ImageButton> btnImages;
    TextToSpeech finalMTts = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listenchoose_layout_m);
        Intent getIntent = getIntent();
        bundle = getIntent.getBundleExtra("IDCate");
        idcate = bundle.getInt("id");

        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finalMTts.speak(txtNewWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 500);

    }

    private void loadQuestion() {
        wordsSQLite = new WordsSQLite(this);
        listword = wordsSQLite.getWordsbyCategori(idcate);
        finalMTts = new TextToSpeech(this.getApplicationContext(), this);
    }

    protected void showQuestion() {
        clearUI();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finalMTts.speak(txtNewWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        }, 1000);
        btnNext.setEnabled(listword.size() >= 4);
        if (listword.size() >= 4) {
            curentWords.clear();
            int position;
            Random random = new Random();
            while (curentWords.size() < 4) {
                position = random.nextInt(listword.size());

                //Search in current list
                Boolean hasInCurrent = false; //  <-- biến này kiểm tra xem từ đó đã có trong mảng chưa
                for (int i = 0; i < curentWords.size(); i++) {
                    if (curentWords.get(i).getWordID() == listword.get(position).getWordID()) {
                        hasInCurrent = true; // <-- kiểm tra đã có thì bật cái cờ lên và đi chỗ khác chơi
                        break;
                    }
                }
                if (!hasInCurrent) // vấn đề ở khúc này
                {
                    curentWords.add(listword.get(position));
                }
            }
            correctAnswer = random.nextInt(4);
            txtNewWord.setText(curentWords.get(correctAnswer).getEnglish());
            for (int i = 0; i < 4; i++) {
                btnImages.get(i).setBackgroundResource(
                        getResources().getIdentifier(curentWords.get(i).getImage(),
                                "drawable", getApplicationContext().getPackageName()));
            }
        } else {
            index = -1;
            clearUI();
            DialogEx.show(this,"Xin Chúc Mừng","Bạn đã hoàn thành !!");

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext: {
                break;
            }
            case R.id.imageBTN1: {
                processAnswer(0);
                break;
            }
            case R.id.imageBTN2: {
                processAnswer(1);
                break;
            }
            case R.id.imageBTN3: {
                processAnswer(2);
                break;
            }
            case R.id.imageBTN4: {
                processAnswer(3);
                break;
            }
        }
    }

    private void processAnswer(int answer) {
        if (answer == correctAnswer) {
            //Remove correct answer
            for (int i = 0; i < listword.size(); i++) {
                if (listword.get(i).getWordID() == curentWords.get(correctAnswer).getWordID()) {
                    listword.remove(i);
                    break;
                }
            }
        } else {
            DialogEx.show(this,"Chú ý","Bạn Chọn sai rồi !!");
        }
        showQuestion();
    }

    private void init() {
        curentWords = new ArrayList<Words_Model>();
        addControl();
        loadQuestion();
        showQuestion();
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalMTts.speak(txtNewWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void addControl() {
        btnImages = new ArrayList<ImageButton>();

        imageBTN1 = (ImageButton) findViewById(R.id.imageBTN1);
        imageBTN1.setOnClickListener(this);
        imageBTN2 = (ImageButton) findViewById(R.id.imageBTN2);
        imageBTN2.setOnClickListener(this);
        imageBTN3 = (ImageButton) findViewById(R.id.imageBTN3);
        imageBTN3.setOnClickListener(this);
        imageBTN4 = (ImageButton) findViewById(R.id.imageBTN4);
        imageBTN4.setOnClickListener(this);

        btnImages.add(imageBTN1);
        btnImages.add(imageBTN2);
        btnImages.add(imageBTN3);
        btnImages.add(imageBTN4);

        txtNewWord = (TextView) findViewById(R.id.txtNewWord);
        btnSpeak = (Button) findViewById(R.id.btnSoundSpeak);
        btnSpeak.setOnClickListener(this);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void clearUI() {
        txtNewWord.setText("");
        imageBTN1.setBackground(null);
        imageBTN2.setBackground(null);
        imageBTN3.setBackground(null);
        imageBTN4.setBackground(null);
    }

    @Override
    public void onInit(int i) {
        if (i != TextToSpeech.ERROR) {
            finalMTts.setLanguage(Locale.US);
        }
    }

    @Override
    protected void onDestroy() {
        if (finalMTts != null) {
            finalMTts.stop();
            finalMTts.shutdown();
        }
        super.onDestroy();
    }


}
