package com.example.myrecycler2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myrecycler2.databinding.ActivityMainBinding
import com.example.myrecycler2.databinding.FragBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<FragOne>(R.id.fragment_container_view)
        }
        binding.btn.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<FragTwo>(R.id.fragment_container_view)
            }
        }
        binding.btn2.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<FragOne>(R.id.fragment_container_view)
            }
        }
    }
}