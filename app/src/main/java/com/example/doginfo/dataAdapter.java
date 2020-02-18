package com.example.doginfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class dataAdapter extends ArrayAdapter<Dog>{

    Context context;
    ArrayList<Dog> mDog;


    public dataAdapter(Context context, ArrayList<Dog> Dog){
        super(context, R.layout.listdogs, Dog);
        this.context=context;
        this.mDog=Dog;
    }

    public  class  Holder{
        TextView breedType;
        TextView dogSize;
        TextView dogAge;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dog data = getItem(position);
        Holder viewHolder;

        if (convertView == null) {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listdogs, parent, false);

            viewHolder.breedType = (TextView) convertView.findViewById(R.id.txtView1);
            viewHolder.dogSize = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.dogAge = (TextView) convertView.findViewById(R.id.textView3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }


        viewHolder.breedType.setText("Breed: "+data.getBreed());
        viewHolder.dogSize.setText("Dog Size: "+data.getDogSize());
        viewHolder.dogAge.setText("Age: "+data.getAge());
        return convertView;
    }

}
