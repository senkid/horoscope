package com.example.horoscope_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {


    List<Data> list = Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override

    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // инициализировать View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        //
        return holder;

    }
    @Override
    // метод использующий View_Holder для заполнения строки в recyclerView
    public void onBindViewHolder(View_Holder holder, int position) {

        //Заполнение строки с recycleView
        holder.title.setText(list.get(position).name);
        holder.description.setText(list.get(position).date);
        holder.imageView.setImageResource(list.get(position).imageId);
    }
    @Override
    public int getItemCount() {
        //возвращает количество элементов, отображаемых RecyclerView
        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    // новый элемент в RecyclerView
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }
    // Удаляем элемент RecyclerView, содержащий указанный объект данных
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }
}

