package ccwav.blogspot.com.learnvocabulary;
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

import ccwav.blogspot.com.learnvocabulary.Adapter.GridViewAdapter;
import ccwav.blogspot.com.learnvocabulary.Database.CategorySQLite;
import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class Main extends AppCompatActivity {
    GridViewAdapter gridViewAdapter;
    GridView gridView;
    ArrayList<String> name= new ArrayList<>();
    int Image;
    ArrayList ArrImage= new ArrayList();
    CategorySQLite catedb;
    WordsSQLite wordsdb;
    List<Categories_Model> listcate= new ArrayList<>();
    List<Words_Model> listwords= new ArrayList<>();
    int index=0,cate;
    Bundle bundle = new Bundle();
    ArrayList<String> nameimg= new ArrayList<>();
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
            nameimg.add(listcate.get(i).getCategori_Icon());
            Image=getResources().getIdentifier(nameimg.get(i), "drawable",getApplicationContext().getPackageName());
            ArrImage.add(Image);
            Log.d("Mang",""+ArrImage);

            gridViewAdapter = new GridViewAdapter(this,Name,ArrImage);
        }

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),""+Name[i],Toast.LENGTH_SHORT).show();
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
