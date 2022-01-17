package com.gilar.awesomeapp.view.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.gilar.awesomeapp.R
import com.gilar.awesomeapp.base.BaseFragment
import com.gilar.awesomeapp.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    private val viewModel: DetailViewModel by viewModel()
    override fun createViewModel() = viewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
        // Setup action bar
        setupActionBar()
        // Show photo details
        mViewBinding.run {
            Glide.with(view.context)
                .load(args.photo.src?.large)
                .apply(RequestOptions().placeholder(R.color.grey).centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(expandedImage)
            tvPhotographerName.text = args.photo.photographer
            "${args.photo.width}px x ${args.photo.height}px".also { tvPhotoResolution.text = it }
            tvPhotoDescription.text = args.photo.alt
        }
    }

    private fun setupActionBar() {
        mViewBinding.run {
            // Add back button
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun loadObservers() {
    }

    override fun initUiListener() {
        mViewBinding.btnVisitProfile.setOnClickListener {
            // Open photographer profile in browser
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(args.photo.photographerUrl)
            startActivity(i)
        }
    }
}