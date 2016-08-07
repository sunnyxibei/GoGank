package com.sunnyxibei.gogank.bean;

import java.util.List;

/**
 * Created by jiayuanbin on 2016/8/7.
 */

public class NewsBean {
    public int count;
    public boolean error;
    public List<Results> results;

    @Override
    public String toString() {
        return "NewsBean{" +
                "count=" + count +
                ", error=" + error +
                ", results=" + results +
                '}';
    }
}

