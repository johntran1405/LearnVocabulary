package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

import static ccwav.blogspot.com.learnvocabulary.VocabularyActivity.listword;

public class ListenAndChooseActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    // @Override
    ImageButton imageBTN1, imageBTN2, imageBTN3, imageBTN4;
    TextView txtNewWord;
    Button btnSpeak, btnNext;

    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate, IdImage1, IdImage2, IdImage3, IdImage4, IdWord;
    ArrayList<Words_Model> listword = new ArrayList<>();
    ArrayList<Words_Model> arrayList = new ArrayList<>();
    TextToSpeech finalMTts = null;
    private int RESULT_CHOSEN = -1, RESULT_FAILED = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listenchoose_layout_m);
        Intent getIntent = getIntent();
        bundle = getIntent.getBundleExtra("IDCate");
        idcate = bundle.getInt("id");

        wordsSQLite = new WordsSQLite(this);
        listword = wordsSQLite.getAllWordsbyCategori(idcate);
//        for(int i= 0; i<listword.size();i++){
//            Log.d("Test"," listword :" +listword.get(i).getEnglish());
//        }
        finalMTts = new TextToSpeech(this.getApplicationContext(), this);
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

//        RandomImg();
        RandomImage();
        checkResult();
//        getAnimationImageButton();


    }

    //cách 1 để load random kết quả lên
    public void RandomImage() {
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int n = listword.size();
            int x = random.nextInt(n);
            arrayList.add(listword.get(x));
            Log.d("Count ++ ", "tum lum : " + arrayList.size());
            listword.remove(x);
            Log.d("Count --", "tum lum : " + listword.size());

        }

        RESULT_CHOSEN = random.nextInt(arrayList.size());
        txtNewWord.setText(arrayList.get(RESULT_CHOSEN).getEnglish().toString());
        IdWord = arrayList.get(RESULT_CHOSEN).getWordID();
        imageBTN1.setBackgroundResource(getResources().getIdentifier(arrayList.get(0).getImage(), "drawable", getApplicationContext().getPackageName()));
        IdImage1 = arrayList.get(0).getWordID();
        imageBTN2.setBackgroundResource(getResources().getIdentifier(arrayList.get(1).getImage(), "drawable", getApplicationContext().getPackageName()));
        IdImage2 = arrayList.get(1).getWordID();
        imageBTN3.setBackgroundResource(getResources().getIdentifier(arrayList.get(2).getImage(), "drawable", getApplicationContext().getPackageName()));
        IdImage3 = arrayList.get(2).getWordID();
        imageBTN4.setBackgroundResource(getResources().getIdentifier(arrayList.get(3).getImage(), "drawable", getApplicationContext().getPackageName()));
        IdImage4 = arrayList.get(3).getWordID();
        Log.d("ID", "tum lum : " + IdWord);
        Log.d("ID", "tum lum : " + IdImage1);
        Log.d("ID", "tum lum : " + IdImage2);
        Log.d("ID", "tum lum : " + IdImage3);
        Log.d("ID", "tum lum : " + IdImage4);
    }


    //cách 2 để load random kết quả lên
//    public void RandomImg() {
//        ArrayList<Words_Model> temp = new ArrayList<>();
////        temp.add(new Words_Model(listword.get(0)));
////        temp.add(new Words_Model(listword.get(1)));
////        temp.add(new Words_Model(listword.get(2)));
////        temp.add(new Words_Model(listword.get(3)));
//        for (int j = 0; j<listword.size();j++){
//            temp.add(new Words_Model(listword.get(j)));
//        }
//
//        Random random = new Random();
//        int position = random.nextInt(temp.size());
//
//        imageBTN1.setBackgroundResource(getResources().getIdentifier(temp.get(position).getImage(), "drawable", getApplicationContext().getPackageName()));
//        IdImage1 = temp.get(position).getWordID();
//        temp.remove(position);
//
//        position = random.nextInt(temp.size());
//        imageBTN2.setBackgroundResource(getResources().getIdentifier(temp.get(position).getImage(), "drawable", getApplicationContext().getPackageName()));
//        IdImage2 = temp.get(position).getWordID();
//        temp.remove(position);
//
//        position = random.nextInt(temp.size());
//        imageBTN3.setBackgroundResource(getResources().getIdentifier(temp.get(position).getImage(), "drawable", getApplicationContext().getPackageName()));
//        IdImage3 = temp.get(position).getWordID();
//        temp.remove(position);
//
//        imageBTN4.setBackgroundResource(getResources().getIdentifier(temp.get(0).getImage(), "drawable", getApplicationContext().getPackageName()));
//        IdImage4 = temp.get(0).getWordID();
//    }
    private void checkResult() {
        imageBTN1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (IdWord == IdImage1) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            arrayList.removeAll(arrayList);
                            Log.d("Count", "tum lum : " + arrayList.size());
                            RandomImage();
//                            RandomImg();
                            btnNext.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi cưng ! Chọn lại đi nhé", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        imageBTN2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (IdWord == IdImage2) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            arrayList.removeAll(arrayList);
                            Log.d("Count", "tum lum : " + arrayList.size());
                            RandomImage();
//                            RandomImg();
                            btnNext.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi cưng ! Chọn lại đi nhé", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        imageBTN3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (IdWord == IdImage3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            arrayList.removeAll(arrayList);
                            Log.d("Count", "tum lum : " + arrayList.size());
                            RandomImage();
//                            RandomImg();
                            btnNext.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi cưng ! Chọn lại đi nhé", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        imageBTN4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (IdWord == IdImage4) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            arrayList.removeAll(arrayList);

                            RandomImage();
//                            RandomImg();
                            btnNext.setVisibility(View.INVISIBLE);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Sai rồi cưng ! Chọn lại đi nhé", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

//    private void checkResult(int choose) {
//
//        if (choose == RESULT_CHOSEN) {
//            btnNext.setVisibility(View.VISIBLE);
//
//        } else {
//            Toast.makeText(this, "Sai rồi ", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void getAnimationImageButton() {
//        imageBTN4.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // PRESSED
////                        btnNext.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                RandomImage();
////                            }
////                        });
//                        return true; // if you want to handle the touch event
//                    case MotionEvent.ACTION_UP:
//                        // RELEASED
//                        checkResult(3);
//                        return true; // if you want to handle the touch event
//                }
//                return false;
//
//            }
//        });
//        imageBTN3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // PRESSED
////                        btnNext.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                RandomImage();
////                            }
////                        });
//                        return true; // if you want to handle the touch event
//                    case MotionEvent.ACTION_UP:
//                        // RELEASED
//                        checkResult(2);
//                        return true; // if you want to handle the touch event
//                }
//                return false;
//
//            }
//        });
//        imageBTN2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // PRESSED
////                        btnNext.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                RandomImage();
////                            }
////                        });
//                        return true; // if you want to handle the touch event
//                    case MotionEvent.ACTION_UP:
//                        // RELEASED
//                        checkResult(1);
//                        return true; // if you want to handle the touch event
//                }
//                return false;
//
//            }
//        });
//
//        imageBTN1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // PRESSED
////                        btnNext.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                RandomImage();
////                            }
////                        });
//                        return true;
//                    // if you want to handle the touch event
//                    case MotionEvent.ACTION_UP:
//                        // RELEASED
//                        checkResult(0);
//                        return true; // if you want to handle the touch event
//                }
//                return false;
//
//            }
//        });
//    }

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
