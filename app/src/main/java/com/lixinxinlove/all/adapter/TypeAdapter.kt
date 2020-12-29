package com.lixinxinlove.all.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.activity.*
import com.lixinxinlove.all.room.RoomActivity


class TypeAdapter : RecyclerView.Adapter<TypeAdapter.LeeViewViewHolder>() {

    private var mData: MutableList<String> = arrayListOf()

    init {
        mData.add("Room")
        mData.add("ViewPager")
        mData.add("Retrofit")
        mData.add("DataStore")
        mData.add("AlarmManager")
        mData.add("SpringAnimation")
        mData.add("Permission")
        mData.add("Coordinator")
        mData.add("Anim")
        mData.add("WebView")
        mData.add("LeeView")
        mData.add("TabLayout")
        mData.add("CalendarActivity")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeeViewViewHolder {
        val itemView: View = View.inflate(parent.context, R.layout.item_type_view, null)
        return LeeViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeeViewViewHolder, position: Int) {
        holder.typeText.text = mData[position]
        holder.typeText.setOnClickListener {

            when (mData[position]) {
                "Room" -> {
                    it.context.startActivity(Intent(it.context, RoomActivity::class.java))
                }
                "ViewPager" -> {
                    it.context.startActivity(Intent(it.context, ViewPagerActivity::class.java))
                }
                "Retrofit" -> {
                    it.context.startActivity(Intent(it.context, RetrofitActivity::class.java))
                }
                "DataStore" -> {
                    it.context.startActivity(Intent(it.context, DataStoreActivity::class.java))
                }
                "AlarmManager" -> {
                    it.context.startActivity(Intent(it.context, AlarmManagerActivity::class.java))
                }
                "SpringAnimation" -> {
                    it.context.startActivity(
                        Intent(
                            it.context,
                            SpringAnimationActivity::class.java
                        )
                    )
                }
                "Permission" -> {
                    it.context.startActivity(
                        Intent(it.context, PermissionActivity::class.java)
                    )
                }
                "Coordinator" -> {
                    it.context.startActivity(
                        Intent(it.context, CoordinatorActivity::class.java)
                    )
                }
                "Anim" -> {
                    it.context.startActivity(
                        Intent(it.context, AnimActivity::class.java)
                    )
                }
                "WebView" -> {
                    it.context.startActivity(
                        Intent(it.context, WebViewActivity::class.java)
                    )
                }
                "LeeView" -> {
                    it.context.startActivity(
                        Intent(it.context, LeeViewActivity::class.java)
                    )
                }"TabLayout" -> {
                    it.context.startActivity(
                        Intent(it.context, TabLayoutActivity::class.java)
                    )
                }"CalendarActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, CalendarActivity::class.java)
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