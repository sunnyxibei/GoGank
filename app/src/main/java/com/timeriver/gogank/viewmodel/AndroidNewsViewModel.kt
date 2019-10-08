package com.timeriver.gogank.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.timeriver.gogank.domain.AndroidNewsDataSource
import com.timeriver.gogank.domain.GankRepository
import com.timeriver.gogank.domain.model.AndroidNewsModel

class AndroidNewsViewModel(repository: GankRepository) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
    }

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
}