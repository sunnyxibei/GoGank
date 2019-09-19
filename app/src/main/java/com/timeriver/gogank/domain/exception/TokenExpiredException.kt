package com.timeriver.gogank.domain.exception


class TokenExpiredException(errorCode: Int = 0, description: String = "") :
        DomainException(errorCode, description)