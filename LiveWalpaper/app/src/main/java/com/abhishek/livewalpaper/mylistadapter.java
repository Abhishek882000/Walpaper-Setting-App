package com.abhishek.livewalpaper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class mylistadapter extends ArrayAdapter {
    private final Activity context;

    Integer imageid[];
    public mylistadapter(Activity context, Integer[] imageid) {
        super(context,R.layout.mylayout,imageid);
        this.context = context;
        this.imageid=imageid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.mylayout, null, false);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(imageid[position]);
        return view;
    }
}
