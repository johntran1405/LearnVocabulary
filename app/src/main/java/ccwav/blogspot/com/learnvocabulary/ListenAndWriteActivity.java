package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Common.DialogEx;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.IDButton;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class ListenAndWriteActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private static final int CHECK_ANWSER = 0;
//    private static final int GAME_OVER = 1;
//    private int heart;
//    private int point;
    private Handler handler;
    private Button btntiep,btnphatam;
//    private TextView txtHeart,txtPoint,;
    private TextView txtread;
    private ImageView imgPicture;
    private LinearLayout lnAnwser1, lnAnwser2, lnCh1, lnCh2;
    private List<Words_Model> listQuestions;
    private Random random;
    private int i = 0;
    private int pst = 0;
    private String dapan;
    private List<IDButton> listChar;
    RelativeLayout relativeLayout;
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;
    TextToSpeech finalMTts = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listenwrite_layout_m);
        relativeLayout= (RelativeLayout) findViewById(R.id.listenwrite);
        relativeLayout.setBackgroundResource(R.drawable.backgr);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");
        Log.d("IDW","id: "+idcate);
        wordsSQLite= new WordsSQLite(this);
        finalMTts= new TextToSpeech(this.getApplicationContext(),this);
        initComponets();
        makeQuestion();
        handler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case CHECK_ANWSER:
                        if (checkAnwser()) {
//                            Toast.makeText(ListenAndWriteActivity.this, "Thiên tài !", Toast.LENGTH_SHORT).show();
//                            point += 100;
//                            txtPoint.setText(point + "");
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_tile_true);
                                ((Button) findViewById(i)).setClickable(false);
                            }
                            btntiep.setVisibility(View.VISIBLE);
                        } else {
                               /* heart--;
                                txtHeart.setText(heart + "");
                                if (heart <= 0) {
                                    handler.sendEmptyMessage(GAME_OVER);
                                    return;
                                }
                                Toast.makeText(ListenAndWriteActivity.this, "Đáp án sai !", Toast.LENGTH_SHORT).show();*/

                                for (int i = 16; i < dapan.length() + 16; i++) {
                                    ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_tile_false);
                                }
                                /*if (heart <= 0) {
                                    handler.sendEmptyMessage(GAME_OVER);
                                }*/

                        }
                        break;
