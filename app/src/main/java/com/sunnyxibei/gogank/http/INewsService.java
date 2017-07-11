package com.sunnyxibei.gogank.http;

import com.sunnyxibei.gogank.bean.NewsBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jiayuanbin on 2016/8/7.
 */

public interface INewsService {
    @GET("category/{category}/count/{count}/page/{page}")
    Flowable<NewsBean> getNews(@Path("category") String category,
                               @Path("count") int count, @Path("page") int page);
}
