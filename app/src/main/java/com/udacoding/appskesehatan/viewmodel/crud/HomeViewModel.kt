package com.udacoding.appskesehatan.viewmodel.crud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.appskesehatan.data.network.RetrofitClient
import com.udacoding.appskesehatan.model.crud.DataResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel(){
    val list = MutableLiveData<DataResponse>()

    fun getData(): LiveData<DataResponse>{
        RetrofitClient.apiNetwork().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.postValue(it)
            },{
                Log.d("HomeViewModel", it.localizedMessage)
            })

        return list
    }
}