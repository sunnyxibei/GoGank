package com.timeriver.gogank.domain.exception

class ServerException(val httpCode: Int = 0, errorCode: Int = 0, description: String = "Unknown server exception") :
    DomainException(errorCode, description)