package ccwav.blogspot.com.learnvocabulary;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ccwav.blogspot.com.learnvocabulary.Common.DialogEx;
import ccwav.blogspot.com.learnvocabulary.Database.WritetestSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.QuestionModel;

public class LuyenVietActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView txtcau;
    Button btnloa, btntt;
    EditText editnhap;
    WritetestSQLite writetestSQLite;
    ArrayList<QuestionModel> listquest;
    int value=0;
    TextToSpeech finalMTts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_viet);
        addControl();
        loadQuestion();
        addEvent();
        finalMTts= new TextToSpeech(this.getApplicationContext(),this);


    }
    private boolean checkdapan()
    {
        String s=editnhap.getText().toString();
        String a=listquest.get(value).getAns();
        if(s.equalsIgnoreCase(a))
        {
            return true;
        }
        return false;
    }

    private void addEvent() {
            btntt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (value <= listquest.size()) {
                        if(checkdapan())
                        {
                            value++;
                            editnhap.setText("");
                            loadQuestion();
                        }else
                        {
                            DialogEx.show(LuyenVietActivity.this, "Thông báo", "Bạn trả lời sai !!");
                        }

                    } else {
                        DialogEx.show(LuyenVietActivity.this, "Xin Chúc Mừng", "Bạn đã hoàn thành !!");
                    }

                }
            });
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
        txtcau.setText(listquest.get(value).getQuest());
        final String ms= listquest.get(value).getAns();
        btnloa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalMTts.speak(ms, TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            finalMTts.setLanguage(Locale.US);
        } else {
            // Initialization failed.
            Log.e("app", "Could not initialize TextToSpeech.");
        }
    }
    @Override
    protected void onDestroy() {
        if (finalMTts != null)
        {
            finalMTts.stop();
            finalMTts.shutdown();
        }
        super.onDestroy();
    }
}
