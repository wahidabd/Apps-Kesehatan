package com.udacoding.appskesehatan.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.appskesehatan.data.network.RetrofitClient
import com.udacoding.appskesehatan.model.auth.UserItems
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    private var user = MutableLiveData<List<UserItems>>()

    fun setLogin(email: String, password: String): LiveData<List<UserItems>> {
        RetrofitClient.userNetwork().login(email, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           if (it.isSuccess == true){
                                user.postValue(it.data!!)
                           }

                }, {
                  Log.d("LoginViewModel", it.message.toString())
                })
        return user
    }
}