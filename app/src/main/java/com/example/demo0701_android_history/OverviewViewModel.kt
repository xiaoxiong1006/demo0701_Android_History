package com.example.demo0701_android_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AndroidApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<AndroidApiStatus>()

    val status: LiveData<AndroidApiStatus>
       get() = _status

    private val _properties = MutableLiveData<List<AndroidHistoryData>>()

    val properties: LiveData<List<AndroidHistoryData>>
        get() = _properties

    /**
     *  在init中调用getAndroidHistory(),可以立即显示Response
     */
    init {
        getAndroidHistory()
    }

    private fun getAndroidHistory() {
      viewModelScope.launch {
          try {
              _status.value = AndroidApiStatus.LOADING
              val listResult = HistoryApi.retrofitService.getProperties()
              _status.value = AndroidApiStatus.DONE
              if (listResult.isNotEmpty()) {
                  _properties.value = listResult
              }
          }catch (e:Exception){
              _status.value = AndroidApiStatus.ERROR
              _properties.value = ArrayList()
          }
      }
    }
}
