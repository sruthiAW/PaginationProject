package com.example.mypagingapp.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypagingapp.databinding.ListItemLayoutBinding

class SamplePagingAdapter: PagingDataAdapter<SampleModel, SamplePagingAdapter.ViewHolder>(SampleDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        Log.d("MTEST", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.d("MTEST", "onBindViewHolder, position $position")
        holder.bindData(getItem(position))
    }

    class ViewHolder(private val binding: ListItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: SampleModel?) {
            model?.let {
                binding.itemName.text = "${model.id} \n ${model.name}"
            }
        }
    }

    object SampleDiffer : DiffUtil.ItemCallback<SampleModel>() {
        override fun areItemsTheSame(oldItem: SampleModel, newItem: SampleModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SampleModel, newItem: SampleModel): Boolean {
            return oldItem == newItem
        }
    }
}