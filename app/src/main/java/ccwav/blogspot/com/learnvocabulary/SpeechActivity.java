package ccwav.blogspot.com.learnvocabulary;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class SpeechActivity extends AppCompatActivity {

   // @Override
    Button btnHome;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_layout_m);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
