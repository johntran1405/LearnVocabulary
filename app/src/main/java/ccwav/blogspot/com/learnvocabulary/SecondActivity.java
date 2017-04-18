//package ccwav.blogspot.com.learnvocabulary;
//
//import android.content.Intent;
//import android.media.Image;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//public class SecondActivity extends AppCompatActivity {
//
//    // @Override
//    private ImageView imgVocabulary, imgListenChoose, imgListenWrite, imgChoice, imgSpeech;
//    private TextView txtVocabulary, txtListenChoose, txtListenWrite, txtChoice, txtSpeech;
//    private final int Time = 500; // time delay circle
//    private Animation rotale1;  // get animation
//
//    Bundle bundle;
//    int idcate;
//
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second_m);
//        Intent getIntent=getIntent();
//        bundle=getIntent.getBundleExtra("ID");
//        idcate=bundle.getInt("Id");
//
//        addControl();
//        addEvent();
//
//
//    }
//    private void addEvent() {
//
//    }
//    private void addControl() {
//        imgVocabulary = (ImageView) findViewById(R.id.imgVocabulary);
//        imgListenChoose = (ImageView) findViewById(R.id.imgListenChoose);
//        imgListenWrite = (ImageView) findViewById(R.id.imgListenWrite);
//        imgChoice = (ImageView) findViewById(R.id.imgChoice);
//        imgSpeech = (ImageView) findViewById(R.id.imgSpeech);
//
//        txtVocabulary = (TextView) findViewById(R.id.txtVocabulary);
//        txt = (TextView) findViewById(R.id.txtVocabulary);
//        txtVocabulary = (TextView) findViewById(R.id.txtVocabulary);
//        txtVocabulary = (TextView) findViewById(R.id.txtVocabulary);
//        txtVocabulary = (TextView) findViewById(R.id.txtVocabulary);
//    }
//    public void TouchButton(View v) {
//        //get Animation rotale
//        final Animation anim_home = AnimationUtils.loadAnimation(this, R.anim.rotale);
//        // set animation scale when the user touch one in all topic button
//        if (v.getId() == R.id.btn_vocabulary) {
//            v.startAnimation(anim_home);
//            Intent intent = new Intent(getApplicationContext(), VocabularyActivity.class);
//            bundle.putInt("id",idcate);
//            intent.putExtra("IDCate",bundle);
//            startActivity(intent);
//        } else if (v.getId() == R.id.btn_speech) {
//            v.startAnimation(anim_home);
//            Intent intent = new Intent(SecondActivity.this, SpeechActivity.class);
//            bundle.putInt("id",idcate);
//            intent.putExtra("IDCate",bundle);
//            startActivity(intent);
//        } else if (v.getId() == R.id.btn_choose) {
//            v.startAnimation(anim_home);
//            Intent intent = new Intent(SecondActivity.this, ChooseActivity.class);
//            bundle.putInt("id",idcate);
//            intent.putExtra("IDCate",bundle);
//            startActivity(intent);
//        } else if (v.getId() == R.id.btn_listen_write) {
//            v.startAnimation(anim_home);
//            Intent intent = new Intent(SecondActivity.this, ListenAndWriteActivity.class);
//            bundle.putInt("id",idcate);
//            intent.putExtra("IDCate",bundle);
//            startActivity(intent);
//        } else if (v.getId() == R.id.btn_listen_choose) {
//            v.startAnimation(anim_home);
//            Intent intent = new Intent(SecondActivity.this, ListenAndChooseActivity.class);
//            bundle.putInt("id",idcate);
//            intent.putExtra("IDCate",bundle);
//            startActivity(intent);
//        }
//    }
//
//
//}
