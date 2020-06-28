package com.timeriver.gogank.domain

import androidx.paging.PagingSource
import com.timeriver.gogank.domain.model.AndroidNewsModel

class NewsDataSource(private val repository: GankRepository) :
    PagingSource<Int, AndroidNewsModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AndroidNewsModel> =
        runCatching {
            val page = params.key ?: 0
            //获取网络数据
            val result =
                repository.getAndroidNewsList(pageNo = params.key ?: 0, pageSize = params.loadSize)
            LoadResult.Page(
                //需要加载的数据
                data = result,
                //如果可以往上加载更多就设置该参数，否则不设置
                prevKey = null,
                //加载下一页的key 如果传null就说明到底了
                nextKey = page + 1
            )
        }.getOrElse {
            LoadResult.Error(it)
        }

}