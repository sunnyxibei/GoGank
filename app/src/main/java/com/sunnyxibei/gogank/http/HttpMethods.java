package com.sunnyxibei.gogank.http;

import com.sunnyxibei.gogank.bean.NewsBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiayuanbin on 2016/8/7.
 */
public class HttpMethods {
    public static final String baseUrl = "http://gank.io/api/search/query/listview/";
    private Retrofit retrofit;
    private INewsService iNewsService;

    private HttpMethods() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
     * @param category
     * @param count
     * @param page
     */
    public void getNews(Subscriber<NewsBean> subscriber, String category, int count, int page){
        iNewsService.getNews(category, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
