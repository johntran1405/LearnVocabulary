package ccwav.blogspot.com.learnvocabulary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ccwav.blogspot.com.learnvocabulary.Database.SQLiteContactController;
import ccwav.blogspot.com.learnvocabulary.Database.SQLiteDataController;
import ccwav.blogspot.com.learnvocabulary.Model.maucaudbModel;

public class MauCauActivity extends AppCompatActivity {
    final int currentDBVersion=2;
    final String DBVERSION_Key="DBVERSION_Key";
    ArrayList<maucaudbModel> listMauCau;
    static int REQ_CODE = 1;
    List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
    ArrayList<String> text=new ArrayList<>();
    TextView txtmaucau;
    TextView txtdapan;
    Button btmaucau;
    int value = 0;
    String dapan;
    SimpleAdapter adapter;
    String txtRate;
    boolean checkdapan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mau_cau);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) displayError();

        setContentView(R.layout.activity_mau_cau);
        initView();
        createDB();
        listMauCau = getData();
        btmaucau.setEnabled(false);

        String maucau = listMauCau.get(0).getSentence();
        txtmaucau.setText(maucau);
        dapan = listMauCau.get(0).getSentence();

        adapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_2,
                new String[]{"text", "score"},
                new int[]{android.R.id.text1, android.R.id.text2});
        ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(adapter);
    }
    private void initView() {
        txtdapan = (TextView) findViewById(R.id.txtdapan);
        txtmaucau = (TextView) findViewById(R.id.txtmaucau);
        btmaucau = (Button) findViewById(R.id.btmaucau);
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
    public void onSpeech(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent, REQ_CODE);
    }

    void displayError() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Your device has no speech recognizer module installed!!!")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                }).show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //float[] score = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
                listData.clear();

                Map<String, String> item = new HashMap<String, String>();
                item.put("text", text.get(0));
//                item.put("score", extractScore(score[0]));
                listData.add(item);

                adapter.notifyDataSetChanged();
                txtRate = text.get(0);
                Thuchienkiemtra();
            }
        }
    }
    private void Thuchienkiemtra() {
        btmaucau.setEnabled(true);
        if (txtRate != null){
            btmaucau.setEnabled(true);
            if (txtRate.toLowerCase().equals(dapan.toLowerCase())){
                checkdapan = true;
            }else {
                checkdapan = false;
            }
            if (checkdapan){
                txtdapan.setText("Phát âm đúng");
            }else {
                txtdapan.setText("Phát âm sai");
            }
        }else {
            Toast.makeText(getBaseContext(), "Bạn chưa phát âm", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed() {
        System.exit(0);
    }

    private ArrayList<maucaudbModel> getData() {
        SQLiteContactController sql = new SQLiteContactController(this);
        return sql.getListMauCau();
    }

    public void nextBai(View v) {
        btmaucau.setEnabled(false);
        listData.clear();
        adapter.notifyDataSetChanged();
        txtRate=null;
        txtdapan.setText("");
        value++;
        if (value < listMauCau.size()) {
            String maucau = listMauCau.get(value).getSentence();
            txtmaucau.setText(maucau);
            dapan = listMauCau.get(value).getSentence();
        } else {
            Toast.makeText(MauCauActivity.this, "Hết bài!", Toast.LENGTH_SHORT).show();
        }
    }
}
