package com.example.moneymanager.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moneymanager.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {
    List list = new ArrayList<>();

    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{

        TextView date,purpose,income,expenditure;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }
    public void remove(@Nullable Object object) {
        super.add(object);
        list.remove(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutHandler layoutHandler;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutHandler = new LayoutHandler();
            convertView = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler.date = (TextView) convertView.findViewById(R.id.date);
            layoutHandler.purpose = (TextView) convertView.findViewById(R.id.purpose);
            layoutHandler.income = (TextView) convertView.findViewById(R.id.income);
            layoutHandler.expenditure = (TextView) convertView.findViewById(R.id.expenditure);
            convertView.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)convertView.getTag();
        }

        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.date.setText(dataProvider.getDate());
        layoutHandler.purpose.setText(dataProvider.getPurpose());
        layoutHandler.income.setText(dataProvider.getIncome());
        layoutHandler.expenditure.setText(dataProvider.getExpenditure());

        return convertView;


    }
}
