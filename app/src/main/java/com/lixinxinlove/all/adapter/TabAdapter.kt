package com.lixinxinlove.all.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.lixinxinlove.all.fragment.PagerFragment

class TabAdapter(fm: FragmentActivity, var mData: MutableList<String>) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
    return    mData.size
    }

    override fun createFragment(position: Int): Fragment {
        return PagerFragment.newInstance("","")
    }

}