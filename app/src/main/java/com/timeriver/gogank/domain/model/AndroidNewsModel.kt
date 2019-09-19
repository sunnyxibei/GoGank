package com.timeriver.gogank.domain.model

import com.google.gson.annotations.SerializedName

data class AndroidNewsModel(
    @SerializedName("_id")
    val id: String,
    val createdAt: String?,
    val desc: String?,
    val publishedAt: String?,
    val source: String?,
    val type: String,
    val url: String?,
    val used: Boolean,
    val who: String?
)