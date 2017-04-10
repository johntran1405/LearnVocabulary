package ccwav.blogspot.com.learnvocabulary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ccwav.blogspot.com.learnvocabulary.R;

/**
 * Created by John on 4/9/2017.
 */

public class GridViewAdapter extends BaseAdapter {
    Context context;
    String NameCategory[];
    int ImageCategory[];

    public GridViewAdapter(Context context, String[] nameCategory, int[] imageCategory) {
        this.context = context;
        NameCategory = nameCategory;
        ImageCategory = imageCategory;
    }

    @Override
    public int getCount() {
        return NameCategory.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.gridview_row_main,null);

        TextView txtNameCategory1 = (TextView) view.findViewById(R.id.txtNameCategory1);
        ImageView ImgCategory1 = (ImageView) view.findViewById(R.id.ImgCategory1);

//        TextView txtNameCategory2 = (TextView) view.findViewById(R.id.txtNameCategory2);
//        ImageView ImgCategory2 = (ImageView) view.findViewById(R.id.ImgCategory2);
//
//        TextView txtNameCategory3 = (TextView) view.findViewById(R.id.txtNameCategory3);
//        ImageView ImgCategory3 = (ImageView) view.findViewById(R.id.ImgCategory3);
//
//        TextView txtNameCategory4 = (TextView) view.findViewById(R.id.txtNameCategory4);
//        ImageView ImgCategory4 = (ImageView) view.findViewById(R.id.ImgCategory4);
//
//        TextView txtNameCategory5 = (TextView) view.findViewById(R.id.txtNameCategory5);
//        ImageView ImgCategory5 = (ImageView) view.findViewById(R.id.ImgCategory5);
//
//        TextView txtNameCategory6 = (TextView) view.findViewById(R.id.txtNameCategory6);
//        ImageView ImgCategory6 = (ImageView) view.findViewById(R.id.ImgCategory6);

        txtNameCategory1.setText(NameCategory[i]);
        ImgCategory1.setImageResource(ImageCategory[i]);

//        txtNameCategory2.setText(NameCategory[i]);
//        ImgCategory2.setImageResource(ImageCategory[i]);
//
//        txtNameCategory3.setText(NameCategory[i]);
//        ImgCategory3.setImageResource(ImageCategory[i]);
//
//        txtNameCategory4.setText(NameCategory[i]);
//        ImgCategory4.setImageResource(ImageCategory[i]);
//
//        txtNameCategory5.setText(NameCategory[i]);
//        ImgCategory5.setImageResource(ImageCategory[i]);
//
//        txtNameCategory6.setText(NameCategory[i]);
//        ImgCategory6.setImageResource(ImageCategory[i]);

        return view;
    }
}
