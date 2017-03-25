package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;

public class SecondActivity extends AppCompatActivity {

    // @Override
    Button btnVocabulary, btnListenChoose, btnListenWrite, btnChoose, btnSpeech;
    RelativeLayout se_R1, se_R2, se_R3;
    private final int Time = 500; // time delay circle
    private Animation rotale1;  // get animation

    MyDatabase myDatabase;
    Bundle bundle;
    int idcate;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_m);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("ID");
        idcate=bundle.getInt("Id");
        Log.d("IDCATE","id: "+idcate);

        addControl();
        addEvent();


    }

    private void addControl() {
        btnVocabulary = (Button) findViewById(R.id.btn_vocabulary);
        btnListenChoose = (Button) findViewById(R.id.btn_listen_choose);
        btnListenWrite = (Button) findViewById(R.id.btn_listen_write);
        btnChoose = (Button) findViewById(R.id.btn_choose);
        btnSpeech = (Button) findViewById(R.id.btn_speech);

        rotale1 = AnimationUtils.loadAnimation(this, R.anim.rotale);
//        rotale2 = AnimationUtils.loadAnimation(this, R.anim.rotale);
//        rotale3 = AnimationUtils.loadAnimation(this, R.anim.rotale);
//        rotale4 = AnimationUtils.loadAnimation(this, R.anim.rotale);
//        rotale5 = AnimationUtils.loadAnimation(this,R.anim.rotale);

        se_R1 = (RelativeLayout) findViewById(R.id.se_R1); // R = RelativeLayout,


    }

    private void addEvent() {
        boolean btndelay = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                se_R1.startAnimation(rotale1);
            }
        }, Time);
    }


    public void TouchButton(View v) {
        //get Animation rotale
        final Animation anim_home = AnimationUtils.loadAnimation(this, R.anim.rotale);
        // set animation scale when the user touch one in all topic button
        if (v.getId() == R.id.btn_vocabulary) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(getApplicationContext(), VocabularyActivity.class);
            //intent.putExtra("Data",null);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_speech) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(SecondActivity.this, SpeechActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_choose) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(SecondActivity.this, ChooseActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_listen_write) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(SecondActivity.this, ListenAndWriteActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_listen_choose) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(SecondActivity.this, ListenAndChooseActivity.class);
            startActivity(intent);
        }
    }


}
