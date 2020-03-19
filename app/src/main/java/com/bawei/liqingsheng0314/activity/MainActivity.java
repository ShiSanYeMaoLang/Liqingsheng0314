package com.bawei.liqingsheng0314.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bawei.liqingsheng0314.R;
import com.bawei.liqingsheng0314.base.BaseActivity;
import com.bawei.liqingsheng0314.fragment.HomeFragment;
import com.bawei.liqingsheng0314.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button button;
    private ImageView imageView;
    private DrawerLayout drawerLayout;
    //标题集合
    List<String> titleList = new ArrayList<>();
    //fragment
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initData() {
        //1.标题添加
        titleList.add("首页");
        titleList.add("热点");
        titleList.add("我的");
        //2.fragment添加
        fragmentList.add(new HomeFragment());
        fragmentList.add(OtherFragment.getInstance("热点"));
        fragmentList.add(OtherFragment.getInstance("我的"));
        //3.适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }

            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initView() {
        //抽屉
        drawerLayout = findViewById(R.id.drawer);
        //头部标签
        tabLayout = findViewById(R.id.tab);
        //vper
        viewPager = findViewById(R.id.vper);
        //头像
        imageView = findViewById(R.id.img);
        //关闭
        button = findViewById(R.id.bun);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转相册
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
        //关闭抽屉按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭抽屉
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }

    @Override
    protected int layouId() {
        return R.layout.activity_main;
    }

}
