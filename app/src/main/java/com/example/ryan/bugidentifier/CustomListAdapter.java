package com.example.ryan.bugidentifier;

/**
 * Created by Ryan on 11/26/2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemName;
    private final String[] itemDesc;
    private final Bitmap[] image;


    public CustomListAdapter(Activity context, String[] itemName, String[] itemDesc, Bitmap[] image) {
        super(context, R.layout.mylist, itemName);

        this.context = context;
        this.itemName = itemName;
        this.image = image;
        this.itemDesc = itemDesc;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extraTxt = (TextView) rowView.findViewById(R.id.extraText);

        txtTitle.setText(itemName[position]);
        imageView.setImageBitmap(image[position]);
        extraTxt.setText("Description: " + itemDesc[position]);
        return rowView;

    };
}
