package com.lixinxinlove.all.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lixinxinlove.all.fragment.CardFragment

class CardViewPagerFragmentAdapter(fm: FragmentManager, var mutableList: MutableList<String>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return mutableList.size
    }

    override fun getItem(position: Int): Fragment {
        return CardFragment.newInstance(mutableList[position])
    }
}