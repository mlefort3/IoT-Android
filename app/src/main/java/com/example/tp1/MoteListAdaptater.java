package com.example.tp1;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MoteListAdaptater  extends BaseAdapter {

    private Mote[] listmotes;
    private LayoutInflater layoutInflater;
    private Context context;

    public MoteListAdaptater(Context contexte,  Mote[] listmotes) {
        this.context = contexte;
        this.listmotes = listmotes;
        layoutInflater = LayoutInflater.from(contexte);
    }

    @Override
    public int getCount() {
        return listmotes.length;
    }

    @Override
    public Object getItem(int position) {
        return listmotes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.idTextView = (TextView) convertView.findViewById(R.id.idtext);
            holder.ipaddressTextView = (TextView) convertView.findViewById(R.id.ipaddresstexte2);
            holder.macaddressTextView = (TextView) convertView.findViewById(R.id.macaddresstext2);
            holder.lonTextView = (TextView) convertView.findViewById(R.id.lonText);
            holder.latTextView = (TextView) convertView.findViewById(R.id.latText);
            /*
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.populationView = (TextView) convertView.findViewById(R.id.textView_population);*/
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Mote mote = this.listmotes[position];
        holder.idTextView.setText("Id de la mote : "+mote.id);
        holder.ipaddressTextView.setText(mote.ipaddress);
        holder.macaddressTextView.setText(mote.macaddress);
        holder.lonTextView.setText("Longitude : "+mote.lon);
        holder.latTextView.setText("Latitude : "+mote.lat);

        return convertView;
    }

    static class ViewHolder {
        public TextView idTextView;
        public TextView ipaddressTextView;
        public TextView macaddressTextView;
        public TextView latTextView;
        public TextView lonTextView;
    }

}