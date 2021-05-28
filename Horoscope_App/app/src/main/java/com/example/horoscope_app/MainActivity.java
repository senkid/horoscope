package com.example.horoscope_app;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Document doc; // где хранится юрл страница
    private Thread secThread; //создание второстепенного потока
    private Runnable runnable; // где будет запускаться код

    private List<Horoscope> mList = new ArrayList<>(); // список данных с горскопами.
    private List<HoroscopeYesterday> mListYesterday = new ArrayList<>();

    String  Host = "https://horo.mail.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init()
    {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);                // создание второстепенного потока
        secThread.start();                  // запуск второстепренного потока
    }
    private void getWeb()           // функция получения юрл-страницы
    {


        try {
            doc = Jsoup.connect("https://horo.mail.ru/").get();
            Elements div = doc.getElementsByClass("p-imaged-item p-imaged-item_circle p-imaged-item_rune p-imaged-item_shadow_inner");

            for (Element e: div)  // цикл заполнения списка ссылками
            {
               // Log.d("MyLog", "Title : " + div.select("a").attr("href")); // фильтр для проверки полученной ссылки.
               String url = Host + e.attr("href");
               parseLinks(url);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        private void parseLinks(String url)
        {
            try {
                String title;
                String description; //переменная хранит гороскоп на сегодня
                Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(10 * 1000).get();

                Thread.sleep(100);

                Elements divs = doc.getElementsByClass("p-score-day__item__value__inner"); // выбираем по классу обьекты для цифр

                String bus = divs.get(0).text(); // переменная хранит цифру бизнеса на сегодня
                String love = divs.get(1).text(); // переменная хранит цифру любви на сегодня
                String numeral = divs.get(2).text(); // переменная хранит цифру дня на сегодня

                Elements hrefTomorrow = doc.select("div[class=filter filter_light] > div[class=filter__list] > a[name=clb11717607]"); // выбираем теги с ссылками на гороскоп на завтра



                for (Element e: hrefTomorrow) // запол
                {
                    String urlTomorrow = Host + e.attr("href");

                    parseLinksTomorrow(urlTomorrow);
                }


                Elements els = doc.select("div[class=p-prediction]"); //
                for (Element el: els)
                {
                    description = el.select("div[class=article__text] > div[class=article__item article__item_alignment_left article__item_html]").text();
                    //
                    mList.add(new Horoscope(description, bus, love, numeral));
                    Log.d("MyLog", String.valueOf(mList));
                    //System.out.println("бизнес: " + bus + "любовь: " + love + "цифра: " + numeral + "описание: " + description);

                    //mList.add(new Horoscope(description));
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void parseLinksTomorrow(String url)
        {
            try {
                String description;
                Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(10 * 1000).get();

                Thread.sleep(100);

                Elements divs = doc.getElementsByClass("p-score-day__item__value__inner"); // выбираем по классу обьекты для цифр

                String bus = divs.get(0).text(); // переменная хранит цифру бизнеса на сегодня
                String love = divs.get(1).text(); // переменная хранит цифру любви на сегодня
                String numeral = divs.get(2).text(); // переменная хранит цифру дня на сегодня

                Elements els = doc.select("div[class=p-prediction]");
                for (Element el: els)
                {
                    description = el.select("div[class=article__text] > div[class=article__item article__item_alignment_left article__item_html]").text();
                    // Log.d("MyLogYes", bus + " " + love + " " + numeral + " " + description);
                    mListYesterday.add(new HoroscopeYesterday(description, bus, love, numeral));
                    //System.out.println("бизнес2: " + bus + "люблвь2: " + love + "цифра2: " + numeral + "описание2: " + description);
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
