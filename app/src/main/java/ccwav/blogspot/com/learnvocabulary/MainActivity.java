package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private TextView txthome,txtpeople,txtfruit,txtanimal,txtsport,txttravel,txtvegetable,txtfood;
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
    Bundle bundle = new Bundle();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();
        catedb= new CategorySQLite(this);
        wordsdb= new WordsSQLite(this);
        listwords=wordsdb.getAllWordsbyCategori(3);
        listcate = catedb.getAllCategory();
        Log.d("Danhsach:","ten:"+listwords.get(2).getWordID()+"a: "+listwords.get(2).getEnglish()+"b: "+listwords.get(2).getVietnamese());

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addControl() {
        btnHome = (Button) findViewById(R.id.btn_home);
        btnHome.setBackground(getImage(listcate.get(0).getCategori_Icon()));
        txthome= (TextView) findViewById(R.id.txthome);
        txthome.setText(listcate.get(0).getCategori_Name());


        btnPeople = (Button) findViewById(R.id.btn_people);
        btnPeople.setBackground(getImage(listcate.get(4).getCategori_Icon()));
        txtpeople= (TextView) findViewById(R.id.txtpeople);
        txtpeople.setText(listcate.get(4).getCategori_Name());


        btnAnimal = (Button) findViewById(R.id.btn_animal);
        btnAnimal.setBackground(getImage(listcate.get(1).getCategori_Icon()));
        txtanimal= (TextView) findViewById(R.id.txtanimal);
        txtanimal.setText(listcate.get(1).getCategori_Name());


        btnFruit = (Button) findViewById(R.id.btn_fruit);
        btnFruit.setBackground(getImage(listcate.get(7).getCategori_Icon()));
        txtfruit= (TextView) findViewById(R.id.txtfruit);
        txtfruit.setText(listcate.get(7).getCategori_Name());


        btnSport = (Button) findViewById(R.id.btn_sport);
        btnSport.setBackground(getImage(listcate.get(6).getCategori_Icon()));
        txtsport= (TextView) findViewById(R.id.txtsport);
        txtsport.setText(listcate.get(6).getCategori_Name());


        btnTravel = (Button) findViewById(R.id.btn_travel);
        btnTravel.setBackground(getImage(listcate.get(2).getCategori_Icon()));
        txttravel= (TextView) findViewById(R.id.txttravel);
        txttravel.setText(listcate.get(2).getCategori_Name());


        btnVegetable = (Button) findViewById(R.id.btn_vegetable);
        btnVegetable.setBackground(getImage(listcate.get(5).getCategori_Icon()));
        txtvegetable= (TextView) findViewById(R.id.txtvegetable);
        txtvegetable.setText(listcate.get(5).getCategori_Name());


        btnFood = (Button) findViewById(R.id.btn_food);
        btnFood.setBackground(getImage(listcate.get(3).getCategori_Icon()));
        txtfood= (TextView) findViewById(R.id.txtfood);
        txtfood.setText(listcate.get(3).getCategori_Name());


      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animation for textview learn english
        scale1 = AnimationUtils.loadAnimation(this,R.anim.scale);
        //Animation for all topic button
        scale2 = AnimationUtils.loadAnimation(this,R.anim.scale);

        tv_learnEnglish = (TextView) findViewById(R.id.tv_learnenglish);

    }

    public Drawable getImage(String nameimg)
    {
//        String name = nameimg.replace(".png","");
        int img = getResources().getIdentifier(nameimg, "drawable",getApplicationContext().getPackageName());
        Log.d("Hinh","TenHinh"+ img);
        Drawable dr;
       if(img!=0) {
            dr = getApplicationContext().getResources().getDrawable(img);
        }else
        {
            dr = getApplicationContext().getResources().getDrawable(R.drawable.home);
        }
        return dr;
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
            int idhome=listcate.get(0).getCategori_ID();
            bundle.putInt("Id",idhome);
            intent.putExtra("ID",bundle);
            startActivity(intent);

        }else if (v.getId() == R.id.R3 || v.getId() == R.id.btn_people) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idPeople=listcate.get(4).getCategori_ID();
            bundle.putInt("Id",idPeople);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.R5 || v.getId() == R.id.btn_fruit) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idfruit=listcate.get(7).getCategori_ID();
            bundle.putInt("Id",idfruit);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.R7 || v.getId() == R.id.btn_animal) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idanimal=listcate.get(1).getCategori_ID();
            bundle.putInt("Id",idanimal);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        }
        else if (v.getId() == R.id.R2 || v.getId() == R.id.btn_sport) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idsport=listcate.get(6).getCategori_ID();
            bundle.putInt("Id",idsport);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.R4 || v.getId() == R.id.btn_travel) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idtravel=listcate.get(2).getCategori_ID();
            bundle.putInt("Id",idtravel);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.R6 || v.getId() == R.id.btn_vegetable) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idveget=listcate.get(5).getCategori_ID();
            bundle.putInt("Id",idveget);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.R8 || v.getId() == R.id.btn_food ) {
            v.startAnimation(anim_home);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int idfood=listcate.get(3).getCategori_ID();
            bundle.putInt("Id",idfood);
            intent.putExtra("ID",bundle);
            startActivity(intent);
        }
    }
}
