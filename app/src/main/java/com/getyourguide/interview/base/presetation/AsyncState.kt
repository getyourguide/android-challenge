package com.getyourguide.interview.base.presetation

sealed class AsyncState<out T>(private val value: T?) {
    open operator fun invoke(): T? = value
}

object Uninitialized : AsyncState<Nothing>(value = null)

data class Loading<T>(val value: T? = null) : AsyncState<T>(value)

data class Success<T>(val value: T) : AsyncState<T>(value) {
    override operator fun invoke(): T = value
}

data class Failure<T>(val e: Exception, val value: T? = null) : AsyncState<T>(value = null)
