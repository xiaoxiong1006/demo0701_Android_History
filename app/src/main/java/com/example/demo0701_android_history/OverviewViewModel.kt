package com.example.demo0701_android_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    // 存储最新的response，泛型中的参数类型为String
    private val _response = MutableLiveData<String>()

    // 存放response（String）的LiveData（immutable类型）
    val response: LiveData<String>
        get() = _response

    /**
     *  在init中调用getAndroidHistory(),可以立即显示Response
     */
    init {
        getAndroidHistory()
    }

    private fun getAndroidHistory() {
       // _response.value = "在这里设置API！"
        HistoryApi.retrofitService.getProperties().enqueue(
            //object: Callback<String> {
            object: Callback<List<AndroidHistoryData>> {
                //override fun onFailure(call: Call<String>, t: Throwable) {
                override fun onFailure(call: Call<List<AndroidHistoryData>>, t: Throwable) {
                    _response.value = "失败: " + t.message
                }

                //override fun onResponse(call: Call<String>, response: Response<String>) {
                override fun onResponse(call: Call<List<AndroidHistoryData>>, response: Response<List<AndroidHistoryData>>) {
                    //_response.value = response.body()
                    _response.value = "成功：检测到 Android 历史版本属性个数为：${response.body()?.size}"
                }
            })
    }
}
