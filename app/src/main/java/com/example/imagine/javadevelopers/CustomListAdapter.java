package com.example.imagine.javadevelopers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imagine on 09/03/2017.
 */
public class CustomListAdapter extends ArrayAdapter<User> {

    ArrayList<User> users;
    Context context;
    int resource;
    public CustomListAdapter(Context context, int resource, ArrayList<User> users) {
        super(context, resource, users);
        this.users = users;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)   {
            LayoutInflater layoutInflater= (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.custom_list_layout,null,true);
        }

        User user = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        Picasso.with(context).load(user.getImage()).into(imageView);

        TextView txtName = (TextView) convertView .findViewById(R.id.txtName);
        txtName.setText(user.getName());

        TextView imgUrl = (TextView) convertView .findViewById(R.id.imageUrl);
        imgUrl.setText(user.getImg_url());

        TextView txtPrice = (TextView) convertView .findViewById(R.id.profileUrl);
        txtPrice.setText(user.getHtml_url());
        return convertView;
    }
}
