package com.example.myrecycler2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecycler2.databinding.ActivityMainBinding
import com.example.myrecycler2.databinding.Frag2Binding
import com.example.myrecycler2.databinding.FragBinding
import com.example.myrecycler2.databinding.PartBinding

class FragOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragBinding.inflate(inflater, container, false)
        val data = listOf("hello1","hello2","hello3",   "hello4", )
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(container?.context)
            adapter = MyAdapter(data)
        }
        return binding.root
    }
}

class FragTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return Frag2Binding.inflate(inflater, container, false).root
    }
}

class MyAdapter(val data: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: PartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(PartBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.et.setText(data[position])
        holder.binding.tv.text = data[position]
    }

    override fun getItemCount(): Int = data.size

}