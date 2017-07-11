package com.sunnyxibei.gogank.http;

import com.sunnyxibei.gogank.bean.NewsBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jiayuanbin on 2016/8/7.
 */
public class HttpMethods {
    private static final String baseUrl = "http://gank.io/api/search/query/listview/";
    private INewsService iNewsService;

    private HttpMethods() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        iNewsService = retrofit.create(INewsService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取gank.io的网络数据
     * @param subscriber
     *
     */
    public void getNews(DisposableSubscriber<NewsBean> subscriber){
        iNewsService.getNews("Android", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
