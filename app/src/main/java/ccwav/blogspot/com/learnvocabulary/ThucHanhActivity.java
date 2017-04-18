package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ThucHanhActivity extends AppCompatActivity {
    LinearLayout itemMauCau;
    LinearLayout itemHinhAnh;
    LinearLayout itemChuDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_hanh);
        itemMauCau = (LinearLayout) findViewById(R.id.itemMauCau);
        itemHinhAnh = (LinearLayout) findViewById(R.id.itemHinhAnh);
        itemChuDe = (LinearLayout) findViewById(R.id.itemChuDe);
        itemMauCau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThucHanhActivity.this, MauCauActivity.class);
                startActivity(intent);
            }
        });
        itemHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThucHanhActivity.this, HinhAnhActivity.class);
                startActivity(intent);
            }
        });
        itemChuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThucHanhActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });
    }
}
