package ccwav.blogspot.com.learnvocabulary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.R;

/**
 * Created by John on 4/9/2017.
 */

public class GridViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> NameCategory;
    ArrayList ImageCategory;

    public GridViewAdapter(Context context, ArrayList<String> nameCategory, ArrayList imageCategory) {
        this.context = context;
        NameCategory = nameCategory;
        ImageCategory = imageCategory;
    }

    @Override
    public int getCount() {
        return NameCategory.size();
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

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.gridview_row_main,null);

            holder.txtNameCategory = (TextView) view.findViewById(R.id.txtNameCategory1);
            holder.linearLayout= (LinearLayout) view.findViewById(R.id.Linear1);



            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.txtNameCategory.setText( NameCategory.get(i));
        holder.linearLayout.setBackgroundResource((Integer) ImageCategory.get(i));

        return view;
    }

}
