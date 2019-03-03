package com.example.tp1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataListAdaptater extends BaseAdapter {

    private Data[] listDatas;
    private LayoutInflater layoutInflater;
    private Context context;

    public DataListAdaptater(Context contexte, Data[] listDatas) {
        this.context = contexte;
        this.listDatas = listDatas;
        layoutInflater = LayoutInflater.from(contexte);
    }

    @Override
    public int getCount() {
        return listDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return listDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_main, null);
            holder = new ViewHolder();
            holder.timestampTextView = (TextView) convertView.findViewById(R.id.timestamp);
            holder.labelTextView = (TextView) convertView.findViewById(R.id.label);
            holder.valueTextView = (TextView) convertView.findViewById(R.id.value);
            holder.moteAddressTextView = (TextView) convertView.findViewById(R.id.addressMote);
            /*
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.populationView = (TextView) convertView.findViewById(R.id.textView_population);*/
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Data Data = this.listDatas[position];
        holder.timestampTextView.setText("Timestamp : "+Data.getTimestamp());
        holder.labelTextView.setText("Type de donnée : "+Data.label);
        holder.valueTextView.setText("Valeur : "+Data.value);
        holder.moteAddressTextView.setText("Adresse de la mote : "+Data.moteAddress);

        return convertView;
    }

    static class ViewHolder {
        public TextView timestampTextView;
        public TextView labelTextView;
        public TextView valueTextView;
        public TextView moteAddressTextView;
    }

}