package ccwav.blogspot.com.learnvocabulary.Adapter;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import ccwav.blogspot.com.learnvocabulary.Model.Tudien;
import ccwav.blogspot.com.learnvocabulary.R;

/**
 * Created by PC14-02 on 12/24/2015.
 */
public class TudienAdapter extends ArrayAdapter<Tudien> implements TextToSpeech.OnInitListener
{
    Activity context;
    int resource;
    List<Tudien> objects;
    TextToSpeech tts;

    public TudienAdapter(Activity context, int resource, List<Tudien> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View tudien_item=inflater.inflate(this.resource, null);
       // View tudien_layout= inflater.inflate(this.resource,parent);


        final TextView txtTu= (TextView) tudien_item.findViewById(R.id.txttu_);
        final TextView btnListen= (TextView) tudien_item.findViewById(R.id.btnnghe_);

        tts= new TextToSpeech(context,this);

        final Tudien tudien=this.objects.get(position);
        txtTu.setText(tudien.getWord());

        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulynghe(tudien, btnListen);

            }
        });
        return tudien_item;


    }


    private void xulynghe(Tudien tudien, TextView btnListen) {
       tts.speak(tudien.getWord().toString(), TextToSpeech.QUEUE_FLUSH,null);
    }


    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR)
        {
            tts.setLanguage(Locale.UK);
        }
    }



}