//                    case GAME_OVER:
//                        Toast.makeText(ListenAndWriteActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
//                        finish();
//                        break;
//                    default:
//                        break;
                }
            }
        };

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                finalMTts.speak(txtread.getText().toString(), TextToSpeech.QUEUE_FLUSH,null);
            }
        }, 2000);


    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void makeQuestion() {
        Words_Model qs = listQuestions.get(i);
        dapan = qs.getEnglish();
        txtread.setText(dapan);
        final String imageFileName = qs.getImage();
        Log.d("anh","Tenanh"+ imageFileName);
        LayoutInflater inflater = LayoutInflater.from(this);
        if (dapan.length() > 8) {
            for (int i = 0; i < 8; i++) {
                Button view = (Button) inflater.inflate(R.layout.item_btn_anwser, lnAnwser1, false);
                view.setId(16 + i);
                lnAnwser1.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((Button) view).getText() != "") {
                            ((Button) view).setText("");
                            for (int i = 0; i < listChar.size(); i++) {
                                if (listChar.get(i).getIdAnwser() == view.getId()) {
                                    ((Button) findViewById(listChar.get(i).getIdPick())).setVisibility(View.VISIBLE);
                                    listChar.remove(i);
                                    break;
                                }
                            }
                            pst--;
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_anwser);
                            }
                        }
                    }
                });
            }
            for (int i = 8; i < dapan.length(); i++) {
                Button view = (Button) inflater.inflate(R.layout.item_btn_anwser, lnAnwser2, false);
                view.setId(16 + i);
                lnAnwser2.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((Button) view).getText() != "") {
                            ((Button) view).setText("");
                            for (int i = 0; i < listChar.size(); i++) {
                                if (listChar.get(i).getIdAnwser() == view.getId()) {
                                    ((Button) findViewById(listChar.get(i).getIdPick())).setVisibility(View.VISIBLE);
                                    listChar.remove(i);
                                    break;
                                }
                            }
                            pst--;
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_anwser);
                            }
                        }
                    }
                });
            }
        } else {
            for (int i = 0; i < dapan.length(); i++) {
                Button view = (Button) inflater.inflate(R.layout.item_btn_anwser, lnAnwser1, false);
                view.setId(16 + i);
                lnAnwser1.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((Button) view).getText() != "") {
                            ((Button) view).setText("");
                            for (int i = 0; i < listChar.size(); i++) {
                                if (listChar.get(i).getIdAnwser() == view.getId()) {
                                    ((Button) findViewById(listChar.get(i).getIdPick())).setVisibility(View.VISIBLE);
                                    listChar.remove(i);
                                    break;
                                }
                            }
                            pst--;
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_anwser);
                            }
                        }
                    }
                });
            }
        }
        imgPicture.setBackground(getImage(imageFileName));

        String[] kt = {"a", "b", "c", "d", "e","f","g", "h", "i","j","k", "l", "m", "n", "o", "u", "q", "p", "r", "s", "t", "y", "v", "x","z","w"};
        List<String> tl = new ArrayList();

        for (int i = 0; i < dapan.length(); i++) {
            tl.add(dapan.charAt(i) + "");
        }

        for (int i = 0; i < 16 - dapan.length(); i++) {
            tl.add(kt[random.nextInt(kt.length)]);
        }
        Collections.shuffle(tl);

        for (int i = 0; i < 8; i++) {
            Button view = (Button) inflater.inflate(R.layout.item_btn, lnCh1, false);
            view.setId(i);
            view.setText(tl.get(i));
            lnCh1.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pst < dapan.length()) {
                        Button btn = (Button) view;
                        addChar(btn.getId(), btn.getText().toString());
                        btn.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
        for (int i = 8; i < 16; i++) {
            Button view = (Button) inflater.inflate(R.layout.item_btn, lnCh2, false);
            view.setId(i);
            view.setText(tl.get(i));
            lnCh2.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pst < dapan.length()) {
                        Button btn = (Button) view;
                        addChar(btn.getId(), btn.getText().toString());
                        btn.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }
    private Drawable getImage(String img)
    {
        int imgResId = getResources().getIdentifier(String.valueOf(img), "drawable",getApplicationContext().getPackageName());
        Log.d("Hinh","TenHinh"+ imgResId);
        Drawable dr;
        if(imgResId!=0) {
            dr = getApplicationContext().getResources().getDrawable(imgResId);
        }else
        {
            dr = getApplicationContext().getResources().getDrawable(R.drawable.home);
        }
        return dr;
    }
    private void initComponets() {
        listChar = new ArrayList();
//        heart = 5;
//        point = 0;
        random = new Random();

        btntiep = (Button) findViewById(R.id.btn_tiep);
        btntiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newQuestion();
            }
        });
        btnphatam= (Button) findViewById(R.id.btnphatam);
        btnphatam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalMTts.speak(txtread.getText().toString(), TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        imgPicture = (ImageView) findViewById(R.id.img_picture);
        lnAnwser1 = (LinearLayout) findViewById(R.id.anwser1);
        lnAnwser2 = (LinearLayout) findViewById(R.id.anwser2);
        lnCh1 = (LinearLayout) findViewById(R.id.ln_3);
        lnCh2 = (LinearLayout) findViewById(R.id.ln_4);
//        txtHeart = (TextView) findViewById(R.id.txt_heart);
//        txtPoint = (TextView) findViewById(R.id.txt_point);
        txtread= (TextView) findViewById(R.id.txtreadword);
//        txtHeart.setText(heart + "");
//        txtPoint.setText(point + "");

        listQuestions = new ArrayList<>();
        listQuestions=wordsSQLite.getAllWordsbyCategori(idcate);

        Log.d("mang","tu: "+ listQuestions);
        Collections.shuffle(listQuestions);

    }
    private void newQuestion() {
        if (i < listQuestions.size() - 1) {
            listChar.clear();
            btntiep.setVisibility(View.INVISIBLE);
            pst = 0;
            i++;
            lnAnwser1.removeAllViews();
            lnAnwser2.removeAllViews();
            lnCh1.removeAllViews();
            lnCh2.removeAllViews();
            makeQuestion();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    finalMTts.speak(txtread.getText().toString(), TextToSpeech.QUEUE_FLUSH,null);
                }
            }, 1000);
        } else
        {
            DialogEx.show(this, "Xin Chúc Mừng", "Bạn đã hoàn thành !!");
        }

    }
    public void addChar(int id, String s) {
        for (int i = 16; i < dapan.length() + 16; i++) {
            if (((Button) findViewById(i)).getText() == "") {
                ((Button) findViewById(i)).setText(s);
                listChar.add(new IDButton(id, i));

                pst++;
                if (pst == dapan.length()) {
                    handler.sendEmptyMessage(CHECK_ANWSER);
                }
                return;
            }
        }

    }
    public boolean checkAnwser() {
        String da = "";
        for (int i = 16; i < dapan.length() + 16; i++) {
            da += ((Button) findViewById(i)).getText();
        }
        if (da.equals(dapan)) {
            return true;
        } else
            return false;
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            finalMTts.setLanguage(Locale.US);
        } else {
            // Initialization failed.
            Log.e("app", "Could not initialize TextToSpeech.");
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
}
