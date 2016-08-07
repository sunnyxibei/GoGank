package com.sunnyxibei.gogank.http;

import com.sunnyxibei.gogank.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jiayuanbin on 2016/8/7.
 */

public interface INewsService {
    @GET("category/{category}/count/{count}/page/{page}")
    Observable<NewsBean> getNews(@Path("category") String category,
                                 @Path("count") int count, @Path("page") int page);
}
