package com.bawei.liqingsheng0314.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * datetime:2020/3/14
 * author:刘庆生(lenovo)
 * function:抽象,fragment公共
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局id
        View inflate = inflater.inflate(layouId(), container, false);
        //2.通过inflate找控件
        initView(inflate);
        //3.返回inflate
        return inflate;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //4.初始化数据
        initData();
    }
    protected abstract void initData();
    protected abstract void initView(View inflate);
    protected abstract int layouId();
}
