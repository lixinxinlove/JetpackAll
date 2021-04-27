package com.lixinxinlove.all.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class DownloadImageActivity : BaseActivity() {

    private val url =
        "https://img40.51tietu.net/pic/2017-030614/201703061428412okbwggudfp630650.jpg"

    private lateinit var testImage: AppCompatImageView

    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_image)


        testImage = findViewById(R.id.test_image)


        testImage.setOnClickListener {
            lifecycleScope.launch {
                bitmap = downloadImg(url)
                if (bitmap != null) {
                    testImage.setImageBitmap(bitmap)
                }
            }
        }
    }


    private suspend fun downloadImg(url: String): Bitmap? {

        var bitmap: Bitmap?
        val request: Request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        withContext(Dispatchers.IO) {
            val mResponse: Response = client.newCall(request).execute()
            bitmap = if (mResponse.isSuccessful) {
                val byteBody: ByteArray = mResponse.body!!.bytes()
                BitmapFactory.decodeByteArray(byteBody, 0, byteBody.size)
            } else {
                null
            }
        }
        return bitmap
    }
}