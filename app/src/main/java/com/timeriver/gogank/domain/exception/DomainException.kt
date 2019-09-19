package com.timeriver.gogank.domain.exception

open class DomainException(
    val errorCode: Int, val description: String = ""
) : Exception(description)