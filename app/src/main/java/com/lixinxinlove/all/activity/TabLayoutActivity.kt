package com.lixinxinlove.all.activity

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.TabAdapter
import com.lixinxinlove.all.base.BaseActivity


class TabLayoutActivity : BaseActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private var list = mutableListOf("订单1", "订单1", "订单1", "订单1", "订单1")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager_2)
        var mAdapter = TabAdapter(this, list)
        viewPager.adapter = mAdapter

        //        TabLayout和ViewPager的绑定  //OnConfigureTabCallback
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = list[position] }
        tabLayoutMediator.attach()

    }


}