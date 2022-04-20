package edu.uga.cs.countryquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView date, score;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.date = (TextView) row.findViewById(R.id.text_date);
            layoutHandler.score = (TextView) row.findViewById(R.id.text_score);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }

        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.score.setText(dataProvider.getScore());
        layoutHandler.date.setText(dataProvider.getDate());
        return row;
    }

}

