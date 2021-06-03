package com.example.horoscope_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


// класс ипользуется для хранения ссылки на заись в RecyclerView

public class View_Holder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.textView_horo);
        description = (TextView) itemView.findViewById(R.id.textView_Date);
        imageView = (ImageView) itemView.findViewById(R.id.imageView_Horo);
    }
}
