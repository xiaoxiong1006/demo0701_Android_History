package com.example.demo0701_android_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    // 存储最新的response，泛型中的参数类型为String
    private val _response = MutableLiveData<String>()

    // 存放response（String）的LiveData（immutable类型）
    val response: LiveData<String>
        get() = _response

    //private val _property = MutableLiveData<AndroidHistoryData>()
    private val _properties = MutableLiveData<List<AndroidHistoryData>>()

    //val property: LiveData<AndroidHistoryData>
    //   get() = _property
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
              val listResult = HistoryApi.retrofitService.getProperties()
              _response.value = "成功:检测到 Android 历史版本属性个数为： ${listResult.size}"
              if (listResult.isNotEmpty()) {
                 // _property.value = listResult[0]
                  _properties.value = listResult
              }
          }catch (e:Exception){
              _response.value = "失败: ${e.message}"
          }
      }
    }
}
