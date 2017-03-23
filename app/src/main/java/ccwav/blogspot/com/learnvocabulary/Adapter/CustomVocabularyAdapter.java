package ccwav.blogspot.com.learnvocabulary.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import ccwav.blogspot.com.learnvocabulary.R;

/**
 * Created by John on 3/23/2017.
 */

public class CustomVocabularyAdapter extends PagerAdapter {
    // get image source , this time i using fake data
    private LayoutInflater layoutInflater;

    private int[] image_resoure = {R.drawable.tvset,R.drawable.tvset,R.drawable.tvset};
    private Context mcontext;

    public CustomVocabularyAdapter(Context mcontext) {
        this.mcontext = mcontext;
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
        txtNewWord.setText("tao lao"+position);
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
