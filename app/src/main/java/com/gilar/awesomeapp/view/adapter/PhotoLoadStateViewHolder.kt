package com.gilar.awesomeapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.gilar.awesomeapp.R
import com.gilar.awesomeapp.databinding.ItemPhotosLoadStateFooterBinding

/**
 * ViewHolder for [PhotoLoadStateAdapter]
 * */
class PhotoLoadStateViewHolder(
    private val binding: ItemPhotosLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvErrorDescription.text = loadState.error.localizedMessage
        }
        binding.progressLoadMore.isVisible = loadState is LoadState.Loading
        binding.btnRetry.isVisible = loadState is LoadState.Error
        binding.tvErrorDescription.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PhotoLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photos_load_state_footer, parent, false)
            val binding = ItemPhotosLoadStateFooterBinding.bind(view)
            return PhotoLoadStateViewHolder(binding, retry)
        }
    }
}