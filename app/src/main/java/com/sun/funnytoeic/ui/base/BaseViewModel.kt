package com.sun.funnytoeic.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>().apply { value = false }
    private val errorMessage = MutableLiveData<String>()
    private val supervisorJob = SupervisorJob()

    protected val mainScope = CoroutineScope(supervisorJob + Main)
    protected val ioScope = CoroutineScope(supervisorJob + IO)

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancel()
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}
