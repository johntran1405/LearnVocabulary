package ccwav.blogspot.com.learnvocabulary;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
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

public class SpeechActivity extends AppCompatActivity {

   // @Override

    ImageButton IbtnMicro; // Image Button Micro
    TextView txt_wordRecord, // text view show up the word when user speech to micro
            txt_newWord; // text view show up random new word from database
    TextView txt_Message; // text view hiển thị trạng thái đúng sai của từ nhập vào
    Button btnNext;
    private final int REQ_CODE_SPEECH_INPUT = 1;
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;
    List<Words_Model> listword= new ArrayList<>();
    int sizearr= listword.size();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_layout_m);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");

        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);

        addControl();
        addEvent();


    }

    private void addControl() {
        IbtnMicro = (ImageButton) findViewById(R.id.imBtn_Speech);
        txt_newWord = (TextView) findViewById(R.id.txtNewWord);
        txt_wordRecord = (TextView) findViewById(R.id.txtwordRecord);
        btnNext = (Button) findViewById(R.id.btnNext);
        txt_Message = (TextView) findViewById(R.id.txtMessage);
    }

    private void addEvent() {
        IbtnMicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeechInput();
            }
        });
//        getNewWord();
        txt_newWord.setText(listword.get(sizearr).getEnglish());
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show();
                txt_newWord.setText(listword.get(sizearr).getEnglish());
                txt_wordRecord.setText("");
                btnNext.setVisibility(View.INVISIBLE);
            }
        });


    }
    public void SpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
//                getString(R.string.speech_prompt));
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
                    if(txt_wordRecord.getText().equals(txt_newWord.getText())){

                       // Toast.makeText(getApplicationContext(),"True"+txt_wordRecord.getText(),Toast.LENGTH_LONG).show();
                        btnNext.setVisibility(View.VISIBLE);
                        txt_Message.setText("Bạn Giỏi Quá !");
                    }
                    else {
                        txt_wordRecord.getText();
                          //Toast.makeText(getApplicationContext(),"False"+txt_wordRecord.getText(),Toast.LENGTH_LONG).show();
                        txt_Message.setText("Bạn Làm Sai Rồi !");
                    }


                    }
                break;
            }
        }
    }
//    public String getNewWord(){
//        for(int i = 0; i<listword.size();i++){
//            txt_newWord.setText(listword.get(i).getEnglish());
//            }
//             //  txt_wordRecord.getText();
//        return txt_newWord.toString();
 //   }



}
