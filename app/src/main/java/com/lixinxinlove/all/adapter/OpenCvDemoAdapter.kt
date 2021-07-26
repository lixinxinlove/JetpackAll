package com.lixinxinlove.all.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.opencv.OpenCvDemo1Activity
import com.lixinxinlove.all.opencv.OpenCvDemo2Activity
import com.lixinxinlove.all.opencv.OpenCvDemo4Activity
import javax.inject.Inject

/**
 *   author ： lixinxin
 *   time    ： 2021/7/22
 *   email：895330766@qq.com
 */
class OpenCvDemoAdapter @Inject constructor() :
    RecyclerView.Adapter<OpenCvDemoAdapter.LeeViewViewHolder>() {


    private var mData: MutableList<String> = arrayListOf()

    init {
        mData.add("基础Api使用")
        mData.add("图片区域切割")
        mData.add("图片人脸识别")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeeViewViewHolder {
        val itemView: View = View.inflate(parent.context, R.layout.item_type_view, null)
        return LeeViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeeViewViewHolder, position: Int) {
        holder.typeText.text = mData[position]


        holder.typeText.setOnClickListener {

            when (mData[position]) {

                "基础Api使用" -> {
                    it.context.startActivity(
                        Intent(it.context, OpenCvDemo1Activity::class.java)
                    )
                }

                "图片区域切割" -> {
                    it.context.startActivity(
                        Intent(it.context, OpenCvDemo2Activity::class.java)
                    )
                }

                "图片人脸识别" -> {
                    it.context.startActivity(
                        Intent(it.context, OpenCvDemo4Activity::class.java)
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    inner class LeeViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeText: TextView = itemView.findViewById(R.id.type_view)
    }

}