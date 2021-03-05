package com.udacoding.appskesehatan.data.helper

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val context: Context) {
    var pref : SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "SESSION"

    var IS_LOGIN = "isLogin"
    var NAME = "name"
    var EMAIL = "email"
    var ID = "id"

    init {
        pref = context.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()
    }

    var login: Boolean?
        get() = pref?.getBoolean(IS_LOGIN, false)
        set(login){
            editor?.putBoolean(IS_LOGIN, true)
            editor?.commit()
        }

    var id: String?
        get() = pref?.getString(ID, "")
        set(id){
            editor?.putString(ID, id)
            editor?.commit()
        }

    var nama: String?
        get() = pref?.getString(NAME, "")
        set(nama){
            editor?.putString(NAME, nama)
            editor?.commit()
        }

    var email: String?
        get() = pref?.getString(EMAIL, "")
        set(email){
            editor?.putString(EMAIL, email)
            editor?.commit()
        }

    fun logout(){
        editor?.clear()
        editor?.commit()
    }

}