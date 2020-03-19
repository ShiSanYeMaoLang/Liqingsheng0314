package com.bawei.liqingsheng0314.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.liqingsheng0314.R;
import com.bawei.liqingsheng0314.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends BaseFragment {

    private TextView textView;

    /**
     * 封装一个方法  geti   动态传值
     * 1、创建一个OtherFragment对象
     * 2、给这个OtherFragment对象传递一个字符串
     */
    @Override
    protected void initData() {
        //接受传值
        Bundle bundle = getArguments();
        if (bundle!=null){
            String key = bundle.getString("key");
            textView.setText(key);
        }
    }

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.tv);
    }

    @Override
    protected int layouId() {
        return R.layout.fragment_other;
    }

    public static OtherFragment getInstance(String value){
        //1.创建OtherFragment对象
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        //2.给other对象传递字符串
        bundle.putString("key",value);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
