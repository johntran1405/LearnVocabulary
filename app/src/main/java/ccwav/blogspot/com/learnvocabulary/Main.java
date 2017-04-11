package ccwav.blogspot.com.learnvocabulary;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ccwav.blogspot.com.learnvocabulary.Adapter.GridViewAdapter;
import ccwav.blogspot.com.learnvocabulary.Database.CategorySQLite;
import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class Main extends AppCompatActivity {
    GridViewAdapter gridViewAdapter;
    GridView gridView;

    int ImageCategory;
    String NameCategory;

    CategorySQLite catedb;
    WordsSQLite wordsdb;
    List<Categories_Model> listcate= new ArrayList<>();
    List<Words_Model> listwords= new ArrayList<>();

    Bundle bundle = new Bundle();

    ArrayList<Integer> ImageCategories= new ArrayList<>();
    ArrayList<String> NameCategories= new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_menu);
        createDB();
        catedb= new CategorySQLite(this);
        wordsdb= new WordsSQLite(this);
        listwords=wordsdb.getAllWordsbyCategori(3);
        listcate = catedb.getAllCategory();
        gridView = (GridView) findViewById(R.id.GvMenu);
        int i;
        for(i=0;i<listcate.size();i++)
        {

            ImageCategory = getResources().getIdentifier(listcate.get(i).getCategori_Icon(), "drawable",getApplicationContext().getPackageName());
            ImageCategories.add(ImageCategory);
            NameCategory = listcate.get(i).getCategori_Name();
            NameCategories.add(NameCategory);

            Log.d("Mang",""+ImageCategory);

            gridViewAdapter = new GridViewAdapter(this,NameCategories,ImageCategories);
        }

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(view.getId() == ImageCategory){
                    Intent intent = new Intent(Main.this, SecondActivity.class);
                    startActivity(intent);
                }

                Object item = adapterView.getItemAtPosition(i);
                Intent intent = new Intent(Main.this,SecondActivity.class);
                int idfruit=listcate.get(i).getCategori_ID();
                bundle.putInt("Id",idfruit);
                Log.d("Id"," :"+idfruit);
                intent.putExtra("ID",bundle);
                startActivity(intent);
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
