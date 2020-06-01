package com.rahul.moviesearch.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.moviesearch.databinding.SearchRecyclerItemViewBinding
import com.rahul.moviesearch.features.search.viewmodel.SearchRowViewModel
import com.rahul.moviesearch.utils.filterEmpty

/**
 * Main Adapter this the adapter used to load
 * the data in the recyclerview of Main activity
 */
class SearchAdapter(val items: ArrayList<SearchRowViewModel>?) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchRecyclerItemViewBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.size.filterEmpty()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position)!!)

    inner class ViewHolder(val binding: SearchRecyclerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchRowViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }

    fun add(rowViewModel: SearchRowViewModel) {
        val position = items?.indexOf(rowViewModel)
        if (position!! == -1) {
            items?.add(rowViewModel)
            notifyItemInserted(items?.size.filterEmpty() - 1)
        }
    }

    fun removeAll() {
            items?.clear()
    }
}
