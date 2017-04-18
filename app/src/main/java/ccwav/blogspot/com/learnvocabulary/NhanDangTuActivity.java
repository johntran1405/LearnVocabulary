package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ccwav.blogspot.com.learnvocabulary.Database.SQLiteContactController;
import ccwav.blogspot.com.learnvocabulary.Database.SQLiteDataController;
import ccwav.blogspot.com.learnvocabulary.Model.nhandangtudbModel;

public class NhanDangTuActivity extends AppCompatActivity {
    final int currentDBVersion = 2;
    final String DBVERSION_Key = "DBVERSION_Key";
    ArrayList<nhandangtudbModel> listData;
    TextView txtname, txtdapan;
    RadioButton rb_answer1, rb_answer2, rb_answer3, rb_answer4;
    Button bt_kiemtra, bt_tieptheo;
    RadioGroup rg_answer;
    int value = 0;

    ImageButton btnLoa;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_dang_tu);
        initView();
        createDB();
        listData = getData();

        String n = listData.get(0).getName();
        txtname.setText(n);
        String a= listData.get(0).getAnswer1();
        rb_answer1.setText(a);
        String b= listData.get(0).getAnswer2();
        rb_answer2.setText(b);
        String c= listData.get(0).getAnswer3();
        rb_answer3.setText(c);
        String d= listData.get(0).getAnswer4();
        rb_answer4.setText(d);


        StringTokenizer tokens1 = new StringTokenizer(listData.get(0).getAnswer1());
        String first = tokens1.nextToken();
        int resourceId1 = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
        rb_answer1.setBackgroundResource(resourceId1);
        StringTokenizer tokens2 = new StringTokenizer(listData.get(0).getAnswer2());
        String second = tokens2.nextToken();
        int resourceId2 = getResources().getIdentifier("@drawable/" + second, null, getPackageName());
        rb_answer2.setBackgroundResource(resourceId2);
        StringTokenizer tokens3 = new StringTokenizer(listData.get(0).getAnswer3());
        String three = tokens3.nextToken();
        int resourceId3 = getResources().getIdentifier("@drawable/" + three, null, getPackageName());
        rb_answer3.setBackgroundResource(resourceId3);
        StringTokenizer tokens4 = new StringTokenizer(listData.get(0).getAnswer4());
        String four = tokens4.nextToken();
        int resourceId4 = getResources().getIdentifier("@drawable/" + four, null, getPackageName());
        rb_answer4.setBackgroundResource(resourceId4);

        KiemTraDapAn();
    }
    private void KiemTraDapAn() {
        bt_kiemtra.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                int id = rg_answer.getCheckedRadioButtonId();
                //Log.e("id", "_______________" + id);
                if (id == -1) {
                    Toast.makeText(getBaseContext(), "Vui lòng chọn một đáp án", Toast.LENGTH_SHORT).show();
                } else {
                    String radiovalue = ((RadioButton) findViewById(rg_answer.getCheckedRadioButtonId())).getText().toString();

//                    Log.d("GiaTri radiovalue =", radiovalue);
//                    Log.d("GiaTri ", rg_answer.getCheckedRadioButtonId()+"");

                    if (radiovalue.equals(listData.get(value).getAnswertrue())) {
                        txtdapan.setText("Đáp án đúng.");
//                        Toast.makeText(getBaseContext(), "Đáp án đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        txtdapan.setText("Sai, đáp án đúng là: " + listData.get(value).getAnswertrue());
//                        Toast.makeText(getBaseContext(), "Đáp án đã chọn sai, đáp án đúng là: " + listData.get(value).getAnswertrue(), Toast.LENGTH_SHORT).show();
                    }
                    bt_kiemtra.setEnabled(false);
                    bt_tieptheo.setEnabled(true);
                }

            }
        });
    }
    private ArrayList<nhandangtudbModel> getData() {
        SQLiteContactController sql = new SQLiteContactController(this);
        return sql.getListNhanDangTu();
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
    private void initView() {
        txtdapan = (TextView) findViewById(R.id.txtdapan);
        txtname = (TextView) findViewById(R.id.txtname);
        rb_answer1 = (RadioButton) findViewById(R.id.rb_answer1);
        rb_answer2 = (RadioButton) findViewById(R.id.rb_answer2);
        rb_answer3 = (RadioButton) findViewById(R.id.rb_answer3);
        rb_answer4 = (RadioButton) findViewById(R.id.rb_answer4);
        rg_answer = (RadioGroup) findViewById(R.id.rg_answer);
        bt_kiemtra = (Button) findViewById(R.id.bt_kiemtra);
        bt_tieptheo = (Button) findViewById(R.id.bt_tieptheo);
        bt_tieptheo.setEnabled(false);
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
            txtname.setText(n);


            String a= listData.get(value).getAnswer1();
            rb_answer1.setText(a);
            String b= listData.get(value).getAnswer2();
            rb_answer2.setText(b);
            String c= listData.get(value).getAnswer3();
            rb_answer3.setText(c);
            String d= listData.get(value).getAnswer4();
            rb_answer4.setText(d);


            StringTokenizer tokens1 = new StringTokenizer(listData.get(value).getAnswer1());
            String first = tokens1.nextToken();
            int resourceId1 = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
            rb_answer1.setBackgroundResource(resourceId1);
            StringTokenizer tokens2 = new StringTokenizer(listData.get(value).getAnswer2());
            String second = tokens2.nextToken();
            int resourceId2 = getResources().getIdentifier("@drawable/" + second, null, getPackageName());
            rb_answer2.setBackgroundResource(resourceId2);
            StringTokenizer tokens3 = new StringTokenizer(listData.get(value).getAnswer3());
            String three = tokens3.nextToken();
            int resourceId3 = getResources().getIdentifier("@drawable/" + three, null, getPackageName());
            rb_answer3.setBackgroundResource(resourceId3);
            StringTokenizer tokens4 = new StringTokenizer(listData.get(value).getAnswer4());
            String four = tokens4.nextToken();
            int resourceId4 = getResources().getIdentifier("@drawable/" + four, null, getPackageName());
            rb_answer4.setBackgroundResource(resourceId4);

        } else {
            bt_kiemtra.setEnabled(false);
            Toast.makeText(NhanDangTuActivity.this, "Hết bài!", Toast.LENGTH_SHORT).show();
        }
    }
    public void loa(View v){
        btnLoa = (ImageButton)findViewById(R.id.imageButtonLoa);
        try{
            if (mp != null){
                mp.stop();
                mp.release();
                mp = null;
            }
            String strAudioName = listData.get(value).getAudio();
            int resourceId = getResources().getIdentifier(strAudioName, "raw", getPackageName());
            mp = MediaPlayer.create(NhanDangTuActivity.this, resourceId);
            //mp = MediaPlayer.create(NhanDangTuActivity.this, R.raw.apple);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                }
            });
        }
        catch (Exception e){}
    }
}
