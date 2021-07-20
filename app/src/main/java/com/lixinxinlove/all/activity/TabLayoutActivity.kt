package com.lixinxinlove.all.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.TabAdapter
import com.lixinxinlove.all.base.BaseActivity


class TabLayoutActivity : BaseActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private var list =
        mutableListOf("订单", "订单", "订单", "订单", "订单", "订单", "订单", "订单", "订单", "订单", "订单")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        tabLayout = findViewById(R.id.tab_layout)


        viewPager = findViewById(R.id.view_pager_2)
        var mAdapter = TabAdapter(this, list)
        viewPager.adapter = mAdapter

        //TabLayout和ViewPager的绑定  //OnConfigureTabCallback
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.customView = getCustomView(position)
        }
        tabLayoutMediator.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setViewColor(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                setViewColor(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    private fun setViewColor(tab: TabLayout.Tab, b: Boolean) {
        //设置选中图标样式
        val tabView = tab.customView
        //设置选中字体颜色
        val tv1 = tabView?.findViewById(R.id.tv1) as TextView
        val line = tabView.findViewById(R.id.line) as View
        if (b) {
            tv1.setTextColor(Color.RED)
            line.setBackgroundColor(Color.RED)
        } else {
            tv1.setTextColor(Color.BLACK)
            line.setBackgroundColor(Color.BLACK)
        }

    }


    private fun getCustomView(position: Int): View {
        val tabCustomView = View.inflate(this, R.layout.tab_custom_view, null)
        val tv1 = tabCustomView.findViewById<TextView>(R.id.tv1)
        val line = tabCustomView.findViewById<View>(R.id.line)
        tv1.text = list[position] + position

        if (position == 0) {
            tv1.setTextColor(Color.RED)
            line.setBackgroundColor(Color.RED)
        }
        return tabCustomView
    }

}