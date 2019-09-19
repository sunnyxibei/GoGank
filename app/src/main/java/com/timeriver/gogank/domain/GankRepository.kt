package com.timeriver.gogank.domain

import com.timeriver.gogank.data.service.GankApiService
import com.timeriver.gogank.domain.mapper.AndrondNewsMapper
import com.timeriver.gogank.domain.mapper.DomainExceptionMapper
import com.timeriver.gogank.domain.model.AndroidNewsModel

class GankRepository(private val apiService: GankApiService) {

    suspend fun getAndroidNewsList(pageNo: Int, pageSize: Int): List<AndroidNewsModel> = try {
        apiService.getAndroidNewsListAsync(pageNo, pageSize).run {
            AndrondNewsMapper.mapToAndroidNewsModel(this)
        }
    } catch (e: Exception) {
        throw DomainExceptionMapper.mapException(e)
    }

}