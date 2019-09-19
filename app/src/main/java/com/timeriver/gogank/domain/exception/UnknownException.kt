package com.timeriver.gogank.domain.exception

class UnknownException(errorCode: Int, description: String = "Unknown exception") :
        DomainException(errorCode, description) {
}