package com.example.coke.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.coke.R
import com.example.coke.adapter.ViewPagerAdapter
import com.example.coke.databinding.FragmentViewPagerBinding
import com.example.coke.model.CardPager
import kotlin.math.abs


class ViewPagerFragment : Fragment() {
    lateinit var binding: FragmentViewPagerBinding
    lateinit var adapter: ViewPagerAdapter
    var data = arrayListOf<CardPager>()
    lateinit var sliderHandler: Handler
    lateinit var slideRunnable: Runnable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater,container,false)

        sliderItems()
        itemSliderView()
        return binding.root
    }

    private fun itemSliderView() {
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
        data.add(CardPager("https://devstorageacco.blob.core.windows.net/pre-prod/_NOR9450_22092019153631.jpg","demo1","demo2"))
    }

    private fun sliderItems() {
        adapter = ViewPagerAdapter(binding.viewPager,data)
        binding.viewPager.adapter = adapter
        binding.viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit =3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(30))
            addTransformer { page, position ->
                val r : Float = 1 - abs(position)
                page.scaleY = 0.9f + r * 0.1f

            }
            binding.viewPager.setPageTransformer(this)
        }
        sliderHandler = Handler()
        slideRunnable = Runnable {
            //remove the comment to enable auto scroll
            //binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        }
        binding.viewPager.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(slideRunnable)
                    sliderHandler.postDelayed(slideRunnable,2000)
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(slideRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(slideRunnable,2000)

    }
}


