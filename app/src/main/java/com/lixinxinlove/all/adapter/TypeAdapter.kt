package com.lixinxinlove.all.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.activity.*
import com.lixinxinlove.all.butter.ButterKnifeActivity
import com.lixinxinlove.all.glide.GlideDemoActivity
import com.lixinxinlove.all.location.LocationActivity
import com.lixinxinlove.all.opencv.OpenCvDemo1Activity
import com.lixinxinlove.all.opencv.OpenCvDemoActivity
import com.lixinxinlove.all.room.RoomActivity
import javax.inject.Inject


class TypeAdapter @Inject constructor() : RecyclerView.Adapter<TypeAdapter.LeeViewViewHolder>() {


    private var mData: MutableList<String> = arrayListOf()

    init {
        mData.add("OpenCv")
        mData.add("PermissionX")
        mData.add("Retrofit2Activity")
        mData.add("ButterKnifeActivity")
        mData.add("Glide高级用法")
        mData.add("GPS定位")
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
        mData.add("SmoothScrollActivity")
        mData.add("ImageToVideoActivity")
        mData.add("SurfaceViewActivity")
        mData.add("TransitionActivity")
        mData.add("CppActivity")
        mData.add("OKIOActivity")
        mData.add("HiltActivity")
        mData.add("DiskLruCacheActivity")
        mData.add("GZipActivity")
        mData.add("FlutterActivity")
        mData.add("DownloadImageActivity")
        mData.add("WindowManagerActivity")
        mData.add("GridItemDraggableActivity")
        mData.add("CardFlipActivity")


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeeViewViewHolder {
        val itemView: View = View.inflate(parent.context, R.layout.item_type_view, null)
        return LeeViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeeViewViewHolder, position: Int) {
        holder.typeText.text = mData[position]

        if (position % 2 == 0) {
            holder.typeText.setBackgroundResource(R.color.colorAccent)
        } else {
            holder.typeText.setBackgroundResource(R.color.design_default_color_error)
        }

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
                }
                "TabLayout" -> {
                    it.context.startActivity(
                        Intent(it.context, TabLayoutActivity::class.java)
                    )
                }
                "CalendarActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, CalendarActivity::class.java)
                    )
                }
                "SmoothScrollActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, SmoothScrollActivity::class.java)
                    )
                }
                "ImageToVideoActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, ImageToVideoActivity::class.java)
                    )
                }
                "SurfaceViewActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, SurfaceViewActivity::class.java)
                    )
                }
                "TransitionActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, TransitionActivity::class.java)
                    )
                }
                "CppActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, CppActivity::class.java)
                    )
                }
                "OKIOActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, OKIOActivity::class.java)
                    )
                }
                "HiltActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, HiltActivity::class.java)
                    )
                }
                "DiskLruCacheActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, DiskLruCacheActivity::class.java)
                    )
                }
                "GZipActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, GZipActivity::class.java)
                    )
                }
                "FlutterActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, FlutterActivity::class.java)
                    )
                }
                "DownloadImageActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, DownloadImageActivity::class.java)
                    )
                }
                "WindowManagerActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, WindowManagerActivity::class.java)
                    )
                }
                "GridItemDraggableActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, GridItemDraggableActivity::class.java)
                    )
                }
                "CardFlipActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, CardFlipActivity::class.java)
                    )
                }
                "Glide高级用法" -> {
                    it.context.startActivity(
                        Intent(it.context, GlideDemoActivity::class.java)
                    )
                }

                "GPS定位" -> {
                    it.context.startActivity(
                        Intent(it.context, LocationActivity::class.java)
                    )
                }
                "ButterKnifeActivity" -> {
                    it.context.startActivity(
                        Intent(it.context, ButterKnifeActivity::class.java)
                    )
                }
                "Retrofit2Activity" -> {
                    it.context.startActivity(
                        Intent(it.context, Retrofit2Activity::class.java)
                    )
                }
                "OpenCv" -> {
                    it.context.startActivity(
                        Intent(it.context, OpenCvDemoActivity::class.java)
                    )
                }
                "PermissionX" -> {
                    it.context.startActivity(
                        Intent(it.context, PermissionXActivity::class.java)
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