package com.gilar.awesomeapp.view.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * This class is an Adapter for displaying a RecyclerView item based on LoadState,
 * such as a loading spinner, or a retry error button.
 * */
class PhotoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PhotoLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PhotoLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotoLoadStateViewHolder {
        return PhotoLoadStateViewHolder.create(parent, retry)
    }
}