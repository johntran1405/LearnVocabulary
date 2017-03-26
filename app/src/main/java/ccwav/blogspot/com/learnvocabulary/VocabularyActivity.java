package ccwav.blogspot.com.learnvocabulary;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ccwav.blogspot.com.learnvocabulary.Adapter.CustomVocabularyAdapter;
import ccwav.blogspot.com.learnvocabulary.Database.MyDatabase;
import ccwav.blogspot.com.learnvocabulary.Database.WordsSQLite;
import ccwav.blogspot.com.learnvocabulary.Model.Categories_Model;
import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;

public class VocabularyActivity extends FragmentActivity implements View.OnClickListener{

    MyDatabase myDatabase;
    List<Categories_Model> categories_models = new ArrayList<>();
    List<Words_Model> topic_models = new ArrayList<>();

    // @Override
    Button btnSoundSpeak, btnBookmark,btnShowContext;
    ImageView imageView;
    TextView txtWord,txtSpelling;

    ViewPager viewPager;
    CustomVocabularyAdapter vocabularyAdapter;
    WordsSQLite wordsSQLite;
    Bundle bundle;
    int idcate;

    ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    static List<Words_Model> listword= new ArrayList<>();
    static int NUM_ITEMS =0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_layout_m);
        // getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
        Intent getIntent=getIntent();
        bundle=getIntent.getBundleExtra("IDCate");
        idcate=bundle.getInt("id");
        Log.d("IDCATE","id: "+idcate);
        wordsSQLite= new WordsSQLite(this);
        listword=wordsSQLite.getAllWordsbyCategori(idcate);
        NUM_ITEMS=listword.size();
        Log.d("Array","arr: "+listword.size());
        addControl();
        //addEvent();




    }
    public static class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
        public ImageFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return SwipeFragment.newInstance(position);
        }
    }

    public static class SwipeFragment extends Fragment implements TextToSpeech.OnInitListener{
        TextToSpeech finalMTts = null;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View swipeView = inflater.inflate(R.layout.custom_vocabulary_layout, container, false);
            ImageView imageView = (ImageView) swipeView.findViewById(R.id.imageView);
            final TextView txtEN=(TextView) swipeView.findViewById(R.id.txtWord);
            TextView txtVN=(TextView) swipeView.findViewById(R.id.txtSpell);
            Button btnSoundSpeak = (Button) swipeView.findViewById(R.id.btn_soundSpeak);
            finalMTts= new TextToSpeech(this.getActivity(),this);

            Bundle bundle = getArguments();
            final int position = bundle.getInt("position");
            String imageFileName = listword.get(position).getImage();
            txtEN.setText(listword.get(position).getEnglish());
            txtVN.setText(listword.get(position).getVietnamese());
//            int imgResId = getResources().getIdentifier(String.valueOf(imageFileName), "drawable",getActivity().getPackageName());
            imageView.setBackground(getImage(imageFileName));
            btnSoundSpeak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalMTts.speak(txtEN.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                }

            });

            return swipeView;
        }

        static SwipeFragment newInstance(int position) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            swipeFragment.setArguments(bundle);
            return swipeFragment;
        }
        Drawable getImage(String img)
        {
            int imgResId = getResources().getIdentifier(String.valueOf(img), "drawable",getActivity().getPackageName());
            Log.d("Hinh","TenHinh"+ imgResId);
            Drawable dr;
            if(imgResId!=0) {
                dr = getActivity().getResources().getDrawable(imgResId);
            }else
            {
                dr = getActivity().getResources().getDrawable(R.drawable.home);
            }
            return dr;
        }


        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub
            if (status != TextToSpeech.ERROR) {
                finalMTts.setLanguage(Locale.US);
            } else {
                // Initialization failed.
                Log.e("app", "Could not initialize TextToSpeech.");
            }
        }
        @Override
        public void onDestroy()
        {
            // Don't forget to shutdown!

            if (finalMTts != null)
            {
                finalMTts.stop();
                finalMTts.shutdown();
            }
            super.onDestroy();
        }
    }

    private void addControl() {
//        btnSoundSpeak = (Button) findViewById(R.id.btn_soundSpeak);
//        btnBookmark = (Button) findViewById(R.id.btn_bookmark);
//        btnShowContext = (Button) findViewById(R.id.btn_showContext);
//        txtWord = (TextView) findViewById(R.id.txtWord);
//        txtSpelling = (TextView) findViewById(R.id.txtSpell);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(imageFragmentPagerAdapter);
//        vocabularyAdapter = new CustomVocabularyAdapter(this);
//        viewPager.setAdapter(vocabularyAdapter);


    }

//
//    private void addEvent() {
//
//        btnSoundSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btnBookmark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnShowContext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }


    @Override
    public void onClick(View view) {

    }
}
