package com.lixinxinlove.all.http.okhttp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class DownloadUtil private constructor() {

    private var okHttpClient: OkHttpClient = OkHttpClient()

    companion object {
        val instance: DownloadUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DownloadUtil()
        }
    }


    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称，后面记得拼接后缀，否则手机没法识别文件类型
     * @param listener     下载监听
     */
    fun download(
        url: String,
        destFileDir: String,
        destFileName: String,
        listener: OnDownloadListener
    ) {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        try {
            val response: Response = client.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //异步请求
        okHttpClient.newCall(request).enqueue(object : Callback {


            override fun onFailure(call: Call, e: IOException) {
                // 下载失败监听回调
                listener.onDownloadFailed(e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {

                var `is`: InputStream? = null
                val buf = ByteArray(2048)
                var len = 0
                var fos: FileOutputStream? = null

                //储存下载文件的目录
                val dir = File(destFileDir)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val file = File(dir, destFileName)
                try {
                    `is` = response.body!!.byteStream()
                    val total: Long = response.body!!.contentLength()
                    fos = FileOutputStream(file)
                    var sum: Long = 0
                    while (`is`.read(buf).also { len = it } != -1) {
                        fos.write(buf, 0, len)
                        sum += len.toLong()
                        val progress = (sum * 1.0f / total * 100).toInt()
                        //下载中更新进度条
                        listener.onDownloading(progress)
                    }
                    fos.flush()
                    //下载完成
                    listener.onDownloadSuccess(file)
                } catch (e: Exception) {
                    listener.onDownloadFailed(e)
                } finally {
                    try {
                        if (`is` != null) {
                            `is`.close()
                        }
                        if (fos != null) {
                            fos.close()
                        }
                    } catch (e: IOException) {
                    }
                }
            }


        })
    }


    interface OnDownloadListener {
        /**
         * 下载成功之后的文件
         */
        fun onDownloadSuccess(file: File)

        /**
         * 下载进度
         */
        fun onDownloading(progress: Int)

        /**
         * 下载异常信息
         */
        fun onDownloadFailed(e: Exception?)
    }


    suspend fun downloadImg(url: String): Bitmap? {
        
        val request: Request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        val mResponse: Response = client.newCall(request).execute()
        return if (mResponse.isSuccessful) {
            val byteBody: ByteArray = mResponse.body!!.bytes()
            BitmapFactory.decodeByteArray(byteBody, 0, byteBody.size)
        } else {
            null
        }
    }
}