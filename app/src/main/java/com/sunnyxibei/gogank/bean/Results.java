package com.sunnyxibei.gogank.bean;

/**
 * Created by jiayuanbin on 2016/8/7.
 */

public class Results {
    public String desc;
    public String ganhuo_id;
    public String publishedAt;
    public String readability;
    public String type;
    public String url;
    public String who;

    @Override
    public String toString() {
        return "Results{" +
                "desc='" + desc + '\'' +
                ", ganhuo_id='" + ganhuo_id + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", readability='" + readability + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
}
