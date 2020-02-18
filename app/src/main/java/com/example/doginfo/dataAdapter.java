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
        TextView nameFV;
        TextView nameSV;
        TextView phoneV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Dog data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
             // view lookup cache stored in tag
        Holder viewHolder;

        if (convertView == null) {


            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listdogs, parent, false);

            viewHolder.nameFV = (TextView) convertView.findViewById(R.id.txtView1);
            viewHolder.nameSV = (TextView) convertView.findViewById(R.id.txtView2);
            viewHolder.phoneV = (TextView) convertView.findViewById(R.id.txtView3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }


        viewHolder.nameFV.setText("Breed: "+data.getBreed());
        viewHolder.nameSV.setText("Dog Size: "+data.getDogSize());
        viewHolder.phoneV.setText("Age: "+data.getAge());

        // Return the completed view to render on screen
        return convertView;
    }

}
