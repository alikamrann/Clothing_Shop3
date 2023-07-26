package com.example.viewmodelscope.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.viewmodelscope.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            Log.i("MyTag","thread is : ${Thread.currentThread().name}")
        }
        lifecycleScope.launchWhenCreated {
            Log.i("MyTag","thread is : ${Thread.currentThread().name}")
        }
        lifecycleScope.launchWhenStarted {
            Log.i("MyTag","thread is : ${Thread.currentThread().name}")
        }
        lifecycleScope.launchWhenResumed {
            Log.i("MyTag","thread is : ${Thread.currentThread().name}")
        }
    }
}