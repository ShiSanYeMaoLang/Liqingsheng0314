package com.bawei.liqingsheng0314.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * datetime:2020/3/14
 * author:刘庆生(lenovo)
 * function:activity父类,封装activity
 * 父类抽象
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置布局id,
        setContentView(layouId());
        //2.findviewbyId()
        initView();
        //3.初始化数据
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layouId();
}
