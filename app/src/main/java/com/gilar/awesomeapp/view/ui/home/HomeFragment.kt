package com.gilar.awesomeapp.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gilar.awesomeapp.R
import com.gilar.awesomeapp.base.BaseFragment
import com.gilar.awesomeapp.databinding.FragmentHomeBinding
import com.gilar.awesomeapp.util.LIST_VIEW_TYPE_GRID
import com.gilar.awesomeapp.util.LIST_VIEW_TYPE_LIST
import com.gilar.awesomeapp.view.adapter.PhotoAdapter
import com.gilar.awesomeapp.view.adapter.PhotoLoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModel()
    override fun createViewModel() = viewModel

    private var searchJob: Job? = null
    private val adapter = PhotoAdapter()
    private var listViewType = LIST_VIEW_TYPE_GRID

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    /**
     * Called when first launch
     */
    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        // Setup RecyclerView header and footer
        mViewBinding.recyclerViewPhoto.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PhotoLoadStateAdapter { adapter.retry() },
            footer = PhotoLoadStateAdapter { adapter.retry() }
        )
        getPhotos()
        viewModel.getListViewType()
    }

    private fun getPhotos() {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getPhotos().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    /**
     * Render UI when adapter load state change
     * */
    private fun renderUi(loadState: CombinedLoadStates) {
        // Only shows the list if refresh succeeds
        mViewBinding.recyclerViewPhoto.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh
        mViewBinding.progressIndicator.isVisible = loadState.source.refresh is LoadState.Loading
        // Show the retry state if initial load or refresh fails
        mViewBinding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error

        // Flag to check whether list is empty
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0

        // If list is empty or load state is error or load state is loading,
        // show text message
        mViewBinding.tvMessage.isVisible = isListEmpty
                || loadState.source.refresh is LoadState.Error
                || loadState.source.refresh is LoadState.Loading

        // Set text message that will appear
        when (loadState.source.refresh) {
            is LoadState.Error -> {
                // Error State
                when ((loadState.source.refresh as LoadState.Error).error) {
                    is UnknownHostException,
                    is SocketTimeoutException -> {
                        // Show connection error message
                        mViewBinding.tvMessage.text = getString(R.string.failed_to_connect_to_the_server_check_internet_connection)
                    }
                    else -> {
                        // Show general error message
                        mViewBinding.tvMessage.text = getString(R.string.failed_to_get_photos)
                    }
                }
            }
            is LoadState.Loading -> {
                // Loading State
                // Show loading message
                mViewBinding.tvMessage.text = getString(R.string.please_wait)
            }
            else -> {
                if (isListEmpty) {
                    // Show empty message
                    mViewBinding.tvMessage.text = getString(R.string.photos_not_found_try_again_later)
                }
            }
        }
    }

    /**
     * Group of all live data observer from view model
     * */
    override fun loadObservers() {
        // Observing listViewType value
        viewModel.listViewType.observe(viewLifecycleOwner) {
            listViewType = it
            when (it) {
                LIST_VIEW_TYPE_GRID -> {
                    // Change RecyclerView layout manager to grid
                    val layoutManager = GridLayoutManager(context, 2)
                    mViewBinding.recyclerViewPhoto.layoutManager = layoutManager
                    // Change ivViewType resource to grid
                    mViewBinding.ivViewType.setImageResource(R.drawable.ic_view_grid)
                }
                LIST_VIEW_TYPE_LIST -> {
                    // Change RecyclerView layout manager to linear
                    val layoutManager = LinearLayoutManager(context)
                    mViewBinding.recyclerViewPhoto.layoutManager = layoutManager
                    // Change ivViewType resource to list
                    mViewBinding.ivViewType.setImageResource(R.drawable.ic_view_list)
                }
            }
        }
    }

    /**
     * Group of all listener
     * */
    override fun initUiListener() {
        mViewBinding.run {
            btnViewType.setOnClickListener {
                // Set list view type
                viewModel.setListViewType(
                    when (listViewType) {
                        LIST_VIEW_TYPE_GRID -> LIST_VIEW_TYPE_LIST
                        LIST_VIEW_TYPE_LIST -> LIST_VIEW_TYPE_GRID
                        else -> LIST_VIEW_TYPE_GRID
                    }
                )
            }
            btnRetry.setOnClickListener { adapter.retry() }
        }
        // Make adapter can listen loading state
        adapter.addLoadStateListener { loadState -> renderUi(loadState) }
    }

}