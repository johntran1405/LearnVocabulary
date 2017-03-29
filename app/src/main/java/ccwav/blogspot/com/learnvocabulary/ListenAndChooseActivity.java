package ccwav.blogspot.com.learnvocabulary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.*;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ListenAndChooseActivity extends AppCompatActivity {

   // @Override
    ImageButton imageBTN1,imageBTN2,imageBTN3,imageBTN4;
    TextView txtNewWord;
    Button btnSpeak, btnNext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listenchoose_layout_m);

        addControl();
        addEvent();

    }

    private void addControl() {
        imageBTN1 = (ImageButton) findViewById(R.id.imageBTN1);
        imageBTN2 = (ImageButton) findViewById(R.id.imageBTN2);
        imageBTN3 = (ImageButton) findViewById(R.id.imageBTN3);
        imageBTN4 = (ImageButton) findViewById(R.id.imageBTN4);
        txtNewWord = (TextView) findViewById(R.id.txtNewWord);
        btnSpeak = (Button) findViewById(R.id.btnSoundSpeak);
        btnNext = (Button) findViewById(R.id.btnNext);
    }
    private void addEvent() {

    }
}
