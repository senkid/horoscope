package com.example.horoscope_app.modelView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.horoscope_app.R;
import com.example.horoscope_app.model.Horoscope;
import com.example.horoscope_app.view.ListHoroActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Document doc; // где хранится юрл страница


    private List<Horoscope> mList = new ArrayList<>(); // список данных с горскопами.


    String Host = "https://horo.mail.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseAllHoro().execute();

    }
    class ParseAllHoro extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                doc = Jsoup.connect("https://horo.mail.ru/").get();
                Elements div = doc.getElementsByClass("p-imaged-item p-imaged-item_circle p-imaged-item_rune p-imaged-item_shadow_inner");

                for (Element e : div)  // цикл заполнения списка ссылками
                {
                    // Log.d("MyLog", "Title : " + div.select("a").attr("href")); // фильтр для проверки полученной ссылки.
                    String mLinkHoroscope = Host + e.attr("href");

                    Document doc_1 = Jsoup.connect(mLinkHoroscope).get();
                    Elements hrefTomorrow = doc_1.select("div[class=filter filter_light] > div[class=filter__list] > a[name=clb11717607]"); // выбираем теги с ссылками на гороскоп на завтра

                    for (Element b: hrefTomorrow) // запол
                    {
                        String mLinkHoroscopeTomorrow = Host + b.attr("href");

                        mList.add(new Horoscope(mLinkHoroscope, mLinkHoroscopeTomorrow));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            Intent intent = new Intent(MainActivity.this, ListHoroActivity.class);
            intent.putParcelableArrayListExtra("horo", (ArrayList<? extends Parcelable>) mList);

            startActivity(intent);

            finish();
        }
    }

}
