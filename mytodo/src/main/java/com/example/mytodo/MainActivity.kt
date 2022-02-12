package com.example.mytodo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.ActivityMainBinding
import com.example.mytodo.databinding.ItemBinding

lateinit var data: MutableList<String>
class MainActivity : AppCompatActivity() {
    val adapter = Adapter()
    val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val todo = it.data?.getStringExtra("new_todo")
        if (it.resultCode == RESULT_OK && todo != null) {
            data.add(todo)
            Log.d(TAG, ": $data")
            Log.d(TAG, ": $todo")
            adapter.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList("data", ArrayList(data))
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: world")
    }
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "onSaveInstanceState: hello")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = savedInstanceState?.getStringArrayList("data")?.toMutableList() ?: mutableListOf()
        binding.recycler.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        binding.fab.setOnClickListener {
            result.launch(Intent(this, AddActivity::class.java))
        }
    }
}

class VH(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

class Adapter : RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.todo.text = data[position]
    }

    override fun getItemCount(): Int = data.size
}