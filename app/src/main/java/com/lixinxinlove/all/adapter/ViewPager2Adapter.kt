package com.lixinxinlove.all.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lixinxinlove.all.fragment.CardFragment

class ViewPager2Adapter(fragment: FragmentActivity, var mData: MutableList<String>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun createFragment(position: Int): Fragment {
        return CardFragment.newInstance(mData[position])
    }
}