package com.lixinxinlove.all.activity

import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.ReposAdapter
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.http.GitHubService
import com.lixinxinlove.all.http.RetrofitFactory
import com.lixinxinlove.all.http.data.UserRepos
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : BaseActivity() {

    private lateinit var reposRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var reposAdapter: ReposAdapter
    private var mData: MutableList<UserRepos> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        progressBar = findViewById(R.id.progress_bar)

        //通过动态代理加载GitHubService 接口
        val service = RetrofitFactory.getRetrofit().create(GitHubService::class.java)

        reposRecyclerView = findViewById(R.id.repos_recycler_view)

        reposRecyclerView.layoutManager = LinearLayoutManager(this)

        reposAdapter = ReposAdapter(mData)
        reposRecyclerView.adapter = reposAdapter


        progressBar.isVisible = true
        lifecycleScope.launch(Dispatchers.Main) {
            mData = service.listRepos("lixinxinlove")
            reposAdapter.mData = mData
            reposAdapter.notifyDataSetChanged()
            progressBar.isVisible = false
        }


    }
}


