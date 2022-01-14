package com.gilar.awesomeapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gilar.awesomeapp.R
import com.gilar.awesomeapp.base.BaseActivity
import com.gilar.awesomeapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
    }

}