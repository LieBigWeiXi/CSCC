package com.example.atlas.cscc20.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.atlas.cscc20.Bean.CulInfo;
import com.example.atlas.cscc20.Bean.PicInfo;
import com.example.atlas.cscc20.R;

import java.util.List;

/**
 * Created by Atlas on 2017/12/3.
 */

public class CulAdapter extends RecyclerView.Adapter<CulAdapter.ViewHolder>{
    List<CulInfo> mList;
    Context context;
    Handler handler;
    public static String now_vod;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.iv_cul_item);
        }
    }
    public CulAdapter(Context context, List<CulInfo> mList, Handler handler)
    {
        this.mList=mList;
        this.context=context;
        this.handler=handler;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTyoe)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cul_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CulInfo pic=mList.get(position);
        holder.imageView.setImageBitmap(pic.instr_BitMap);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =holder.getAdapterPosition();
                Message message=new Message();
                 message.what=200;
                message.arg1=position;
                handler.sendMessage(message);
            }
        });
    }

    public int getItemCount()
    {
        return mList.size();
    }

    public void setList(List<CulInfo> mList)
    {
        this.mList=mList;
//        CulInfo culInfo=new CulInfo();
//        this.mList.add(culInfo);
    }

}