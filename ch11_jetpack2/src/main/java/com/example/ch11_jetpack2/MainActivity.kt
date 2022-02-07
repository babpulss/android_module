package com.example.ch11_jetpack2

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch11_jetpack2.databinding.*

val data = mutableListOf<Item>()
var recyclerAdapter: RecyclerView.Adapter<ViewHolder>? = null
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this, binding.drawer, R.string.open_drawer, R.string.close_drawer
        ).apply { syncState() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            val fragments = listOf(FragOne(), FragTwo(), FragThree())
            override fun getItemCount(): Int = fragments.size
            override fun createFragment(position: Int): Fragment = fragments[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        ((menu?.findItem(R.id.search)?.actionView) as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                data.forEachIndexed { i, it ->
                    it.isVisible = it.title.contains(newText ?: "")
                    recyclerAdapter?.notifyItemChanged(i)
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

data class Item(val title: String, val name: String, var isVisible: Boolean = true)
class ViewHolder(val view: ItemBinding) : RecyclerView.ViewHolder(view.root)
class Adapter(private val data: MutableList<Item>, private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.apply {
            title.text = data[position].title
            name.text = data[position].name
            root.setOnClickListener {
                Toast.makeText(context, "position: $position", Toast.LENGTH_SHORT).show()
            }
            root.isVisible = data[position].isVisible
        }
    }

    override fun getItemCount(): Int = data.size

}

class FragOne : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        for (i in 1..10) {
            data.add(Item("Hello$i", "world$i"))
        }
        recyclerAdapter = Adapter(data, inflater.context)
        val binding = FragOneBinding.inflate(inflater, container, false)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    c.density = 100
                    super.onDraw(c, parent, state)
                }
            })
        }
        return binding.root
    }
}

class FragTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragTwoBinding.inflate(inflater).root
    }
}

class FragThree : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragThreeBinding.inflate(inflater).root
    }
}