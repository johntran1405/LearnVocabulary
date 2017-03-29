package ccwav.blogspot.com.learnvocabulary;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class SpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

   // @Override

    ImageButton IbtnMicro; // Image Button Micro
    TextView txt_wordRecord, // text view show up the word when user speech to micro
            txt_newWord; // text view show up random new word from database
    TextView txt_Message, // text view hiển thị trạng thái đúng sai của từ nhập vào
                txt_Mean; // text view show up mean that word
    Button btnNext,btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 1;
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;
    List<Words_Model> listword= new ArrayList<>();
    int sizearr = 0;
    int i=0;
    TextToSpeech finalMTts = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_layout_m);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");

        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);
        finalMTts= new TextToSpeech(this.getApplicationContext(),this);

        addControl();
        addEvent();
    }

    private void addControl() {
        IbtnMicro = (ImageButton) findViewById(R.id.imBtn_Speech);
        txt_newWord = (TextView) findViewById(R.id.txtNewWord);
        txt_wordRecord = (TextView) findViewById(R.id.txtwordRecord);
        btnNext = (Button) findViewById(R.id.btnNext);
        txt_Message = (TextView) findViewById(R.id.txtMessage);
        btnSpeak = (Button) findViewById(R.id.btnSoundSpeak);
        txt_Mean = (TextView) findViewById(R.id.txtMean);

    }
    private void addEvent() {
        IbtnMicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeechInput();
            }
        });
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalMTts.speak(txt_newWord.getText().toString(), TextToSpeech.QUEUE_FLUSH,null);
            }
        });
//        getNewWord();
        txt_newWord.setText(listword.get(sizearr).getEnglish());
        sizearr=listword.size();
//        Log.d("aaaa","id: "+sizearr);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show();
                i++;
                getword(i);
                Log.d("aaaa","id: "+sizearr);
                txt_wordRecord.setText("");
                btnNext.setVisibility(View.INVISIBLE);
                txt_Message.setText("Tap on mic to speak");
                txt_Mean.setText("");

            }
        });
    }
    public void SpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try{
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
//            getNewWord();
        }catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }

    }
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txt_wordRecord.setText(result.get(0));
                    if(txt_wordRecord.getText().toString().equalsIgnoreCase(txt_newWord.getText().toString())){
                       // Toast.makeText(getApplicationContext(),"True"+txt_wordRecord.getText(),Toast.LENGTH_LONG).show();
                        btnNext.setVisibility(View.VISIBLE);
                        txt_Message.setText("Bạn Giỏi Quá !");
                        txt_Mean.setText(listword.get(i).getVietnamese());
                    }
                    else {
                        txt_wordRecord.getText();
//                          //Toast.makeText(getApplicationContext(),"False"+txt_wordRecord.getText(),Toast.LENGTH_LONG).show();
                        txt_Message.setText("Bạn Đọc Sai Rồi !");
                    }
                    }
                break;
            }
        }
    }
    public String getword(int f){
        String text = listword.get(f).getEnglish();
        txt_newWord.setText(text);
        return  txt_newWord.toString();
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
