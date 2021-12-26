package com.example.coke.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.coke.databinding.ViewPagerCardBinding
import com.example.coke.model.CardPager

class ViewPagerAdapter(val viewPager: ViewPager2, val data: ArrayList<CardPager>)
    : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>(){
        class MyViewHolder( val item_binding : ViewPagerCardBinding) : RecyclerView.ViewHolder(item_binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val bind = ViewPagerCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.item_binding.root.context)
            .load(data[position].img).into(holder.item_binding.imageBackground)
        holder.item_binding.apply {
            textBold. text = data[position].text1
            textNormal.text = data[position].text2
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
