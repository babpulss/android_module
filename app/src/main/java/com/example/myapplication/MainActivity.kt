package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {
    val i = AtomicInteger(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.btn.setOnClickListener {
            AlertDialog.Builder(this).setMessage("도경아 안녕~!").setPositiveButton(android.R.string.ok, null).create().show()
            runOnUiThread {
                binding.t.text = i.incrementAndGet().toString()
            }
        }
        supportFragmentManager.beginTransaction().addToBackStack("back").add(R.id.fragmentView, OneFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        (menuItem?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextChange: $p0")
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextSubmit: $p0")
                (menuItem.actionView as SearchView).onActionViewCollapsed()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

}