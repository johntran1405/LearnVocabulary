package ccwav.blogspot.com.learnvocabulary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Thông báo !")
                .setMessage("Bạn có muốn thoát ?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
