package com.example.coke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coke.databinding.ActivityMainBinding
import com.example.coke.ui.BlankFragment
import com.example.coke.ui.ViewPagerFragment

class MainActivity : AppCompatActivity() {
    lateinit var biniding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biniding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biniding.root)
        biniding.bottomNavView.background = null
        biniding.bottomNavView.backgroundTintList = null
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,ViewPagerFragment()).commit()

        biniding.fab.imageTintList = null
        biniding.bottomNavView.itemIconTintList = null
        biniding.bottomNavView.setOnItemSelectedListener {
            it.icon.setTint(resources.getColor(R.color.red))
            true
        }
    }
}