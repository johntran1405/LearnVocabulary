package ccwav.blogspot.com.learnvocabulary;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import ccwav.blogspot.com.learnvocabulary.Adapter.GridViewAdapter;

public class Main extends AppCompatActivity {
    GridViewAdapter gridViewAdapter;
    GridView gridView;
    String Name[] ={"HOME","PEOPLE","FRUIT","VEGETABLE","ANIMAL","SPORT","TAO LAO"};
    int Image[] ={  R.drawable.home_new,R.drawable.people_new,
                    R.drawable.fruit_new,R.drawable.vegetable_new,
                    R.drawable.animal_new,R.drawable.sport_new,R.drawable.sport_new};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_menu);

        gridView = (GridView) findViewById(R.id.GvMenu);
        gridViewAdapter = new GridViewAdapter(this,Name,Image);

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),""+Name[i],Toast.LENGTH_SHORT).show();
            }
        });

    }

}
