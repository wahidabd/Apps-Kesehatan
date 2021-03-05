package com.udacoding.appskesehatan.viewmodel.crud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.appskesehatan.data.network.RetrofitClient
import com.udacoding.appskesehatan.model.crud.ApiResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class DeleteViewModel : ViewModel() {
    val list = MutableLiveData<ApiResponse>()

    fun delete(id: Int?): LiveData<ApiResponse>{
        RetrofitClient.apiNetwork().delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.postValue(it)
            }, {
                Log.d("Delete View Model", it.localizedMessage)
            })

        return list
    }
}