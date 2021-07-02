package com.lixinxinlove.all.activity

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.adapter.ReposAdapter
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.databinding.ActivityRetrofitBinding
import com.lixinxinlove.all.http.GitHubService
import com.lixinxinlove.all.http.RetrofitFactory
import com.lixinxinlove.all.http.data.UserRepos
import com.lixinxinlove.all.vm.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import androidx.lifecycle.Observer as Observer1

@AndroidEntryPoint
class RetrofitActivity : BaseActivity(), CoroutineScope by MainScope() {

    private lateinit var reposAdapter: ReposAdapter
    private val mData: MutableList<UserRepos> = mutableListOf()


    //liveData
    private val retrofitViewModel: RetrofitViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = DataBindingUtil.setContentView<ActivityRetrofitBinding>(
            this,
            R.layout.activity_retrofit
        )
        binding.viewModel = retrofitViewModel
        binding.lifecycleOwner = this


        // setContentView(R.layout.activity_retrofit)
        // progressBar = findViewById(R.id.progress_bar)

        //通过动态代理加载GitHubService 接口
        val service = RetrofitFactory.getRetrofit().create(GitHubService::class.java)

        //reposRecyclerView = findViewById(R.id.repos_recycler_view)

        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
        reposAdapter = ReposAdapter(mData)
        binding.reposRecyclerView.adapter = reposAdapter



//        lifecycleScope.launch(Dispatchers.Main) {
//            mData = service.listRepos("lixinxinlove")
//            reposAdapter.mData = mData
//            reposAdapter.notifyDataSetChanged()
//            progressBar.isVisible = false
//        }


        binding.progressBar.isVisible = true
        retrofitViewModel.userReposLiveData.observe(this, {
            mData.clear()
            mData.addAll(retrofitViewModel.userReposLiveData.value as MutableList<UserRepos>)
            reposAdapter.notifyDataSetChanged()
            binding.progressBar.isVisible = false
        })

        retrofitViewModel.userRepos("lixinxinlove")

//                    launch {
//                mData = service.listRepos("lixinxinlove")
//                reposAdapter.mData = mData
//                reposAdapter.notifyDataSetChanged()
//                progressBar.isVisible = false
//            }

    }

    override fun onDestroy() {
        super.onDestroy()
        //cancel()
    }

}


