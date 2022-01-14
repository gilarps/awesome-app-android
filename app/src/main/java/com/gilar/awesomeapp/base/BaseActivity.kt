package com.gilar.awesomeapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import org.koin.core.component.KoinComponent

/**
 * [BaseActivity] purpose:
 * 1. Avoid code duplication
 * 2. Keep consistency
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), KoinComponent {

    lateinit var mViewBinding: VB

    /**
     * Initialize View Binding
     * */
    private fun initViewBinding() {
        if (!::mViewBinding.isInitialized) {
            mViewBinding = getViewBinding()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setContentView(mViewBinding.root)
        onFirstLaunch(savedInstanceState)
    }

    /**
     * Something to do when app first launch grouped here to maintain clean and consistent code
     * */
    abstract fun onFirstLaunch(savedInstanceState: Bundle?)

    /**
     * Getting view binding
     * */
    abstract fun getViewBinding(): VB

}