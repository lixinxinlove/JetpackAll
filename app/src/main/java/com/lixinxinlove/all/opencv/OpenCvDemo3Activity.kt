package com.lixinxinlove.all.opencv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.CvType.CV_8UC4
import org.opencv.core.Mat
import kotlin.experimental.or

class OpenCvDemo3Activity : BaseActivity() {


    private lateinit var showResultImage: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo3)
        showResultImage = findViewById(R.id.show_result_image)
        initOpenCv()
    }


    private fun initOpenCv() {
        val sc = OpenCVLoader.initDebug()
        if (sc) {
            Log.e("CppActivity", "initDebug成功")
        } else {
            Log.e("CppActivity", "initDebug失败")
        }

    }

    fun onRemember(view: View) {
        val srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_demo3)
        val dstBitmap = ImageUtils.remin3(srcBitmap)
        showResultImage.setImageBitmap(dstBitmap)

    }


    /**
     * 怀旧滤镜
     */
    fun remin(bitmap: Bitmap): Bitmap {


        val mat = Mat()

        Utils.bitmapToMat(bitmap, mat)
        //获取通道数
        val channel = mat.channels()

        Log.e(TAG, "通道数=$channel")

        val width = mat.width()

        val height = mat.height()

        //保存一个像素点的信息
        var p: ByteArray = byteArrayOf(0, 0, 0, 0)


        var dstMat = Mat(mat.width(), mat.height(), CV_8UC4)


        var b = 0
        var g = 0
        var r = 0

        for (row in 0 until height) {

            for (col in 0 until width) {
                mat.get(row, col, p)
                b = p[0].toInt() and 0xff
                g = p[1].toInt() and 0xff
                r = p[2].toInt() and 0xff

                var AB: Int = (0.272 * r + 0.534 * g + 0.131 * b).toInt()
                var AG: Int = (0.349 * r + 0.686 * g + 0.168 * b).toInt()
                var AR: Int = (0.393 * r + 0.769 * g + 0.189 * b).toInt()

                if (AB > 255) {
                    AB = 255
                }
                if (AB < 0) {
                    AB = 0
                }

                if (AG > 255) {
                    AG = 255
                }
                if (AG < 0) {
                    AG = 0
                }

                if (AR > 255) {
                    AR = 255
                }
                if (AR < 0) {
                    AR = 0
                }



                p[0] = AB.toByte()
                p[1] = AG.toByte()
                p[2] = AR.toByte()


                dstMat.put(row, col, p)

            }
        }


        var resultBitmap =
            Bitmap.createBitmap(dstMat.width(), dstMat.height(), Bitmap.Config.ARGB_8888)

        Utils.matToBitmap(dstMat, resultBitmap)

        mat.release()
        dstMat.release()

        return resultBitmap

    }


}