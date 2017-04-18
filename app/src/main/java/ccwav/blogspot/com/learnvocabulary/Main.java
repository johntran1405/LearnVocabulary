package ccwav.blogspot.com.learnvocabulary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ccwav.blogspot.com.learnvocabulary.Adapter.GridViewAdapter;
import ccwav.blogspot.com.learnvocabulary.Common.DialogEx;
import ccwav.blogspot.com.learnvocabulary.Database.CategorySQLite;
import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;


public class Main extends AppCompatActivity {
    GridViewAdapter gridViewAdapter;
    GridView gridView;

    private int ImageCategory, idCategory;
    private String NameCategory;
    private LinearLayout LnVocabulary, LnListenChoose, LnListenWrite, LnSpeech, LnChoice;
    private Button btnClose;
    Dialog dialog;

    CategorySQLite catedb;
    WordsSQLite wordsdb;
    List<Categories_Model> listcate = new ArrayList<>();
    List<Words_Model> listwords = new ArrayList<>();

    Bundle bundle = new Bundle();

    ArrayList<Integer> ImageCategories = new ArrayList<>();
    ArrayList<String> NameCategories = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_menu);
        createDB();
        catedb = new CategorySQLite(this);
        wordsdb = new WordsSQLite(this);
        listwords = wordsdb.getAllWordsbyCategori(3);
        listcate = catedb.getAllCategory();
        gridView = (GridView) findViewById(R.id.GvMenu);
        int i;
        for (i = 0; i < listcate.size(); i++) {

            ImageCategory = getResources().getIdentifier(listcate.get(i).getCategori_Icon(), "drawable", getApplicationContext().getPackageName());
            ImageCategories.add(ImageCategory);
            NameCategory = listcate.get(i).getCategori_Name();
            NameCategories.add(NameCategory);

            Log.d("Mang", "" + ImageCategory);

            gridViewAdapter = new GridViewAdapter(this, NameCategories, ImageCategories);
        }

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (view.getId() == ImageCategory) {
                    //  Intent intent = new Intent(Main.this, SecondActivity.class);
                    //  startActivity(intent);
                }

                Object item = adapterView.getItemAtPosition(i);
                //Intent intent = new Intent(Main.this, SecondActivity.class);
                int idfruit = listcate.get(i).getCategori_ID();
                bundle.putInt("Id", idfruit);
                Log.d("Id", " :" + idfruit);
                // intent.putExtra("ID", bundle);
                // startActivity(intent);
                Dialog(i);
            }
        });



    }

    private void Dialog(int position) {

        idCategory = listcate.get(position).getCategori_ID();
        dialog = new Dialog(this, R.style.dialog_message);
        dialog.setContentView(R.layout.activity_second_m);


//        ImageView imgVocabulary, imgListenChoose, imgListenWrite, imgChoice, imgSpeech;
//        TextView txtVocabulary, txtListenChoose, txtListenWrite, txtChoice, txtSpeech;
//
//        imgVocabulary = (ImageView) dialog.findViewById(R.id.imgVocabulary);
//        imgListenChoose = (ImageView) dialog.findViewById(R.id.imgListenChoose);
//        imgListenWrite = (ImageView) dialog.findViewById(R.id.imgListenWrite);
//        imgChoice = (ImageView) dialog.findViewById(R.id.imgChoice);
//        imgSpeech = (ImageView) dialog.findViewById(R.id.imgSpeech);
//
//        txtVocabulary = (TextView) dialog.findViewById(R.id.txtVocabulary);
//        txtListenChoose = (TextView) dialog.findViewById(R.id.txtListenChoose);
//        txtListenWrite = (TextView) dialog.findViewById(R.id.txtListenWrite);
//        txtSpeech = (TextView) dialog.findViewById(R.id.txtSpeech);
//        txtChoice = (TextView) dialog.findViewById(R.id.txtChoice);

        LnVocabulary = (LinearLayout) dialog.findViewById(R.id.LnVocabulary);
        LnListenChoose = (LinearLayout) dialog.findViewById(R.id.LnListenChoose);
        LnListenWrite = (LinearLayout) dialog.findViewById(R.id.LnListenWrite);
        LnSpeech = (LinearLayout) dialog.findViewById(R.id.LnSpeech);
        LnChoice = (LinearLayout) dialog.findViewById(R.id.LnChoice);
        btnClose = (Button) dialog.findViewById(R.id.btnClose);

        TouchButton();
        dialog.show();

    }

    public void TouchButton() {

        LnVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocabularyActivity.class);
                bundle.putInt("id", idCategory);
                intent.putExtra("IDCate", bundle);
                startActivity(intent);
            }
        });
        LnListenChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListenAndChooseActivity.class);
                bundle.putInt("id", idCategory);
                intent.putExtra("IDCate", bundle);
                startActivity(intent);
            }
        });


        LnListenWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListenAndWriteActivity.class);
                bundle.putInt("id", idCategory);
                intent.putExtra("IDCate", bundle);
                startActivity(intent);
            }
        });

        LnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpeechActivity.class);
                bundle.putInt("id", idCategory);
                intent.putExtra("IDCate", bundle);
                startActivity(intent);
            }
        });

        LnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                bundle.putInt("id", idCategory);
                intent.putExtra("IDCate", bundle);
                startActivity(intent);
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


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

}
