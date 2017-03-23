package ccwav.blogspot.com.learnvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ccwav.blogspot.com.learnvocabulary.Database.CategorySQLite;
import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class MainActivity extends AppCompatActivity {

    //@Override


    private Button btnHome,btnPeople,btnFruit,btnAnimal,btnSport,btnTravel,btnVegetable,btnFood;
//    RelativeLayout R1, R2, R3, R4, R5, R6, R7, R8;
    private Animation scale1,scale2,scale3,scale4,scale5,scale6,scale7,scale8;
    private final int TIME_DELAY_SCALE_BTN = 700;  // time delay for topic  button
    private final int TIME_DELAY_SCALE_TV = 10;     // time delay textview learnEnglish
    private TextView tv_learnEnglish;
    CategorySQLite catedb;
    WordsSQLite wordsdb;
    List<Categories_Model> listcate= new ArrayList<>();
    List<Words_Model> listwords= new ArrayList<>();
    int index=0,cate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();
        catedb= new CategorySQLite(this);
        wordsdb= new WordsSQLite(this);
        listwords=wordsdb.getAllWords();
        listcate = catedb.getAllCategory();
        getSupportActionBar().hide();
        addControl();
        addEvent();



    }
    private void createDB() {
// khởi tạo database
        MyDatabase sql = new MyDatabase(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addControl() {
//        int ImageId1=this.getResources().getIdentifier(listcate.get(0).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId2=this.getResources().getIdentifier(listcate.get(1).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId3=this.getResources().getIdentifier(listcate.get(2).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId4=this.getResources().getIdentifier(listcate.get(3).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId5=this.getResources().getIdentifier(listcate.get(4).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId6=this.getResources().getIdentifier(listcate.get(5).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId7=this.getResources().getIdentifier(listcate.get(6).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        int ImageId8=this.getResources().getIdentifier(listcate.get(7).getCategori_Icon().toLowerCase(),"drawable",getPackageName());
//        Log.d("hinh", String.valueOf(ImageId2));
        btnHome = (Button) findViewById(R.id.btn_home);
        //btnHome.setBackgroundResource(ImageId1);
        btnPeople = (Button) findViewById(R.id.btn_people);
        //btnPeople.setBackgroundResource(ImageId2);
        btnAnimal = (Button) findViewById(R.id.btn_animal);
        //btnAnimal.setBackgroundResource(ImageId3);
        btnFruit = (Button) findViewById(R.id.btn_fruit);
        //btnFruit.setBackgroundResource(ImageId4);
        btnSport = (Button) findViewById(R.id.btn_sport);
        //btnSport.setBackgroundResource(ImageId5);
        btnTravel = (Button) findViewById(R.id.btn_travel);
        //btnTravel.setBackgroundResource(ImageId6);
        btnVegetable = (Button) findViewById(R.id.btn_vegetable);
        //btnVegetable.setBackgroundResource(ImageId7);
        btnFood = (Button) findViewById(R.id.btn_food);
        //btnFood.setBackgroundResource(ImageId8);


      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animation for textview learn english
        scale1 = AnimationUtils.loadAnimation(this,R.anim.scale);
        //Animation for all topic button
        scale2 = AnimationUtils.loadAnimation(this,R.anim.scale);
//        scale3 = AnimationUtils.loadAnimation(this,R.anim.scale);
//        scale4 = AnimationUtils.loadAnimation(this,R.anim.scale);
//        scale5 = AnimationUtils.loadAnimation(this,R.anim.scale);
//        scale6 = AnimationUtils.loadAnimation(this,R.anim.scale);
//        scale7 = AnimationUtils.loadAnimation(this,R.anim.scale);
//        scale8 = AnimationUtils.loadAnimation(this,R.anim.scale);

//
//        R1 = (RelativeLayout) findViewById(R.id.R1); // R = RelativeLayout,
//        R2 = (RelativeLayout) findViewById(R.id.R2);
//        R3 = (RelativeLayout) findViewById(R.id.R3);
//        R4 = (RelativeLayout) findViewById(R.id.R4);
//        R5 = (RelativeLayout) findViewById(R.id.R5);
//        R6 = (RelativeLayout) findViewById(R.id.R6);
//        R7 = (RelativeLayout) findViewById(R.id.R7);
//        R8 = (RelativeLayout) findViewById(R.id.R8);

        tv_learnEnglish = (TextView) findViewById(R.id.tv_learnenglish);

    }

    private void addEvent() {
        // Animation Time delay textview Learn English
        boolean btndelay1 = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_learnEnglish.startAnimation(scale1);

            }
        },TIME_DELAY_SCALE_TV);

        // Animation Time delay for topic button
        boolean btndelay3 = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnAnimal.startAnimation(scale2);
                btnFood.startAnimation(scale2);
                btnFruit.startAnimation(scale2);
                btnHome.startAnimation(scale2);
                btnPeople.startAnimation(scale2);
                btnSport.startAnimation(scale2);
                btnTravel.startAnimation(scale2);
                btnVegetable.startAnimation(scale2);

            }
        },TIME_DELAY_SCALE_BTN);
    }

    public void onTouch(View v) {
        //get Animation scale
        final Animation anim_home = AnimationUtils.loadAnimation(this, R.anim.scale);
        // set animation scale when the user touch one in all topic button
        if (v.getId() == R.id.R1 || v.getId() == R.id.btn_home) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

        }else if (v.getId() == R.id.R3 || v.getId() == R.id.btn_people) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.R5 || v.getId() == R.id.btn_fruit) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.R7 || v.getId() == R.id.btn_animal) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.R2 || v.getId() == R.id.btn_sport) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.R4 || v.getId() == R.id.btn_travel) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.R6 || v.getId() == R.id.btn_vegetable) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.R8 || v.getId() == R.id.btn_food ) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }
}
