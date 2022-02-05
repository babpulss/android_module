package com.example.myrecycler

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecycler.databinding.ItemMainBinding


class MyAdapter(private val datas: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.binding.apply {
            itemData.text = datas[position]
            itemRoot.setOnClickListener {
                Log.d(TAG, "onBindViewHolder click: $position")
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}