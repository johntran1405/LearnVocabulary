package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class LuyenNgheActivity extends AppCompatActivity {
    LinearLayout itemBaiNghe;
    LinearLayout itemNhanDangTu;
    LinearLayout itemTroChoi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_nghe);
        itemBaiNghe = (LinearLayout) findViewById(R.id.itemBaiNghe);
        itemNhanDangTu = (LinearLayout) findViewById(R.id.itemNhanDangTu);
        itemTroChoi = (LinearLayout) findViewById(R.id.itemTroChoi);
        itemBaiNghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LuyenNgheActivity.this, BaiNgheActivity.class);
                startActivity(intent);
            }
        });
        itemNhanDangTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LuyenNgheActivity.this, NhanDangTuActivity.class);
                startActivity(intent);
            }
        });
        itemTroChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LuyenNgheActivity.this, NoiTuActivity.class);
                startActivity(intent);
            }
        });
    }
}
