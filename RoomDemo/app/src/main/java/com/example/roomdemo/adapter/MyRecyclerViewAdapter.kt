package com.example.roomdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.databinding.SubscriberItemBinding
import com.example.roomdemo.db.Subscriber

class MyRecyclerViewAdapter(private val subscribers: List<Subscriber>,private val clickListener:(Subscriber)->Unit) :RecyclerView.Adapter<MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val  binding : SubscriberItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.subscriber_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position],clickListener )
    }
}
class MyViewHolder(val binding: SubscriberItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber : Subscriber ,clickListener:(Subscriber)->Unit ){
        binding.subscriberName.text = subscriber.name
        binding.subscriberEmail.text = subscriber.email
        binding.root.setOnClickListener{
             clickListener(subscriber)
        }
    }
}