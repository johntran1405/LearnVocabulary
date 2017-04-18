package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ccwav.blogspot.com.learnvocabulary.Common.DialogEx;

public class MainChinhActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnTudien, btnBaihoc, btnTest, btnThongtin,btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chinh);
        addControl();
        addEvent();

    }

    private void addEvent() {
        btnTudien.setOnClickListener(this);
        btnBaihoc.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }

    private void addControl() {
        btnTudien = (Button) findViewById(R.id.btnTuDien);
        btnBaihoc= (Button) findViewById(R.id.btnBaiHoc);
        btnTest= (Button) findViewById(R.id.btnTest);
        btnThongtin= (Button) findViewById(R.id.btnthongtin);
        btnInfo = (Button) findViewById(R.id.btnInfo);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnTuDien:
                Intent i = new Intent(MainChinhActivity.this, TuDienActivity.class);
                startActivity(i);
                break;
            case R.id.btnBaiHoc:
                Intent i2 = new Intent(MainChinhActivity.this, Main.class);
                startActivity(i2);
                break;
            case R.id.btnInfo:
                DialogEx.show(this,"Info","Thành viên nhóm : \n Tuyến Trần \n Việt Nguyễn \n Sang Võ \n Tú Tạ");
            case R.id.btnTest:
                Intent i3 = new Intent(MainChinhActivity.this, TestActivity.class);
                startActivity(i3);
            case R.id.btnthongtin:

        }
    }
}
