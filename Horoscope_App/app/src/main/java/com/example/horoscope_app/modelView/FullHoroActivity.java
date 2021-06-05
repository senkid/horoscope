package com.example.horoscope_app.modelView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.horoscope_app.R;
import com.squareup.picasso.Picasso;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FullHoroActivity extends AppCompatActivity{
    private TextView mDescription;
    private TextView mNameHoro;
    private TextView mDate;
    private ImageView mImg;
    private TextView mNumeral;
    private TextView mBus;
    private TextView mLove;
    private TextView mDescriptionTommorow;
    private TextView mNumeralTommorow;
    private TextView mBusTommorow;
    private TextView mLoveTommorow;
    private String MlinkHoro;
    private String MlinkHoroTommorow;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_horo);

        mDescription = (TextView) findViewById(R.id.TV);
        mDescriptionTommorow = findViewById(R.id.TVTommorow);
        mNumeralTommorow = findViewById(R.id.numeral);
        mBusTommorow = findViewById(R.id.bus);
        mLoveTommorow = findViewById(R.id.love);
        mNameHoro = findViewById(R.id.textView_horo);
        mDate = findViewById(R.id.textView_date_2);
        mImg = findViewById(R.id.imageHoro);
        mNumeral = findViewById(R.id.Numeral);
        mBus = findViewById(R.id.Bus);
        mLove = findViewById(R.id.Love);

        mNameHoro.setText(getIntent().getIntExtra("name", 1));
        mDate.setText(getIntent().getIntExtra("date", 1));

        MlinkHoro = getIntent().getStringExtra("linkHoro");
        MlinkHoroTommorow = getIntent().getStringExtra("linkHoroTomorrow");

        Picasso.get()
                .load(getIntent().getIntExtra("img", 1)).fit().centerCrop().into(mImg);


        new HoroLoad().execute();
    }


    // пасринг с сайта
    class HoroLoad extends AsyncTask<Void, Void, Void> {
        String description, numeral, bus, love;
        String descriptionTommorow, numeralTommorow, busTommorow, loveTommorow;


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(MlinkHoro).get();
                Elements divs = doc.getElementsByClass("p-score-day__item__value__inner"); // выбираем по классу обьекты для цифр

                bus = divs.get(0).text();
                love = divs.get(1).text();
                numeral = divs.get(2).text();
                Elements els = doc.select("div[class=p-prediction]");
                description = els.select("div[class=article__text] > div[class=article__item article__item_alignment_left article__item_html]").text();

                Document docTommorow = Jsoup.connect(MlinkHoroTommorow).get();
                Elements divs_Tommorow = docTommorow.getElementsByClass("p-score-day__item__value__inner");

                busTommorow = divs_Tommorow.get(0).text();
                loveTommorow = divs_Tommorow.get(1).text();
                numeralTommorow = divs_Tommorow.get(2).text();
                Elements elsTommorow = docTommorow.select("div[class=p-prediction]");
                descriptionTommorow = elsTommorow.select("div[class=article__text] > div[class=article__item article__item_alignment_left article__item_html]").text();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mDescription.setText(description);
            mNumeral.setText(numeral);
            mLove.setText(love);
            mBus.setText(bus);

            mDescriptionTommorow.setText(descriptionTommorow);
            mNumeralTommorow.setText(numeralTommorow);
            mLoveTommorow.setText(loveTommorow);
            mBusTommorow.setText(busTommorow);
        }
    }
}