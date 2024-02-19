package com.getyourguide.interview.base.presetation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<T>(private val initialState: T): ViewModel() {

    private val _state = MutableStateFlow<T>(initialState)
    val state: StateFlow<T> = _state

    fun setState(reducer: (currentState: T) -> T) {
        val currentValue = _state.value
        _state.value = reducer(currentValue)
    }
}
