package com.example.horoscope_app;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListHoroActivity extends AppCompatActivity {

 //   public RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_horo);

        final List<Data> data = fill_with_data();


        // manager recyclerView
        GridLayoutManager manager = new GridLayoutManager(this, 3);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

 /*       final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPos = mRecyclerView.getChildLayoutPosition(view);

                Intent intentPageActivity = new Intent(ListHoroActivity.this, FullHoroActivity.class);
                intentPageActivity.putExtra("page", data.get(itemPos).getClass());
                startActivity(intentPageActivity);

                //Log.d("MyLog", String.valueOf(itemPos));
            }
        };

*/
    }


    // заполенение листа для recyclerView
    private List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();

        data.add(new Data(R.string.aries_text, R.string.date_aries, R.drawable.aries__1_));
        data.add(new Data(R.string.taurus_text, R.string.date_taurus, R.drawable.taurus));
        data.add(new Data(R.string.gemini_text, R.string.date_gemini, R.drawable.gemini));
        data.add(new Data(R.string.cancer_text, R.string.date_cancer, R.drawable.cancer__1_));
        data.add(new Data(R.string.leo_text, R.string.date_leo, R.drawable.leo));
        data.add(new Data(R.string.virgo_text, R.string.date_virgo, R.drawable.virgo));
        data.add(new Data(R.string.libra_text, R.string.date_libra, R.drawable.libra));
        data.add(new Data(R.string.scorpio_text, R.string.date_scorpio, R.drawable.scorpio));
        data.add(new Data(R.string.sagittarius_text, R.string.date_sagittarius, R.drawable.sagittarius));
        data.add(new Data(R.string.capricorn_text, R.string.date_capricorn, R.drawable.capricorn));
        data.add(new Data(R.string.aquarius_text, R.string.date_aquarius, R.drawable.aquarius));
        data.add(new Data(R.string.pisces_text, R.string.date_pisces, R.drawable.pisces));

        return data;
    }






}