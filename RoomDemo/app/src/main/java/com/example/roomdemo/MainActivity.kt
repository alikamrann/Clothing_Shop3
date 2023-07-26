package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.adapter.MyRecyclerViewAdapter
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository
import com.example.roomdemo.db.SubscriberViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val  factory = SubscribeViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
         initRecyclerVew()

        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun initRecyclerVew(){
        binding.recyclerviewItem.layoutManager = LinearLayoutManager(this)
        displaySubscriber()
    }
    private fun displaySubscriber(){
        subscriberViewModel.subscriber.observe(this) {
            Log.i("MyTag", it.toString())
            binding.recyclerviewItem.adapter = MyRecyclerViewAdapter(it,{selectedItem:Subscriber->listItemClicked(selectedItem)})
        }
    }
    private fun listItemClicked(subscriber: Subscriber){
//       subscriberViewModel.initSubscriber(subscriber)
        subscriberViewModel.initSubscriber(subscriber)
    }
}