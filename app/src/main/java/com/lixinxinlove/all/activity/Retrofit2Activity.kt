package com.lixinxinlove.all.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.http.GitHubService
import com.lixinxinlove.all.http.RetrofitFactory
import com.lixinxinlove.all.http.data.RespData
import com.lixinxinlove.all.http.data.UserRepos
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Retrofit2Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)

        val service = RetrofitFactory.getRetrofit().create(GitHubService::class.java)
        // val call = service.listRepos1("lixinxinlove")

        service.listRepos1("lixinxinlove")
            .enqueue(object : MyCallback<MutableList<UserRepos>>() {

                override fun onSuccess(t: MutableList<UserRepos>) {
                    Log.e(TAG, "${t.size}")
                }

                override fun onError() {
                }

                override fun onFailure() {
                }
            })

//        call.enqueue(object : retrofit2.Callback<MutableList<UserRepos>> {
//
//
//            override fun onResponse(
//                call: Call<MutableList<UserRepos>>,
//                response: Response<MutableList<UserRepos>>
//            ) {
//                Log.e(TAG, "${response.body()!!.size}")
//            }
//
//            override fun onFailure(call: Call<MutableList<UserRepos>>, t: Throwable) {
//
//            }
//        })

    }


    abstract class MyCallback<T> : retrofit2.Callback<RespData<T>> {

        abstract fun onSuccess(t: T)

        abstract fun onError()

        abstract fun onFailure()

        override fun onResponse(call: Call<RespData<T>>, response: Response<RespData<T>>) {
            if (response.isSuccessful) {
                if (response.body()?.code == "1") {
                    onSuccess(response.body()!!.data)
                } else {

                }
            } else {
                onFailure()
            }

        }

        override fun onFailure(call: Call<RespData<T>>, t: Throwable) {

        }
    }
}