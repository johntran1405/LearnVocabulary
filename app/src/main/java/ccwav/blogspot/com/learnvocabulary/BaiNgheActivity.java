package ccwav.blogspot.com.learnvocabulary;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.Database.SQLiteContactController;
import ccwav.blogspot.com.learnvocabulary.Database.SQLiteDataController;
import ccwav.blogspot.com.learnvocabulary.Model.bainghedbModel;

public class BaiNgheActivity extends AppCompatActivity {
    final int currentDBVersion = 2;
    final String DBVERSION_Key = "DBVERSION_Key";
    ArrayList<bainghedbModel> listData;
    TextView name;
    TextView txtquestion, txtdapan;
    RadioButton rb_answer1;
    RadioButton rb_answer2;
    RadioButton rb_answer3;
    RadioButton rb_answer4;
    Button bt_tieptheo, bt_kiemtra;
    RadioGroup rg_answer;
    int id;
    Thread t;
    public int diem = 0;
    int value = 0;

    ImageButton btnPlay;
    ImageButton btnPause;
    //MediaPlayer mp = new MediaPlayer();
    MediaPlayer mp = null;
    SeekBar sb_media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_nghe);
        initView();
        createDB();
        listData = getData();

        String n = listData.get(0).getName();
        name.setText(n);

        String q = listData.get(0).getQuestion();
        txtquestion.setText(q);
        String b1 = listData.get(0).getAnswer1();
        rb_answer1.setText(b1);
        String b2 = listData.get(0).getAnswer2();
        rb_answer2.setText(b2);
        String b3 = listData.get(0).getAnswer3();
        rb_answer3.setText(b3);
        String b4 = listData.get(0).getAnswer4();
        rb_answer4.setText(b4);

        KiemTraDapAn();
    }
    private void ChaySeekBar() {
//        final SeekBar mSeelBar = new SeekBar(this);
        final int duration = mp.getDuration();
        final int amoungToupdate = duration / 1000;
        sb_media.setMax(amoungToupdate);
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < duration / 1000; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!(amoungToupdate * sb_media.getProgress() >= duration)) {
                                int p = sb_media.getProgress();
                                p += 1;
                                sb_media.setProgress(p);
                            }
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }

        });
        t.start();
    }
    private void KiemTraDapAn() {
        bt_kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = rg_answer.getCheckedRadioButtonId();
//               Log.e("id", "_______________hehe" + id);
                if (id == -1) {
                    Toast.makeText(getBaseContext(), "Vui lòng chọn một đáp án", Toast.LENGTH_SHORT).show();
                } else {
                    String radiovalue = ((RadioButton) findViewById(rg_answer.getCheckedRadioButtonId())).getText().toString();
                    if (radiovalue.equals(listData.get(value).getAnswer_true())) {
                        txtdapan.setText("Đáp án đúng.");
//                        Toast.makeText(getBaseContext(), "Đáp án đúng", Toast.LENGTH_SHORT).show();
                        diem = diem + 10;
                        Log.e("id", "_______________hehe" + diem);
                    } else {
                        txtdapan.setText("Đáp án đã chọn sai, đáp án đúng là: " + listData.get(value).getAnswer_true());
//                        Toast.makeText(getBaseContext(), "Đáp án đã chọn sai, đáp án đúng là: " + listData.get(value).getAnswer_true(), Toast.LENGTH_SHORT).show();
                    }
                    bt_kiemtra.setEnabled(false);
                    bt_tieptheo.setEnabled(true);
                }
            }
        });

    }
    private void initView() {
        txtdapan = (TextView) findViewById(R.id.txtdapan);
        name = (TextView) findViewById(R.id.name);
        txtquestion = (TextView) findViewById(R.id.txtquestion);
        rb_answer1 = (RadioButton) findViewById(R.id.rb_answer1);
        rb_answer2 = (RadioButton) findViewById(R.id.rb_answer2);
        rb_answer3 = (RadioButton) findViewById(R.id.rb_answer3);
        rb_answer4 = (RadioButton) findViewById(R.id.rb_answer4);
        rg_answer = (RadioGroup) findViewById(R.id.rg_answer);
        bt_kiemtra = (Button) findViewById(R.id.bt_kiemtra);
        bt_tieptheo = (Button) findViewById(R.id.bt_tieptheo);
        bt_tieptheo.setEnabled(false);
        //sb_media = (SeekBar) findViewById(R.id.sb_media);
        btnPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        btnPause = (ImageButton) findViewById(R.id.imageButtonPause);
    }
    private void createDB() {
        try {
            SQLiteDataController sql = new SQLiteDataController(this);
            SharedPreferences share = getSharedPreferences("mycache", MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            int cacheVersionDB = share.getInt(DBVERSION_Key, -1);
            if (cacheVersionDB != currentDBVersion) {
                sql.deleteDatabase();
                editor.putInt(DBVERSION_Key, currentDBVersion);
                editor.commit();
            }
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<bainghedbModel> getData() {
        SQLiteContactController sql = new SQLiteContactController(this);
        return sql.getListBaiNghe();
    }
    public void nextBai(View v) {
        rb_answer1.setChecked(false);
        rb_answer2.setChecked(false);
        rb_answer3.setChecked(false);
        rb_answer4.setChecked(false);
        bt_kiemtra.setEnabled(true);
        bt_tieptheo.setEnabled(false);
        txtdapan.setText("");
        value++;
        if (value < listData.size()) {
            String n = listData.get(value).getName();
            name.setText(n);
            String q = listData.get(value).getQuestion();
            txtquestion.setText(q);
            String b1 = listData.get(value).getAnswer1();
            rb_answer1.setText(b1);
            String b2 = listData.get(value).getAnswer2();
            rb_answer2.setText(b2);
            String b3 = listData.get(value).getAnswer3();
            rb_answer3.setText(b3);
            String b4 = listData.get(value).getAnswer4();
            rb_answer4.setText(b4);
        } else {
            bt_kiemtra.setEnabled(false);
            Toast.makeText(BaiNgheActivity.this, "Hết bài!", Toast.LENGTH_SHORT).show();
        }
    }
    public void stop(View v) {
//        sb_media.setProgress(0);
        if (null == mp) return;
        if (mp.isPlaying()) {
            mp.pause();
            mp = null;
        }
    }
    public void play(View v) {
        try {
            if (mp != null) {
                mp.stop();
                mp.release();
                mp = null;
            }
            String strAudioName = listData.get(value).getAudio();
            int resourceId1 = getResources().getIdentifier(strAudioName, "raw", getPackageName());
            mp = MediaPlayer.create(BaiNgheActivity.this, resourceId1);
//            mp = MediaPlayer.create(BaiNgheActivity.this, R.raw.bai1);
            mp.start();
           /* ChaySeekBar();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    sb_media.setProgress(0);
                }
            });*/
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        diem = 0;
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        } else return;
    }
}
