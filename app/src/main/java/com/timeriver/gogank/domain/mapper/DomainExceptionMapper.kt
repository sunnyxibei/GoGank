package com.timeriver.gogank.domain.mapper

import com.google.gson.JsonParser
import com.timeriver.gogank.domain.exception.NetworkException
import com.timeriver.gogank.domain.exception.ServerException
import com.timeriver.gogank.domain.exception.TokenExpiredException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException


object DomainExceptionMapper {
    fun mapException(exception: Exception): Exception =
        when (exception) {
            is HttpException -> {
                if (exception.code() == 401) {
                    TokenExpiredException()
                } else {
                    parseErrorResponse(exception.response())
                }
            }
            is UnknownHostException -> {
                NetworkException()
            }
            else -> {
                exception
            }
        }

    private fun parseErrorResponse(response: Response<*>?): ServerException {
        return response?.let {
            val jsonObject = JsonParser().parse(response.errorBody()?.string())
            jsonObject.asJsonObject.let {
                ServerException(response.code(), it.get("code").asInt, it.get("message").asString)
            }
        } ?: ServerException()
    }
}