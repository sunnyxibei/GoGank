package com.sunnyxibei.gogank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sdsmdg.tastytoast.TastyToast;
import com.sunnyxibei.gogank.R;
import com.sunnyxibei.gogank.SunnyApplication;
import com.sunnyxibei.gogank.adapter.GankAdapter;
import com.sunnyxibei.gogank.bean.NewsBean;
import com.sunnyxibei.gogank.bean.Results;
import com.sunnyxibei.gogank.global.GlobalConstant;
import com.sunnyxibei.gogank.http.HttpMethods;

import java.util.List;

import io.reactivex.subscribers.DisposableSubscriber;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRecyclerView;
    private GankAdapter adapter;
    private List<Results> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initActionBar();
        initData();
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setupDrawerContent(mNavigationView);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void initData() {
        DisposableSubscriber<NewsBean> subscriber = new DisposableSubscriber<NewsBean>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                TastyToast.makeText(SunnyApplication.getContext(),
                        "Loading Success", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            }

            @Override
            public void onNext(NewsBean newsBean) {
                if (newsBean.error) {
                    TastyToast.makeText(SunnyApplication.getContext(),
                            "Loading Failure", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    return;
                }
                LinearLayoutManager layoutManager =
                        new LinearLayoutManager(SunnyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(layoutManager);
                results = newsBean.results;
                adapter = new GankAdapter(results);
                adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM | BaseQuickAdapter.SCALEIN);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener((adapter, view, position) -> {
                    Results result = MainActivity.this.results.get(position);
                    Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
                    intent.putExtra(GlobalConstant.DESC_URL, result.url);
                    intent.putExtra(GlobalConstant.TITLE, result.desc);
                    startActivity(intent);
                });
            }
        };
        HttpMethods.getInstance().getNews(subscriber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener((MenuItem menuItem) -> {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
