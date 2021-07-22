package com.lixinxinlove.all.opencv

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.lixinxinlove.all.R
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat

/**
 * openCV
 */
class OpenCvDemo1Activity : AppCompatActivity() {


    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo1)
        if (OpenCVLoader.initDebug()) {
            Log.e("CppActivity", "initDebug成功")
        } else {
            Log.e("CppActivity", "initDebug失败")
        }
        image = findViewById(R.id.image3)


        var dstmat = Mat()


        val srcmat1 = Utils.loadResource(this, R.drawable.icon0)
        val srcmat2 = Utils.loadResource(this, R.drawable.icon1)


        findViewById<Button>(R.id.btn1).setOnClickListener {

            Core.bitwise_and(srcmat1, srcmat2, dstmat)
            val bitmap = Bitmap.createBitmap(dstmat.width(), dstmat.height(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(dstmat, bitmap)

            image.setImageBitmap(bitmap)


        }


    }
}