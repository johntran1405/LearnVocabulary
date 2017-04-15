package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainChinhActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnTudien, btnBaihoc, btnTest, btnThongtin;

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
    }

    private void addControl() {
        btnTudien = (Button) findViewById(R.id.btnTuDien);
        btnBaihoc= (Button) findViewById(R.id.btnBaiHoc);
        btnTest= (Button) findViewById(R.id.btnTest);
        btnThongtin= (Button) findViewById(R.id.btnthongtin);

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
            case R.id.btnTest:
            case R.id.btnthongtin:
        }
    }
}
