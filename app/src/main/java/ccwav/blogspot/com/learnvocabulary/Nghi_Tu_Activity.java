package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by quang on 04/17/2017.
 */

public class Nghi_Tu_Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView txtTu, txtYNgia;
    ImageButton btnLoa2;

    TextToSpeech tts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nghia_tu_activity);
        addControl();
        addEvent();

    }
    private void addEvent() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("TTBH");

        txtTu.setText((CharSequence) bundle.getSerializable("TU"));
        txtYNgia.setText((CharSequence) bundle.getSerializable("YNGHIA"));
        btnLoa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulynghe2(txtTu, btnLoa2);
            }
        });

    }

    private void xulynghe2(TextView txtTu, ImageButton btnLoa2) {
        tts.speak(txtTu.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);

    }

    private void addControl() {
        tts= new TextToSpeech(this,this);
        txtTu = (TextView) findViewById(R.id.txtTu);
        txtYNgia = (TextView) findViewById(R.id.txtYNghia);
        btnLoa2= (ImageButton) findViewById(R.id.btnnghe2);
    }

    @Override
    public void onInit(int status) {

        if(status != TextToSpeech.ERROR)
        {
            tts.setLanguage(Locale.UK);
        }
    }
}
