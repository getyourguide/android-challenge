package com.getyourguide.interview.base.data

import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection


/**
 * Exception represents IO exception
 */
class ConnectionException : Exception("Network Exception")

/**
 * Exception represents any unknown Exception
 */
class UnknownException(message: String) : Exception(message)


/**
 * Convert error returned from http call to custom exception
 *
 * @return
 * [ConnectionException]
 *
 * [UnknownException]
 */
fun Exception.handleHttpException(): Exception {
    return when (this) {
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> {
                    ConnectionException()
                }
                else -> {
                    UnknownException(this.message ?: this.stackTraceToString())
                }
            }
        }
        is IOException -> {
            ConnectionException()
        }
        else -> {
            UnknownException(this.message ?: this.stackTraceToString())
        }
    }
}
