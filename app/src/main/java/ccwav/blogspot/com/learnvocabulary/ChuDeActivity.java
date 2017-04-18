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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import ccwav.blogspot.com.learnvocabulary.Model.chudedbModel;

public class ChuDeActivity extends AppCompatActivity {
    final int currentDBVersion = 2;
    final String DBVERSION_Key = "DBVERSION_Key";
    ArrayList<chudedbModel> listChuDe;
    static int REQ_CODE = 1;
    List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
    SimpleAdapter adapter;
    TextView txtname;
    CheckBox cb_answer1,cb_answer2,cb_answer3,cb_answer4,cb_answer5,cb_answer6;
    Button bt_tieptheo, bt_kiemtra;
    int value=0;
    ArrayList<String> List_DapAn = new ArrayList<String>();
    ArrayList<String> List_Chon = new ArrayList<String>();
    ArrayList<String> List_Trung = new ArrayList<String>();
    boolean check_chon_dung = false;
    boolean check_noi_dung = false;
    TextView tv_ketqua;

    boolean check_added = false;
    String text_read;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_de);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) displayError();
        setContentView(R.layout.activity_chu_de);
        initView();
        createDB();
        bt_tieptheo.setEnabled(false);

        adapter = new SimpleAdapter(this, listData, android.R.layout.simple_list_item_2,
                new String[] {"text", "score"},
                new int[] {android.R.id.text1, android.R.id.text2});
        ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(adapter);
        listChuDe = getData();



        String name = listChuDe.get(0).getName();
        txtname.setText(name);

        String a= listChuDe.get(0).getPicture1();
        cb_answer1.setText(a);
        String b= listChuDe.get(0).getPicture2();
        cb_answer2.setText(b);
        String c= listChuDe.get(0).getPicture3();
        cb_answer3.setText(c);
        String d= listChuDe.get(0).getPicture4();
        cb_answer4.setText(d);
        String e= listChuDe.get(0).getPicture5();
        cb_answer5.setText(e);
        String f= listChuDe.get(0).getPicture6();
        cb_answer6.setText(f);
        t = listChuDe.get(0).getAnswertrue();



        //lấy img từ sqlite
        StringTokenizer tokens1 = new StringTokenizer(listChuDe.get(0).getPicture1());
        String first = tokens1.nextToken();
        int resourceId1 = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
        cb_answer1.setBackgroundResource(resourceId1);
        StringTokenizer tokens2 = new StringTokenizer(listChuDe.get(0).getPicture2());
        String second = tokens2.nextToken();
        int resourceId2 = getResources().getIdentifier("@drawable/" + second, null, getPackageName());
        cb_answer2.setBackgroundResource(resourceId2);
        StringTokenizer tokens3 = new StringTokenizer(listChuDe.get(0).getPicture3());
        String three = tokens3.nextToken();
        int resourceId3 = getResources().getIdentifier("@drawable/" + three, null, getPackageName());
        cb_answer3.setBackgroundResource(resourceId3);
        StringTokenizer tokens4 = new StringTokenizer(listChuDe.get(0).getPicture4());
        String four = tokens4.nextToken();
        int resourceId4 = getResources().getIdentifier("@drawable/" + four, null, getPackageName());
        cb_answer4.setBackgroundResource(resourceId4);
        StringTokenizer tokens5 = new StringTokenizer(listChuDe.get(0).getPicture5());
        String five = tokens5.nextToken();
        int resourceId5 = getResources().getIdentifier("@drawable/" + five, null, getPackageName());
        cb_answer5.setBackgroundResource(resourceId5);
        StringTokenizer tokens6 = new StringTokenizer(listChuDe.get(0).getPicture6());
        String six = tokens6.nextToken();
        int resourceId6 = getResources().getIdentifier("@drawable/" + six, null, getPackageName());
        cb_answer6.setBackgroundResource(resourceId6);

        ThayDoiCheckBox();
        ThemListDapAnDung();
        ThucHienKiemTra();
    }
    private void ThucHienKiemTra() {
        bt_kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      List_Chon.clear();
                if (!check_added){
                    ThucHienAdd_Again();
                }
                for (int i=0;i< List_DapAn.size();i++){
                    if (List_Chon.contains(List_DapAn.get(i))){
                        List_Trung.add(List_DapAn.get(i));
                    }
                }
                if (List_Trung.size() == 2 && List_Chon.size() ==2){
                    check_chon_dung = true;
                }else {
                    check_chon_dung = false;
                }


                if (text_read != null){
                    bt_tieptheo.setEnabled(true);
                    if (text_read.toLowerCase().equals(t.toLowerCase())){
                        check_noi_dung = true;
                    } else {
                        check_noi_dung = false;
                    }
                    if(check_noi_dung){
                        if (check_chon_dung){
                            tv_ketqua.setText("Chọn đúng và phát âm đúng");
                        }else {
                            tv_ketqua.setText("Chọn sai và phát âm đúng");
                        }
                    }else {
                        if (check_chon_dung){
                            tv_ketqua.setText("Chọn đúng và phát âm sai");
                        }else {
                            tv_ketqua.setText("Chọn sai và phát âm sai");
                        }
                    }
                    bt_kiemtra.setEnabled(false);
                } else {
                    Toast.makeText(getBaseContext(), "Bạn chưa phát âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void ThucHienAdd_Again() {
        if (cb_answer1.isChecked()){
            String a = cb_answer1.getText().toString();
            List_Chon.add(a);
        }
        if (cb_answer2.isChecked()){
            String a = cb_answer2.getText().toString();
            List_Chon.add(a);
        }
        if (cb_answer3.isChecked()){
            String a = cb_answer3.getText().toString();
            List_Chon.add(a);
        }
        if (cb_answer4.isChecked()){
            String a = cb_answer4.getText().toString();
            List_Chon.add(a);
        }
        if (cb_answer5.isChecked()){
            String a = cb_answer5.getText().toString();
            List_Chon.add(a);
        }
        if (cb_answer6.isChecked()){
            String a = cb_answer6.getText().toString();
            List_Chon.add(a);
        }
    }

    private void ThemListDapAnDung() {
        String g = listChuDe.get(0).getPicturetrue1();
        List_DapAn.add(g);
        String h = listChuDe.get(0).getPicturetrue2();
        List_DapAn.add(h);

    }
    private void ThayDoiCheckBox() {
        cb_answer1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check_added = true;
                    String a = cb_answer1.getText().toString();
                    List_Chon.add(a);
                } else {
                    check_added = false;
                    List_Chon.clear();
                }
            }
        });

        cb_answer2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check_added = true;
                    String a = cb_answer2.getText().toString();
                    List_Chon.add(a);
                } else {
                    check_added = false;
                    List_Chon.clear();
                }
            }
        });

        cb_answer3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check_added = true;
                    String a = cb_answer3.getText().toString();
                    List_Chon.add(a);
                } else {
                    check_added = false;
                    List_Chon.clear();
                }
            }
        });

        cb_answer4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check_added = true;
                    String a = cb_answer4.getText().toString();
                    List_Chon.add(a);
                } else {
                    check_added = false;
                    List_Chon.clear();
                }
            }
        });


        cb_answer5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check_added = true;
                    String a = cb_answer5.getText().toString();
                    List_Chon.add(a);
                } else {
                    check_added = false;
                    List_Chon.clear();
                }
            }
        });

        cb_answer6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check_added = true;
                    String a = cb_answer6.getText().toString();
                    List_Chon.add(a);
                } else {
                    check_added = false;
                    List_Chon.clear();
                }
            }
        });
    }
    private void initView() {
        txtname = (TextView) findViewById(R.id.txtname);
        cb_answer1 = (CheckBox) findViewById(R.id.cb_answer1);
        cb_answer2 = (CheckBox) findViewById(R.id.cb_answer2);
        cb_answer3 = (CheckBox) findViewById(R.id.cb_answer3);
        cb_answer4 = (CheckBox) findViewById(R.id.cb_answer4);
        cb_answer5 = (CheckBox) findViewById(R.id.cb_answer5);
        cb_answer6 = (CheckBox) findViewById(R.id.cb_answer6);
        bt_tieptheo = (Button) findViewById(R.id.bt_tieptheo);
        bt_kiemtra =(Button)findViewById(R.id.bt_kiemtra);
        tv_ketqua = (TextView)findViewById(R.id.tv_ketqua);
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
                    public void onClick(DialogInterface dialog, int which) { System.exit(0); }
                }).show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                float[] score = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
                listData.clear();
                Map<String, String> item = new HashMap<String, String>();
                item.put("text", text.get(0));
