package com.bawei.liqingsheng0314.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.liqingsheng0314.R;
import com.bawei.liqingsheng0314.activity.NetActivity;
import com.bawei.liqingsheng0314.base.BaseFragment;
import com.bawei.liqingsheng0314.bean.Bean;
import com.bawei.liqingsheng0314.util.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private PullToRefreshListView pullToRefreshListView;
    //当前页数
    int page = 1;
    //旧数据
    List<Bean.ResultBean> list = new ArrayList<>();
    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected void initView(View inflate) {
        pullToRefreshListView = inflate.findViewById(R.id.pull);
        //支持上下拉,BOTH
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置监听上下拉
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉
                //1.清除旧数据
                list.clear();
                //2.page= 1
                page = 1;
                //3.重新请求
                getData();
                //4.隐藏加载
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉
                //1.page++;
                page++;
                //2.重新请求
                getData();
                //3.隐藏加载
            }
        });
        //设置条目点击事件
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //数据中的url,传递
                String url = list.get(i).getUrl();
               Intent intent = new Intent(getActivity(), NetActivity.class);
                //传值
                intent.putExtra("key",url);
                //跳转
                startActivity(intent);
            }
        });

    }

    @Override
    protected int layouId() {
        return R.layout.fragment_home;
    }

    public void getData() {
        //判断是否有网
        if (NetUtil.getInstance().hasNet(getActivity())){
            String httpUrl ="http://47.94.132.125/baweiapi/gank_android?pageSize=5&page="+page;
            NetUtil.getInstance().doGet(httpUrl, new NetUtil.MyCallback() {
                @Override
                public void onDoGetSuccess(String json) {
                    Bean bean = new Gson().fromJson(json, Bean.class);
                    //获取集合
                    list.addAll(bean.getResult());
                    //适配器
                    MyAdapter myAdapter = new MyAdapter(list);
                    pullToRefreshListView.setAdapter(myAdapter);
                    pullToRefreshListView.onRefreshComplete();
                }

                @Override
                public void onDoGetError() {

                }
            });
        }else{
            Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
        }
    }
}
