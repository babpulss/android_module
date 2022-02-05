package com.example.myviewpager

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myviewpager.databinding.ActivityMainBinding
import com.example.myviewpager.databinding.FragOneBinding
import com.example.myviewpager.databinding.FragTwoBinding
import com.example.myviewpager.databinding.ItemPagerBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = MyFragmentStateAdapter(this)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}

class MyFragmentStateAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragments: List<Fragment> = listOf(FragOne(), FragTwo())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
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
        val binding = FragTwoBinding.inflate(inflater, container, false)
        binding.tv.apply {
            adapter = MyPagerAdapter(listOf("hello", "list", "MyPagerAdapteris"))
            layoutManager = LinearLayoutManager(container?.context)
        }
        return binding.root
    }
}

class MyPagerAdapter(val list: List<String>) : RecyclerView.Adapter<MyPagerAdapter.MyPagerViewHolder>() {
    class MyPagerViewHolder(val binding: ItemPagerBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPagerViewHolder {
        return MyPagerViewHolder(ItemPagerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyPagerViewHolder, position: Int) {
        holder.binding.itemPagerTextView.text = list[position]
        when (position % 3) {
            0 -> holder.binding.item.setBackgroundColor(Color.RED)
            1 -> holder.binding.item.setBackgroundColor(Color.BLUE)
            2 -> holder.binding.item.setBackgroundColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int = list.size
}
