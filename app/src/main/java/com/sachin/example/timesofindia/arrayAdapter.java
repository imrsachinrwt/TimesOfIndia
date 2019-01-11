package com.sachin.example.timesofindia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class arrayAdapter extends ArrayAdapter {

    private final int resource;
    private  final LayoutInflater layoutInflater;
    private ArrayList<FeedEntry> feedEntries;



     arrayAdapter(@NonNull Context context, int resource,ArrayList<FeedEntry> feedEntries) {
        super(context, resource);
        this.resource=resource;
        this.layoutInflater=LayoutInflater.from(context);
        this.feedEntries=feedEntries;


    }

    @Override
    public int getCount() {
        return feedEntries.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(resource,parent,false);
            viewHolder=new ViewHolder(convertView);
             convertView.setTag(viewHolder);
        }else {
           viewHolder=(ViewHolder) convertView.getTag();
        }

        FeedEntry currentApp=feedEntries.get(position);

        viewHolder.firstTitle.setText(currentApp.getTitle());
        viewHolder.desc.setText(currentApp.getDesc());
        viewHolder.pubDate.setText(currentApp.getPubDate());


        return convertView;
    }

    class ViewHolder{


        TextView firstTitle;
        TextView desc;
        TextView pubDate;

         ViewHolder(View v) {


            this.firstTitle = v.findViewById(R.id.firstTitle);
            this.desc = v.findViewById(R.id.desc);
            this.pubDate = v.findViewById(R.id.pubDate);

        }
    }


}
