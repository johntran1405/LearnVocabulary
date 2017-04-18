package ccwav.blogspot.com.learnvocabulary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ccwav.blogspot.com.learnvocabulary.Database.SQLiteContactController;
import ccwav.blogspot.com.learnvocabulary.Database.SQLiteDataController;
import ccwav.blogspot.com.learnvocabulary.Model.trochoidbModel;

public class NoiTuActivity extends AppCompatActivity {
    final int currentDBVersion = 2;
    final String DBVERSION_Key = "DBVERSION_Key";
    ArrayList<trochoidbModel> listTroChoi;
    Button btword1, btword2, btword3, btword4, bt_tieptheo;
    ImageButton ibpicture1, ibpicture2, ibpicture3, ibpicture4;
    int value = 0;
    String clickbutton = "";
    String clickImg = "";
    private String a, b, c, d;
    private String first, second, three, four;
    int done = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_tu);
        initView();
        createDB();
        listTroChoi = getData();
        a = listTroChoi.get(0).getWord1();
        btword1.setText(a);
        btword1.setOnClickListener(OnClick);
        b = listTroChoi.get(0).getWord2();
        btword2.setText(b);
        btword2.setOnClickListener(OnClick);
        c = listTroChoi.get(0).getWord3();
        btword3.setText(c);
        btword3.setOnClickListener(OnClick);
        d = listTroChoi.get(0).getWord4();
        btword4.setText(d);
        btword4.setOnClickListener(OnClick);

        //lấy img từ sqlite
        StringTokenizer tokens1 = new StringTokenizer(listTroChoi.get(0).getPicture1());
        first = tokens1.nextToken();
        int resourceId1 = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
        ibpicture1.setBackgroundResource(resourceId1);
        ibpicture1.setOnClickListener(OnClick);
        StringTokenizer tokens2 = new StringTokenizer(listTroChoi.get(0).getPicture2());
        second = tokens2.nextToken();
        int resourceId2 = getResources().getIdentifier("@drawable/" + second, null, getPackageName());
        ibpicture2.setBackgroundResource(resourceId2);
        ibpicture2.setOnClickListener(OnClick);
        StringTokenizer tokens3 = new StringTokenizer(listTroChoi.get(0).getPicture3());
        three = tokens3.nextToken();
        int resourceId3 = getResources().getIdentifier("@drawable/" + three, null, getPackageName());
        ibpicture3.setBackgroundResource(resourceId3);
        ibpicture3.setOnClickListener(OnClick);
        StringTokenizer tokens4 = new StringTokenizer(listTroChoi.get(0).getPicture4());
        four = tokens4.nextToken();
        int resourceId4 = getResources().getIdentifier("@drawable/" + four, null, getPackageName());
        ibpicture4.setBackgroundResource(resourceId4);
        ibpicture4.setOnClickListener(OnClick);
        checkkiemtra();
    }
    View.OnClickListener OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btword1:
                    clickbutton = a;
                    break;
                case R.id.btword2:
                    clickbutton = b;
                    break;
                case R.id.btword3:
                    clickbutton = c;
                    break;
                case R.id.btword4:
                    clickbutton = d;
                    break;
                case R.id.ibpicture1:
                    clickImg = first;
                    if (clickbutton.toLowerCase().equals(clickImg.toLowerCase())) {
                        done++;
                        if (first.toLowerCase().equals(btword1.getText().toString().toLowerCase()))
                            btword1.setVisibility(View.GONE);
                        if (first.toLowerCase().equals(btword2.getText().toString().toLowerCase()))
                            btword2.setVisibility(View.GONE);
                        if (first.toLowerCase().equals(btword3.getText().toString().toLowerCase()))
                            btword3.setVisibility(View.GONE);
                        if (first.toLowerCase().equals(btword4.getText().toString().toLowerCase()))
                            btword4.setVisibility(View.GONE);
                        ibpicture1.setVisibility(View.GONE);
//                        Toast.makeText(TroChoiActivity.this, "đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        hienAnhvaButton();
                        Toast.makeText(NoiTuActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ibpicture2:
                    clickImg = second;

                    if (clickbutton.toLowerCase().equals(clickImg.toLowerCase())) {
                        done++;
                        if (second.toLowerCase().equals(btword1.getText().toString().toLowerCase()))
                            btword1.setVisibility(View.GONE);
                        if (second.toLowerCase().equals(btword2.getText().toString().toLowerCase()))
                            btword2.setVisibility(View.GONE);
                        if (second.toLowerCase().equals(btword3.getText().toString().toLowerCase()))
                            btword3.setVisibility(View.GONE);
                        if (second.toLowerCase().equals(btword4.getText().toString().toLowerCase()))
                            btword4.setVisibility(View.GONE);
                        ibpicture2.setVisibility(View.GONE);
//                        Toast.makeText(TroChoiActivity.this, "đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        hienAnhvaButton();
                        Toast.makeText(NoiTuActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ibpicture3:
                    clickImg = three;
                    if (clickbutton.toLowerCase().equals(clickImg.toLowerCase())) {
                        done++;
                        if (three.toLowerCase().equals(btword1.getText().toString().toLowerCase()))
                            btword1.setVisibility(View.GONE);
                        if (three.toLowerCase().equals(btword2.getText().toString().toLowerCase()))
                            btword2.setVisibility(View.GONE);
                        if (three.toLowerCase().equals(btword3.getText().toString().toLowerCase()))
                            btword3.setVisibility(View.GONE);
                        if (three.toLowerCase().equals(btword4.getText().toString().toLowerCase()))
                            btword4.setVisibility(View.GONE);
                        ibpicture3.setVisibility(View.GONE);
//                        Toast.makeText(TroChoiActivity.this, "đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        hienAnhvaButton();
                        Toast.makeText(NoiTuActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ibpicture4:
                    clickImg = four;
                    if (clickbutton.toLowerCase().equals(clickImg.toLowerCase())) {
                        done++;
                        if (four.toLowerCase().equals(btword1.getText().toString().toLowerCase()))
                            btword1.setVisibility(View.GONE);
                        if (four.toLowerCase().equals(btword2.getText().toString().toLowerCase()))
                            btword2.setVisibility(View.GONE);
                        if (four.toLowerCase().equals(btword3.getText().toString().toLowerCase()))
                            btword3.setVisibility(View.GONE);
                        if (four.toLowerCase().equals(btword4.getText().toString().toLowerCase()))
                            btword4.setVisibility(View.GONE);
                        ibpicture4.setVisibility(View.GONE);
//                        Toast.makeText(TroChoiActivity.this, "đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        hienAnhvaButton();
                        Toast.makeText(NoiTuActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private void hienAnhvaButton() {
        done = 0;
        ibpicture1.setVisibility(View.VISIBLE);
        ibpicture2.setVisibility(View.VISIBLE);
        ibpicture3.setVisibility(View.VISIBLE);
        ibpicture4.setVisibility(View.VISIBLE);
        btword1.setVisibility(View.VISIBLE);
        btword2.setVisibility(View.VISIBLE);
        btword3.setVisibility(View.VISIBLE);
        btword4.setVisibility(View.VISIBLE);
    }
    private void checkkiemtra() {

    }

    private void initView() {
        btword1 = (Button) findViewById(R.id.btword1);
        btword2 = (Button) findViewById(R.id.btword2);
        btword3 = (Button) findViewById(R.id.btword3);
        btword4 = (Button) findViewById(R.id.btword4);
        bt_tieptheo = (Button) findViewById(R.id.bt_tieptheo);
        ibpicture1 = (ImageButton) findViewById(R.id.ibpicture1);
        ibpicture2 = (ImageButton) findViewById(R.id.ibpicture2);
        ibpicture3 = (ImageButton) findViewById(R.id.ibpicture3);
        ibpicture4 = (ImageButton) findViewById(R.id.ibpicture4);

    }

    public void nextBai(View v) {
        if (done < 4) {
            Toast.makeText(NoiTuActivity.this, "Chưa hoàn thành bài !", Toast.LENGTH_SHORT).show();
        } else {
            hienAnhvaButton();
            value++;
            if (value < listTroChoi.size()) {
                a = listTroChoi.get(value).getWord1();
                btword1.setText(a);
                b = listTroChoi.get(value).getWord2();
                btword2.setText(b);
                c = listTroChoi.get(value).getWord3();
                btword3.setText(c);
                d = listTroChoi.get(value).getWord4();
                btword4.setText(d);

                //lấy img từ sqlite
                StringTokenizer tokens1 = new StringTokenizer(listTroChoi.get(value).getPicture1());
                first = tokens1.nextToken();
                int resourceId1 = getResources().getIdentifier("@drawable/" + first, null, getPackageName());
                ibpicture1.setBackgroundResource(resourceId1);
                StringTokenizer tokens2 = new StringTokenizer(listTroChoi.get(value).getPicture2());
                second = tokens2.nextToken();
                int resourceId2 = getResources().getIdentifier("@drawable/" + second, null, getPackageName());
                ibpicture2.setBackgroundResource(resourceId2);
                StringTokenizer tokens3 = new StringTokenizer(listTroChoi.get(value).getPicture3());
                three = tokens3.nextToken();
                int resourceId3 = getResources().getIdentifier("@drawable/" + three, null, getPackageName());
                ibpicture3.setBackgroundResource(resourceId3);
                StringTokenizer tokens4 = new StringTokenizer(listTroChoi.get(value).getPicture4());
                four = tokens4.nextToken();
                int resourceId4 = getResources().getIdentifier("@drawable/" + four, null, getPackageName());
                ibpicture4.setBackgroundResource(resourceId4);
            } else {
                Toast.makeText(NoiTuActivity.this, "Hết bài!", Toast.LENGTH_SHORT).show();
            }
        }
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

    private ArrayList<trochoidbModel> getData() {
        SQLiteContactController sql = new SQLiteContactController(this);
        return sql.getListTroChoi();
    }
}
