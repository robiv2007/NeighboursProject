package com.example.neighbourproject.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.neighbourproject.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "SearchActivity"
    }

    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}