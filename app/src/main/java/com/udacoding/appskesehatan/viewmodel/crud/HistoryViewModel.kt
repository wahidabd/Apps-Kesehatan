package com.udacoding.appskesehatan.viewmodel.crud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.appskesehatan.data.network.RetrofitClient
import com.udacoding.appskesehatan.model.crud.DataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel : ViewModel(){
    private val list = MutableLiveData<DataResponse>()

    fun getHistory(user_id: Int): LiveData<DataResponse>{
        RetrofitClient.apiNetwork().getHistory(user_id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccess == true){
                    list.postValue(it)
                }
            }, {
                Log.d("HistoryViewModel", it.localizedMessage)
            })
        return list
    }
}