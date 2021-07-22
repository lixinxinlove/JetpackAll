package com.lixinxinlove.all.opencv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

/**
 * openCV
 */
class OpenCvDemo1Activity : BaseActivity() {


    private lateinit var image: ImageView
    private lateinit var image4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo1)
        if (OpenCVLoader.initDebug()) {
            Log.e("CppActivity", "initDebug成功")
        } else {
            Log.e("CppActivity", "initDebug失败")
        }
        image = findViewById(R.id.image3)
        image4 = findViewById(R.id.image4)


        var dstmat = Mat()
        val srcmat1 = Utils.loadResource(this, R.drawable.icon0)
        val srcmat2 = Utils.loadResource(this, R.drawable.icon1)


        findViewById<Button>(R.id.btn1).setOnClickListener {

            Core.bitwise_and(srcmat1, srcmat2, dstmat)
            val bitmap =
                Bitmap.createBitmap(dstmat.width(), dstmat.height(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(dstmat, bitmap)
            image.setImageBitmap(bitmap)



            srcmat1.release()
            srcmat2.release()
            dstmat.release()
        }

    }


    fun imageToGray(view: View) {

        val startTime = System.currentTimeMillis()
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon0)
        val src = Mat()
        val dst = Mat()
        Utils.bitmapToMat(bitmap, src)
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2GRAY)
        Utils.matToBitmap(dst, bitmap)
        image4.setImageBitmap(bitmap)
        src.release()
        dst.release()
        val endTime = System.currentTimeMillis()
        Log.e(TAG, "耗时=${endTime - startTime}")
    }


}