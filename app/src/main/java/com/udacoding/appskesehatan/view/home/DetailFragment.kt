package com.udacoding.appskesehatan.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.udacoding.appskesehatan.data.network.RetrofitClient.BASE_URL
import com.udacoding.appskesehatan.databinding.FragmentDetailBinding
import com.udacoding.appskesehatan.model.crud.DataItem

class DetailFragment : Fragment() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<DataItem>(EXTRA_DATA)

        Glide.with(binding.root)
            .load(BASE_URL+"image/"+data?.image)
            .into(binding.imgDetail)

        binding.tvDetailJudul.text  = data?.judul
        binding.tvDetailDeskripsi.text = data?.deskripsi

        binding.btnKembali.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}