package com.udacoding.appskesehatan.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udacoding.appskesehatan.data.helper.SessionManager
import com.udacoding.appskesehatan.databinding.FragmentProfileBinding
import com.udacoding.appskesehatan.view.auth.AuthActivity
import com.udacoding.appskesehatan.view.auth.fragment.LoginFragment

class ProfileFragment : Fragment() {

    private var session: SessionManager? = null

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = context?.let { SessionManager(it) }

        binding.tvProfileName.text = session?.nama
        binding.tvProfileUsername.text = session?.nama
        binding.tvProfileEmail.text = session?.email

        binding.btnLogout.setOnClickListener {
            session?.logout()

            val i = Intent(activity, AuthActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
        }
    }
}