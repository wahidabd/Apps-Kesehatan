package com.udacoding.appskesehatan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.data.helper.SessionManager
import com.udacoding.appskesehatan.view.auth.AuthActivity
import com.udacoding.appskesehatan.view.auth.fragment.LoginFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val session = SessionManager(this)

        Handler().postDelayed(Runnable {
            if (session.login == true){
                startActivity(Intent(this, AuthActivity::class.java))
            }else{
                startActivity(Intent(this, MainActivity::class.java))
            }

            finish()

        }, 2000)
    }
}