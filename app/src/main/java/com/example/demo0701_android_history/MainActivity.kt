package com.example.demo0701_android_history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.demo0701_android_history.databinding.ActivityMainBinding
import com.example.demo0701_android_history.databinding.GridViewItemBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding: GridViewItemBinding

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.grid_view_item)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

    }
}
