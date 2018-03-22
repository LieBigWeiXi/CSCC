package com.example.atlas.cscc20.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.atlas.cscc20.Bean.PicInfo;
import com.example.atlas.cscc20.Bean.VodInfo;
import com.example.atlas.cscc20.InternetHelper.InternetConfig;
import com.example.atlas.cscc20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atlas on 2017/12/3.
 */

public class HistoryAdapter extends BaseAdapter {
    private List<PicInfo> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoryAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        arrayList = new ArrayList<PicInfo>();//初始化集合
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
        HistoryAdapter.Holder holder;
        if (convertView == null) {
            holder = new HistoryAdapter.Holder();
            convertView = layoutInflater.inflate(R.layout.historyitem, parent, false);
            holder.image = (ImageView) convertView.findViewById(R.id.history_img);
            holder.textView=(TextView)convertView.findViewById(R.id.history_title);
            convertView.setTag(holder);
        }else {
            holder = ( HistoryAdapter.Holder) convertView.getTag();
        }
        PicInfo picInfo = arrayList.get(position);
        holder.image.setImageBitmap(picInfo.old_bitMap);
        holder.textView.setText(picInfo.name);
        return  convertView;
    }

    class Holder{
        ImageView image;
        TextView textView;
    }
    public void setArrayList(List<PicInfo> arrayList) {
        this.arrayList = arrayList;
    }
}
