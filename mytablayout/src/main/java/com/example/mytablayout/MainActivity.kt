package com.example.mytablayout

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mytablayout.databinding.ActivityMainBinding
import com.example.mytablayout.databinding.FragOneBinding
import com.example.mytablayout.databinding.FragTwoBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabSelected: ")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabUnselected: ")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabReselected: ")
            }

        })

        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            val list = listOf(FragOne(), FragTwo())
            override fun getItemCount(): Int {
                return list.size
            }

            override fun createFragment(position: Int): Fragment {
                return list[position]
            }
        }
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = "$position"
        }.attach()
    }
}

class FragOne : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragOneBinding.inflate(inflater).root
    }
}
class FragTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragTwoBinding.inflate(inflater).root
    }
}