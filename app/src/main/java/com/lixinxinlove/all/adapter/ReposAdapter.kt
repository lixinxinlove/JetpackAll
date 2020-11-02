package com.lixinxinlove.all.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.http.data.UserRepos

class ReposAdapter(var mData: List<UserRepos>) :
    RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val itemView = View.inflate(parent.context, R.layout.item_repos_view, null)
        return ReposViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.loginNameText.text = mData[position].full_name
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    inner class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loginNameText: TextView = itemView.findViewById(R.id.login_name)
    }


}