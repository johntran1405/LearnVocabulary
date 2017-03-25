package ccwav.blogspot.com.learnvocabulary.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import ccwav.blogspot.com.learnvocabulary.Model.Words_Model;
import ccwav.blogspot.com.learnvocabulary.R;

/**
 * Created by John on 3/23/2017.
 */

public class CustomVocabularyAdapter extends PagerAdapter {
    // get image source , this time i using fake data
    private  LayoutInflater layoutInflater;
    String[] English;
    String[] Vietnamese;
    String[] Spell;
    int[] image;
    String[]context;

    private int[] image_resoure = {R.drawable.tvset,R.drawable.tvset,R.drawable.tvset};
    private Context mcontext;

    public CustomVocabularyAdapter(Context mcontext,String[] EngLish,String[] Spell,int[] image) {

        this.mcontext = mcontext;
        this.English = EngLish;
       // this.Vietnamese = Vietnamese;
        this.Spell = Spell;
       // this.context = context;
        this.image = image;
        System.out.println("entering adapter");

    }

    @Override
    public int getCount() {
        return image_resoure.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
        //return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_vocabulary_layout,container,false);

        //
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView txtNewWord = (TextView) view.findViewById(R.id.txtWord);
        TextView txtSpell = (TextView) view.findViewById(R.id.txtSpell);

        Button btnBookmark = (Button) view.findViewById(R.id.btn_bookmark);
        Button btnSpeak = (Button) view.findViewById(R.id.btn_soundSpeak);
        Button btnhowContext = (Button) view.findViewById(R.id.btn_showContext);

        //
        imageView.setImageResource(image_resoure[position]);
        txtNewWord.setText(English[position]);
        txtSpell.setText(Spell[position]);
        container.addView(view);

        return view;
     //   return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
        //container.removeView((LinearLayout)object);
       // super.destroyItem(container, position, object);
    }
}
