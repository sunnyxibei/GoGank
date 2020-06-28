package com.timeriver.gogank.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.timeriver.gogank.domain.AndroidNewsDataSource
import com.timeriver.gogank.domain.GankRepository
import com.timeriver.gogank.domain.NewsDataSource
import com.timeriver.gogank.domain.model.AndroidNewsModel

class AndroidNewsViewModel(private val repository: GankRepository) : ViewModel() {

    val normalClassData: LiveData<PagedList<AndroidNewsModel>> =
        object : DataSource.Factory<Int, AndroidNewsModel>() {
            override fun create(): DataSource<Int, AndroidNewsModel> =
                AndroidNewsDataSource(repository, viewModelScope)
        }.toLiveData(
            config = Config(
                pageSize = PAGE_SIZE,
                initialLoadSizeHint = PAGE_SIZE
            ),
            initialLoadKey = 1
        )

    val newsLiveData: LiveData<PagingData<AndroidNewsModel>>
        get() = Pager(
            PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
            initialKey = 1
        ) { NewsDataSource(repository) }.flow.asLiveData()

    companion object {
        private const val PAGE_SIZE = 20
    }

}