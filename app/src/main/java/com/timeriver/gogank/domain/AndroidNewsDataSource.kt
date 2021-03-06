package com.timeriver.gogank.domain

import androidx.paging.PageKeyedDataSource
import com.timeriver.gogank.domain.exception.ServerException
import com.timeriver.gogank.domain.model.AndroidNewsModel
import com.timeriver.gogank.extension.handleApiErrors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AndroidNewsDataSource(
    private val repository: GankRepository,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, AndroidNewsModel>() {

    companion object {
        private const val INITIAL_KEY = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, AndroidNewsModel>
    ) {
        scope.launch {
            try {
                repository.getAndroidNewsList(
                    pageNo = INITIAL_KEY,
                    pageSize = params.requestedLoadSize
                ).run {
                    callback.onResult(this, null, INITIAL_KEY + 1)
                }
            } catch (e: Exception) {
                handleApiErrors(e) {
                    when (it) {
                        is ServerException -> {

                        }
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, AndroidNewsModel>) {
        scope.launch {
            try {
                repository.getAndroidNewsList(
                    pageNo = params.key,
                    pageSize = params.requestedLoadSize
                ).run {
                    callback.onResult(this, params.key + 1)
                }
            } catch (e: Exception) {
                handleApiErrors(e) {
                    when (it) {
                        is ServerException -> {

                        }
                    }
                }
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, AndroidNewsModel>
    ) {
    }

}