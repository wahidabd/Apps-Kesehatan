package com.udacoding.appskesehatan.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.data.adapter.DataListAdapter
import com.udacoding.appskesehatan.databinding.FragmentHomeBinding
import com.udacoding.appskesehatan.model.crud.DataItem
import com.udacoding.appskesehatan.viewmodel.crud.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var viewModel: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.layoutManager = LinearLayoutManager(activity)
        showData()
    }

    private fun showData(){
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel?.getData()?.observe(this, Observer {
            val adapter = it.data?.let { it1 -> DataListAdapter(it1) }
            binding.rvHome.adapter = adapter

            adapter?.setOnItemCallBack(object : DataListAdapter.OnItemCallBack{
                override fun onItemClicked(data: DataItem) {

                    val mBundle = Bundle()
                    mBundle.putParcelable(DetailFragment.EXTRA_DATA, data)

                    val fm = DetailFragment()
                    fm.arguments = mBundle

                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.frame_home_container, fm, DetailFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }

                    Toast.makeText(activity, data.judul, Toast.LENGTH_LONG).show()
                }

            })
        })
    }
}