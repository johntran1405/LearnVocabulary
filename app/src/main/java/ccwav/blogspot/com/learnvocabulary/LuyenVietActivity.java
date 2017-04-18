package ccwav.blogspot.com.learnvocabulary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.Database.WritetestSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.QuestionModel;

public class LuyenVietActivity extends AppCompatActivity {
    TextView txtcau;
    Button btnloa, btntt;
    EditText editnhap;
    WritetestSQLite writetestSQLite;
    ArrayList<QuestionModel> listquest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_viet);
        addControl();
    }

    private void addControl() {
        txtcau= (TextView) findViewById(R.id.txtcauhoi);
        btnloa= (Button) findViewById(R.id.buttonloa);
        btntt= (Button) findViewById(R.id.buttontt);
        editnhap= (EditText) findViewById(R.id.editdientu);
    }
    private void loadQuestion() {
        writetestSQLite = new WritetestSQLite(this);
        listquest = writetestSQLite.getAllQuestion();

    }
}
