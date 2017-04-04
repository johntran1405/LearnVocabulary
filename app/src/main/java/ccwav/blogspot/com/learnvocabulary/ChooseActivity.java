package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class ChooseActivity extends AppCompatActivity {
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;

    static List<Words_Model> listword= new ArrayList<>();
    static int NUM_ITEMS =0;
    ImageView imageView;
    Button btnA,btnB,btnC,btnD;
    ArrayList<String> word = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout_m);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");
        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);

        addControl();
        addEvent();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addEvent() {
        Random rd = new Random();
        String img=listword.get(0).getImage();
        imageView.setBackground(getImage(img));
        String A=listword.get(0).getEnglish();
        String B=listword.get(1).getEnglish();
        String C=listword.get(2).getEnglish();
        String D=listword.get(3).getEnglish();
        word.add(A);
        word.add(B);
        word.add(C);
        word.add(D);
        int i;
        String text = null;
        for (i=0;i<word.size();i++)
        {
            text=word.get(rd.nextInt(word.size()));
            word.remove(word.get(i));
        }

        btnA.setText(text);
        btnB.setText(text);
        btnC.setText(text);
        btnD.setText(text);

    }

    private void addControl() {
        imageView= (ImageView) findViewById(R.id.imagecate);
        btnA= (Button) findViewById(R.id.dapan1);
        btnB= (Button) findViewById(R.id.dapan2);
        btnC= (Button) findViewById(R.id.dapan3);
        btnD= (Button) findViewById(R.id.dapan4);

    }
    private Drawable getImage(String img)
    {
        int imgResId = getResources().getIdentifier(String.valueOf(img), "drawable",getApplicationContext().getPackageName());
        Log.d("Hinh","TenHinh"+ imgResId);
        Drawable dr;
        if(imgResId!=0) {
            dr = getApplicationContext().getResources().getDrawable(imgResId);
        }else
        {
            dr = getApplicationContext().getResources().getDrawable(R.drawable.home);
        }
        return dr;
    }
}
