package com.udacoding.appskesehatan.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.appskesehatan.data.network.RetrofitClient
import com.udacoding.appskesehatan.databinding.ListItemBinding
import com.udacoding.appskesehatan.model.crud.DataItem

class DataListAdapter(private val list: List<DataItem>) : RecyclerView.Adapter<DataListAdapter.ViewHolder>() {

    private var onItemCallBack: OnItemCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem){
            Glide.with(binding.root)
                .load(RetrofitClient.BASE_URL+"image/"+data.image)
                .into(binding.image)

            binding.tvJudul.maxLines = 1
            binding.tvDes.maxLines = 3

            binding.tvJudul.text = data.judul
            binding.tvDes.text = data.deskripsi

            binding.root.setOnClickListener {
                onItemCallBack?.onItemClicked(data)
            }
        }
    }

    fun setOnItemCallBack(onItemCallBack: OnItemCallBack){
        this.onItemCallBack = onItemCallBack
    }

    interface OnItemCallBack{
        fun onItemClicked(data: DataItem)
    }
}