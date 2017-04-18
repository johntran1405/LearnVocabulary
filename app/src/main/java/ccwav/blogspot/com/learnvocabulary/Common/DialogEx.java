package ccwav.blogspot.com.learnvocabulary.Common;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ccwav.blogspot.com.learnvocabulary.R;

public class DialogEx {
    public static void show(Activity activity, String message, String content)
    {
        new DialogEx(activity, message, content);
    }
    private Dialog dialog;

    public DialogEx(Activity activity, String message, String content)
    {
        dialog = new android.app.Dialog(activity, R.style.dialog_message);

        dialog.setContentView(R.layout.dialog_message_finish);

        Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        TextView txtCongratulation = (TextView) dialog.findViewById(R.id.txtCongratulation);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);

        txtCongratulation.setText(message);
        txtMessage.setText(content);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
