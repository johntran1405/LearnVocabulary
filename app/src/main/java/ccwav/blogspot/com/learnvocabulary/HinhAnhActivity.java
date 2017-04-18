package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import ccwav.blogspot.com.learnvocabulary.Database.SQLiteContactController;
import ccwav.blogspot.com.learnvocabulary.Database.SQLiteDataController;
import ccwav.blogspot.com.learnvocabulary.Model.hinhanhdbModel;

public class HinhAnhActivity extends AppCompatActivity {
    final int currentDBVersion=2;
    final String DBVERSION_Key="DBVERSION_Key";
    ArrayList<hinhanhdbModel> listHinhAnh;
    static int REQ_CODE = 1;
    List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
    SimpleAdapter adapter;

    Button btn_next;
    TextView name;
    ImageView img_hinhanh;
    String txtRate;
    TextView txtdapan;
    String dapan;
    int value = 0;
    boolean checkdapan = false;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinh_anh);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) displayError();
        setContentView(R.layout.activity_hinh_anh);
        initView();
        createDB();
        btn_next.setEnabled(false);
        listHinhAnh = getData();
        String a = listHinhAnh.get(0).getName();
        name.setText(a);
        dapan = listHinhAnh.get(0).getAnswertrue();

        //lấy img từ sqlite
        StringTokenizer tokens = new StringTokenizer(listHinhAnh.get(0).getPicture());
        String first = tokens.nextToken();
        int resourceId = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
        img_hinhanh.setImageResource( resourceId );


        adapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_2,
                new String[] {"text", "score"},
                new int[] {android.R.id.text1, android.R.id.text2});
        ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(adapter);
    }
    public void onSpeech(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent, REQ_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) if (resultCode == RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            float[] score = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
            listData.clear();

            Map<String, String> item = new HashMap<String, String>();
            item.put("text", text.get(0));
//            item.put("score", extractScore(score[0]));
            listData.add(item);

            adapter.notifyDataSetChanged();
            txtRate = text.get(0);
//            Toast.makeText(HinhAnhActivity.this, "___"+dapan, Toast.LENGTH_SHORT).show();
            Thuchienkiemtra();
        }
    }
    private void Thuchienkiemtra() {
        if (txtRate != null){
            btn_next.setEnabled(true);
            if (txtRate.toLowerCase().equals(dapan.toLowerCase())){
                checkdapan = true;
            }else {
                checkdapan = false;
            }
            if (checkdapan){
                txtdapan.setText("Phát âm đúng");
            }else {
                txtdapan.setText("Phát âm sai. Đáp án là: "+dapan.toLowerCase());
            }
        }else {
            Toast.makeText(getBaseContext(), "Bạn chưa phát âm", Toast.LENGTH_SHORT).show();
        }
    }
    private void displayError() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Your device has no speech recognizer module installed!!!")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { System.exit(0); }
                }).show();
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
        btn_next = (Button) findViewById(R.id.btn_next);
        name = (TextView) findViewById(R.id.name);
        img_hinhanh = (ImageView) findViewById(R.id.img_hinhanh);
        txtdapan = (TextView) findViewById(R.id.txtdapan);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void nextBai(View v) {
        btn_next.setEnabled(false);
        listData.clear();
        adapter.notifyDataSetChanged();
        txtRate = null;
        txtdapan.setText("");
        value++;
        if (value < listHinhAnh.size()) {
            String n = listHinhAnh.get(value).getName();
            name.setText(n);
            StringTokenizer tokens = new StringTokenizer(listHinhAnh.get(value).getPicture());
            String first = tokens.nextToken();
            int resourceId = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
            img_hinhanh.setImageResource(resourceId);
            dapan = listHinhAnh.get(value).getAnswertrue();
        } else {
            Toast.makeText(HinhAnhActivity.this, "Hết bài!", Toast.LENGTH_SHORT).show();
        }
    }
    private ArrayList<hinhanhdbModel> getData() {
        SQLiteContactController sql = new SQLiteContactController(this);
        return sql.getListHinhAnh();
    }
}
