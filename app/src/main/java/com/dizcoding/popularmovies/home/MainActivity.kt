package com.dizcoding.popularmovies.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dizcoding.popularmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pagerAdapter = HomePagerAdapter(this,supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)


    }
}