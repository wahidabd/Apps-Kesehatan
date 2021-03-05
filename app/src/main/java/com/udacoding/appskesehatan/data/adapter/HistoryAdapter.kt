package com.udacoding.appskesehatan.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacoding.appskesehatan.databinding.ListHistoryBinding
import com.udacoding.appskesehatan.model.crud.DataItem
import com.udacoding.appskesehatan.model.crud.DataResponse

class HistoryAdapter(private val list: List<DataItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private lateinit var onClickCallback: OnClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val binding = ListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(val binding: ListHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem){
            binding.tvHistoryJudul.text = data.judul

            binding.tvHistoryJudul.setOnClickListener {
                onClickCallback.onItemClicked(data)
            }

            binding.ivEdit.setOnClickListener {
                onClickCallback.onItemEdit(data)
            }

            binding.ivDelete.setOnClickListener {
                onClickCallback.onItemDelete(data)
            }
        }
    }

    fun setOnItemClickClallback(onClickCallback: OnClickCallback){
        this.onClickCallback = onClickCallback
    }

    interface OnClickCallback{
        fun onItemClicked(data: DataItem)
        fun onItemEdit(data: DataItem)
        fun onItemDelete(data: DataItem)
    }
}