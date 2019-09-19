package com.timeriver.gogank.domain.mapper

import com.timeriver.gogank.data.model.AndroidNewsResponse
import com.timeriver.gogank.domain.model.AndroidNewsModel

object AndrondNewsMapper {

    fun mapToAndroidNewsModel(androidNewsResponse: AndroidNewsResponse): List<AndroidNewsModel> =
        androidNewsResponse.results.map {
            AndroidNewsModel(
                id = it.id,
                createdAt = it.createdAt,
                desc = it.desc,
                publishedAt = it.publishedAt,
                source = it.source,
                type = it.type,
                url = it.url,
                used = it.used,
                who = it.who
            )
        }
}