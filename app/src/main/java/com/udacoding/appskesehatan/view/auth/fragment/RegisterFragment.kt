package com.udacoding.appskesehatan.view.auth.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.databinding.FragmentRegisterBinding
import com.udacoding.appskesehatan.viewmodel.auth.RegisterViewModel

class RegisterFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var viewModel: RegisterViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Button
        binding.btnRegister.setOnClickListener(this)
        binding.gotoLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.gotoLogin -> {
                val mFragment = LoginFragment()
                addFragment(mFragment)
            }

            binding.btnRegister -> {
                binding.apply {
                    val nama = edtRegisterName.text.toString()
                    val email = edtRegisterEmail.text.toString().trim()
                    val password = edtRegisterPassword.text.toString().trim()

                    var isEmptyField = false

                    when{
                        nama.isEmpty() -> {
                            edtRegisterName.error = "Nama tidak boleh kosong"
                            isEmptyField = true
                        }

                        email.isEmpty() -> {
                            edtRegisterEmail.error = "Email tidak boleh kosong"
                            isEmptyField = true
                        }

                        password.isEmpty() || password.length < 6 -> {
                            edtRegisterPassword.error = "Password terlalu pendek"
                            isEmptyField = true
                        }

                        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                            edtRegisterEmail.error = "Email tidak valid"
                        }
                    }

                    if (!isEmptyField){
                        viewModel(nama, email, password)
                    }
                }
            }
        }
    }

    private fun viewModel(nama: String, email: String, password: String){
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel?.setRegister(nama, email, password)

        Snackbar.make(binding.root, "Registrasi Berhasil", Snackbar.LENGTH_SHORT).show()
        val fragment = LoginFragment()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment){
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.frame_auth_container, fragment, LoginFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }
}