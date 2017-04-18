package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class TestActivity extends AppCompatActivity {
    LinearLayout itemLuyenNghe;
    LinearLayout itemThucHanh;
    LinearLayout itemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        itemLuyenNghe = (LinearLayout) findViewById(R.id.itemLuyenNghe);
        itemThucHanh = (LinearLayout) findViewById(R.id.itemThucHanh);
        itemInfo = (LinearLayout) findViewById(R.id.itemInfo);

        itemLuyenNghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, LuyenNgheActivity.class);
                startActivity(intent);
            }
        });
        itemThucHanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, ThucHanhActivity.class);
                startActivity(intent);
            }
        });
        itemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, LuyenVietActivity.class);
                startActivity(intent);
            }
        });
    }

}
