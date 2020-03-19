package com.bawei.liqingsheng0314.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liqingsheng0314.R;
import com.bawei.liqingsheng0314.bean.Bean;
import com.bawei.liqingsheng0314.util.NetUtil;

import java.util.List;

/**
 * datetime:2020/3/14
 * author:刘庆生(lenovo)
 * function:
 */
public class MyAdapter extends BaseAdapter{
    private List<Bean.ResultBean> list;

    public MyAdapter(List<Bean.ResultBean> list) {

        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            holder.imageView = convertView.findViewById(R.id.img);
            holder.textView = convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Bean.ResultBean resultBean = list.get(position);
        holder.textView.setText(resultBean.getDesc());
        List<String> images = resultBean.getImages();
        if (images!=null){
            holder.imageView.setVisibility(View.VISIBLE);
            NetUtil.getInstance().doGetPhoto(images.get(0),holder.imageView);
        }else{
            holder.imageView.setVisibility(View.GONE);
        }

        return convertView;
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
