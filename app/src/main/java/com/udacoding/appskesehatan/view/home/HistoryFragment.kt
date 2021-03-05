package com.udacoding.appskesehatan.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.data.adapter.HistoryAdapter
import com.udacoding.appskesehatan.data.helper.SessionManager
import com.udacoding.appskesehatan.databinding.FragmentHistoryBinding
import com.udacoding.appskesehatan.model.crud.DataItem
import com.udacoding.appskesehatan.view.MainActivity
import com.udacoding.appskesehatan.viewmodel.crud.DeleteViewModel
import com.udacoding.appskesehatan.viewmodel.crud.HistoryViewModel

class HistoryFragment : Fragment() {

    private lateinit var updateViewModel: HistoryViewModel
    private lateinit var deleteViewModel: DeleteViewModel
    private var session: SessionManager? = null
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        session = context?.let { SessionManager(it) }

        session?.id?.toInt()?.let { getViewModel(it) }
    }

    private fun getViewModel(user_id: Int){
        updateViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        deleteViewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)

        updateViewModel.getHistory(user_id).observe(this, Observer {
            val adapter = it.data?.let { it1 -> HistoryAdapter(it1) }
            binding.rvHistory.adapter = adapter

            adapter?.setOnItemClickClallback(object : HistoryAdapter.OnClickCallback{
                override fun onItemClicked(data: DataItem) {
                    Toast.makeText(activity, data.judul, Toast.LENGTH_SHORT).show()
                }

                override fun onItemEdit(data: DataItem) {
                    val mBundle = Bundle()
                    mBundle.putString(UpdateFragment.EXTRA_ID, data.id.toString())

                    val fm = UpdateFragment()
                    fm.arguments = mBundle

                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.frame_home_container, fm, UpdateFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                }

                override fun onItemDelete(data: DataItem) {
                    deleteViewModel.delete(data.id).observe(this@HistoryFragment, Observer {
                        startActivity(Intent(activity, MainActivity::class.java))
                        Toast.makeText(activity, "Delete berhasil", Toast.LENGTH_SHORT).show()
                    })
                }
            })
        })
    }
}