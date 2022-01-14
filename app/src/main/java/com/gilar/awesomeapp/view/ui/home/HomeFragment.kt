package com.gilar.awesomeapp.view.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gilar.awesomeapp.R
import com.gilar.awesomeapp.base.BaseFragment
import com.gilar.awesomeapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModel()

    override fun createViewModel() = viewModel

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    /**
     * Called when first launch
     */
    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        mViewBinding.collapsingToolbar.apply {
            title = getString(R.string.app_name)
            setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitle)
        }
    }

    /**
     * Group of all live data observer from view model
     * */
    override fun loadObservers() {
    }

    override fun initUiListener() {
        mViewBinding.run {
            btnViewType.setOnClickListener {
            }
        }
    }

}