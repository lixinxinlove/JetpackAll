package com.lixinxinlove.all.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.CardViewPagerFragmentAdapter
import com.lixinxinlove.all.adapter.ViewPager2Adapter
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.fragment.CardFragment
import com.lixinxinlove.all.transform.CardTransformer
import com.lixinxinlove.all.transform.CardTransformer2
import com.lixinxinlove.all.transform.OverlayTransformer
import kotlin.concurrent.fixedRateTimer

class ViewPagerActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var cardAdapter: CardViewPagerFragmentAdapter
    private var mData: MutableList<String> = arrayListOf()


    private lateinit var viewPager2:ViewPager2
    private lateinit var cardAdapter2: ViewPager2Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        viewPager = findViewById(R.id.card_view_pager)


        for (i in 1..10) {
            mData.add("lee $i")
        }
        cardAdapter = CardViewPagerFragmentAdapter(supportFragmentManager, mData)
        viewPager.setCurrentItem(mData.size - 1, false)
        viewPager.setPageTransformer(true, CardTransformer())
        viewPager.adapter = cardAdapter

        //
        viewPager2=findViewById(R.id.card_view_pager2)
        viewPager2.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        cardAdapter2 = ViewPager2Adapter(this, mData)

        viewPager2.setCurrentItem(mData.size - 1, false)
        viewPager2.setPageTransformer(OverlayTransformer (mData.size))
        viewPager2.adapter = cardAdapter2

        viewPager2.adapter

    }
}