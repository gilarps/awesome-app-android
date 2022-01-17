package com.gilar.awesomeapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

/**
 * [BaseFragment] purpose:
 * 1. Avoid code duplication
 * 2. Keep consistency
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    lateinit var mViewBinding: VB

    private lateinit var parentVM: VM

    private var navController: NavController? = null

    var hasInitializedRootView = false
    private var rootView: View? = null

    /**
     * This function is to create view model
     * */
    abstract fun createViewModel(): VM

    /**
     * Initialize NavController
     * */
    private fun initNavController(view: View) {
        navController = Navigation.findNavController(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::parentVM.isInitialized) {
            parentVM = createViewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::mViewBinding.isInitialized) {
            mViewBinding = getViewBinding()
        }
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            onFirstLaunch(savedInstanceState, view)
            initNavController(view)
        }
        loadObservers()
        initUiListener()
    }

    /**
     * This function is to group all live data observer from view model
     * */
    abstract fun loadObservers()

    /**
     * Getting view binding
     */
    abstract fun getViewBinding(): VB

    /**
     * Called when first launch
     */
    abstract fun onFirstLaunch(savedInstanceState: Bundle?, view: View)

    /**
     * Initialize UI event listeners
     * All UI listeners are grouped here
     */
    abstract fun initUiListener()

    /**
     * Getting supportFragmentManager
     * */
    fun getParentFm() = requireActivity().supportFragmentManager

    /**
     * Getting childFragmentManager
     * */
    fun getChildFm() = childFragmentManager

    /**
     * Getting NavController
     * */
    fun getNavController() = navController

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }
}