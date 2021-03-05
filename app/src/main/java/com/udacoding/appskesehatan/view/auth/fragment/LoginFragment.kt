package com.udacoding.appskesehatan.view.auth.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.data.helper.SessionManager
import com.udacoding.appskesehatan.databinding.FragmentLoginBinding
import com.udacoding.appskesehatan.view.MainActivity
import com.udacoding.appskesehatan.viewmodel.auth.LoginViewModel

class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var viewModel: LoginViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Button
        binding.btnLogin.setOnClickListener(this)
        binding.gotoRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnLogin -> {
                val email = binding.edtLoginEmail.text.toString().trim()
                val password = binding.edtLoginPassword.text.toString().trim()

                var isEmptyField = false

                when {
                    email.isEmpty() -> {
                        binding.edtLoginEmail.error = "Email tidak boleh kosong"
                        isEmptyField = true
                    }

                    password.isEmpty() -> {
                        binding.edtLoginPassword.error = "Password tidak boleh kosong"
                        isEmptyField = true
                    }
                }

                if (!isEmptyField) {
                    setViewModel(email, password)
                }
            }

            binding.gotoRegister -> {
                val mFragment = RegisterFragment()
                val mFragmentManager = fragmentManager

                mFragmentManager?.beginTransaction()?.apply {
                    replace(
                        R.id.frame_auth_container,
                        mFragment,
                        RegisterFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    private fun setViewModel(email: String, password: String) {
        viewModel?.setLogin(email, password)?.observe(this, Observer{
            val session = context?.let { it1 -> SessionManager(it1) }
            session?.id = it[0].id.toString()
            session?.nama = it[0].nama
            session?.email = it[0].email

            Toast.makeText(context, "${it[0].nama} Berhasil Login", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, MainActivity::class.java))
        })

        Snackbar.make(binding.root, "Email atau Password salah", Snackbar.LENGTH_SHORT).show()
    }
}