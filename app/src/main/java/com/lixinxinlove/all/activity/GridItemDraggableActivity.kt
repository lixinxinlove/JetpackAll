package com.lixinxinlove.all.activity

import android.app.Service
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.GridItemDraggableAdapter
import com.lixinxinlove.all.base.BaseActivity
import java.util.*
import kotlin.collections.ArrayList


class GridItemDraggableActivity : BaseActivity(), View.OnClickListener {


    private val d: MutableList<String> = mutableListOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
    )

    private lateinit var mRecyclerView: RecyclerView

    // private lateinit var mAdapter: Ap
    private var mAdapter: GridItemDraggableAdapter = GridItemDraggableAdapter()
    private lateinit var mData: MutableList<String>
    private lateinit var edAdd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_item_draggable)
        initData()
        mRecyclerView = findViewById(R.id.rv)

        edAdd = findViewById(R.id.et_add)


        mRecyclerView.layoutManager = GridLayoutManager(this, 3)

        // mAdapter = Ap(this, mData)
        mAdapter.data = mData
        mRecyclerView.adapter = mAdapter
        helper.attachToRecyclerView(mRecyclerView)
        findViewById<View>(R.id.tv).setOnClickListener(this)
        findViewById<View>(R.id.tv_add).setOnClickListener(this)
    }

    private fun initData() {
        mData = ArrayList()
        //  直接用d操作集合会崩溃，Arrays.asList集合不可增删改；详细可以看我的博客
        for (i in d.indices) {
            mData.add(d[i])
        }
    }

    override
    fun onClick(view: View) {
        when (view.id) {
            R.id.tv -> {
                var i = 0
                while (i < mData!!.size) {
                    Log.i(TAG, "onClick: ____" + mData!![i])
                    i++
                }
            }
            R.id.tv_add -> {
             //   mAdapter!!.add(edAdd!!.text.toString().trim { it <= ' ' })
              //  edAdd.setText(null)
            }
        }
    }


    inner class Ap(context: Context, stringList: MutableList<String>) :
        RecyclerView.Adapter<Ap.Vh>() {
        private val mContext: Context = context
        private var mStringList: MutableList<String> = stringList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
            return Vh(LayoutInflater.from(mContext).inflate(R.layout.item_grid_view, null))
        }

        override fun onBindViewHolder(holder: Vh, position: Int) {
            holder.tv.text = mStringList[position]
            holder.iv.setOnClickListener { remove(position) }
        }

        override fun getItemCount(): Int {
            return mStringList.size
        }

        fun add(item: String) {
            val position = mStringList.size
            mStringList.add(item)
            notifyItemInserted(position)
        }

        fun add(position: Int, item: String) {
            mStringList.add(position, item)
            notifyItemInserted(position)
        }

        //  public void remove(T item) {
        //   final int position = stringList.indexOf(item);
        //   if (-1 == position)
        //    return;
        //   stringList.remove(item);
        //   notifyItemRemoved(position);
        //  }
        fun remove(position: Int) {
            mStringList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mStringList.size)
        }

        inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv: TextView
            var iv: ImageView

            init {
                tv = itemView.findViewById(R.id.tv)
                iv = itemView.findViewById(R.id.iv_delete)
            }
        }

    }

    private val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            var dragFrlg = 0
            if (recyclerView.layoutManager is GridLayoutManager) {
                dragFrlg =
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            } else if (recyclerView.layoutManager is LinearLayoutManager) {
                dragFrlg = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            }
            return makeMovementFlags(dragFrlg, 0)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            //滑动事件 下面注释的代码，滑动后数据和条目错乱，被舍弃
            //   Collections.swap(datas,viewHolder.getAdapterPosition(),target.getAdapterPosition());
            //   ap.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            //得到当拖拽的viewHolder的Position
            val fromPosition = viewHolder.adapterPosition
            //拿到当前拖拽到的item的viewHolder
            val toPosition = target.adapterPosition
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(mData, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(mData, i, i - 1)
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //侧滑删除可以使用；
        }

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }

        /**
         * 长按选中Item的时候开始调用
         * 长按高亮
         * @param viewHolder
         * @param actionState
         */
        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder!!.itemView.setBackgroundColor(Color.RED)
                //获取系统震动服务//震动70毫秒
                val vib = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
                vib.vibrate(70)
            }
            super.onSelectedChanged(viewHolder, actionState)
        }

        /**
         * 手指松开的时候还原高亮
         * @param recyclerView
         * @param viewHolder
         */
        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            viewHolder.itemView.setBackgroundColor(0)
            mAdapter!!.notifyDataSetChanged() //完成拖动后刷新适配器，这样拖动后删除就不会错乱
        }
    })

}