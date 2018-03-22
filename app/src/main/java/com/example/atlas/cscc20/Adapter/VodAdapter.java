package com.example.atlas.cscc20.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.atlas.cscc20.Bean.VodInfo;
import com.example.atlas.cscc20.InternetHelper.InternetConfig;
import com.example.atlas.cscc20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atlas on 2017/12/3.
 */

public class VodAdapter extends BaseAdapter {
    private List<VodInfo> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public VodAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        arrayList = new ArrayList<VodInfo>();//初始化集合
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VodAdapter.Holder holder;
        if (convertView == null) {
            holder = new VodAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.voditem, parent, false);
            holder.image = (ImageView) convertView.findViewById(R.id.vod_img);
            holder.textViewTitle = (TextView)convertView.findViewById(R.id.vod_title);
            holder.textViewOrder = (TextView)convertView.findViewById(R.id.title_order);
            convertView.setTag(holder);
        }else {
            holder = ( VodAdapter.Holder)convertView.getTag();
        }
        VodInfo vodInfo =arrayList.get(position);
        Glide.with(context).load("http://"+ InternetConfig.Server_ip+":8100/"+vodInfo.icon).into(holder.image);
        holder.textViewTitle.setText(vodInfo.name);
        String order = String.valueOf(position+1);
        holder.textViewOrder.setText(order);
        return  convertView;
    }

    class Holder{
        ImageView image;
        TextView textViewTitle,textViewOrder;
    }
    public void setArrayList(List<VodInfo> arrayList) {
        this.arrayList = arrayList;
    }
}
