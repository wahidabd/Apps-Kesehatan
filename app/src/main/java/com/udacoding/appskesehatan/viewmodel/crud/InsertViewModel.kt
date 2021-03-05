package com.udacoding.appskesehatan.viewmodel.crud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.appskesehatan.data.network.RetrofitClient
import com.udacoding.appskesehatan.model.crud.ApiResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class InsertViewModel : ViewModel() {

    private var composite = CompositeDisposable()

    private var list = MutableLiveData<ApiResponse>()

    fun insertData(user_id: Int?, judul: RequestBody, deskripsi: RequestBody, image: MultipartBody.Part): LiveData<ApiResponse>{
        composite.add(RetrofitClient.apiNetwork().insertData(user_id, judul, deskripsi, image)
            .doOnError {
                Log.d("InsertViewModel", it.localizedMessage)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.postValue(it)
            }, {})
        )

        return list
    }
}