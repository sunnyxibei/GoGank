package com.sunnyxibei.gogank.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunnyxibei.gogank.R;
import com.sunnyxibei.gogank.bean.Results;

import java.util.List;

/**
 * Created by jiayuanbin on 2016/8/7.
 */
public class GankAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {


    public GankAdapter(List<Results> data) {
        super(R.layout.item_recyclerview,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Results results) {
        helper.setText(R.id.desc, results.desc)
                .setText(R.id.publishedAt, results.publishedAt)
                .setText(R.id.type, results.type)
                .setText(R.id.who, results.who);
    }
}
