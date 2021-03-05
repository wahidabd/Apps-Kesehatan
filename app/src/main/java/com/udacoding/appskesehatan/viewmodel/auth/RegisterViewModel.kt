package com.udacoding.appskesehatan.viewmodel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.udacoding.appskesehatan.data.network.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

class RegisterViewModel : ViewModel() {
    fun setRegister(nama: String, email: String, password: String){
        RetrofitClient.userNetwork().register(nama, email, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           it.isSuccess
                },{
                    Log.d("RegisterViewModel", it.message.toString())
                })
    }
}