//                    item.put("score", extractScore(score[0]));
                listData.add(item);
                adapter.notifyDataSetChanged();
                text_read = text.get(0);
            }
        }
    }
    public void nextBai(View v) {
        bt_tieptheo.setEnabled(false);
        cb_answer1.setChecked(false);
        cb_answer2.setChecked(false);
        cb_answer3.setChecked(false);
        cb_answer4.setChecked(false);
        cb_answer5.setChecked(false);
        cb_answer6.setChecked(false);
        bt_kiemtra.setEnabled(true);
        List_Trung.clear();
        listData.clear();
        adapter.notifyDataSetChanged();
        text_read = null;
        tv_ketqua.setText("");
        value++;
        if (value < listChuDe.size()) {
            String name = listChuDe.get(value).getName();
            txtname.setText(name);
            String a= listChuDe.get(value).getPicture1();
            cb_answer1.setText(a);
            String b= listChuDe.get(value).getPicture2();
            cb_answer2.setText(b);
            String c= listChuDe.get(value).getPicture3();
            cb_answer3.setText(c);
            String d= listChuDe.get(value).getPicture4();
            cb_answer4.setText(d);
            String e= listChuDe.get(value).getPicture5();
            cb_answer5.setText(e);
            String f= listChuDe.get(value).getPicture6();
            cb_answer6.setText(f);

            String g = listChuDe.get(value).getPicturetrue1();
            List_DapAn.add(g);
            String h = listChuDe.get(value).getPicturetrue2();
            List_DapAn.add(h);
            t = listChuDe.get(value).getAnswertrue();

            //lấy img từ sqlite
            StringTokenizer tokens1 = new StringTokenizer(listChuDe.get(value).getPicture1());
            String first = tokens1.nextToken();
            int resourceId1 = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
            cb_answer1.setBackgroundResource(resourceId1);

            StringTokenizer tokens2 = new StringTokenizer(listChuDe.get(value).getPicture2());
            String second = tokens2.nextToken();
            int resourceId2 = getResources().getIdentifier("@drawable/" + second, null, getPackageName());
            cb_answer2.setBackgroundResource(resourceId2);

            StringTokenizer tokens3 = new StringTokenizer(listChuDe.get(value).getPicture3());
            String three = tokens3.nextToken();
            int resourceId3 = getResources().getIdentifier("@drawable/" + three, null, getPackageName());
            cb_answer3.setBackgroundResource(resourceId3);

            StringTokenizer tokens4 = new StringTokenizer(listChuDe.get(value).getPicture4());
            String four = tokens4.nextToken();
            int resourceId4 = getResources().getIdentifier("@drawable/" + four, null, getPackageName());
            cb_answer4.setBackgroundResource(resourceId4);

            StringTokenizer tokens5 = new StringTokenizer(listChuDe.get(value).getPicture5());
            String five = tokens5.nextToken();
            int resourceId5 = getResources().getIdentifier("@drawable/" + five, null, getPackageName());
            cb_answer5.setBackgroundResource(resourceId5);

            StringTokenizer tokens6 = new StringTokenizer(listChuDe.get(value).getPicture6());
            String six = tokens6.nextToken();
            int resourceId6 = getResources().getIdentifier("@drawable/" + six, null, getPackageName());
            cb_answer6.setBackgroundResource(resourceId6);
        } else {
            Toast.makeText(ChuDeActivity.this, "Hết bài!", Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<chudedbModel> getData() {
        SQLiteContactController sql = new SQLiteContactController(this);
        return sql.getListChuDe();
    }
}
