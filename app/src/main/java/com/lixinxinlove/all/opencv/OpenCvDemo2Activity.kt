package com.lixinxinlove.all.opencv

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

/**
 * openCv 图片区域切割
 */
class OpenCvDemo2Activity : BaseActivity() {


    private lateinit var srcMat: Mat
    private lateinit var dstMat: Mat
    private lateinit var hsvMat: Mat
    private lateinit var resultBitmap: Bitmap
    private lateinit var image: ImageView
    private lateinit var tv: TextView


    private val contours: MutableList<MatOfPoint> = mutableListOf()
    private var contoursCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo2)
        image = findViewById(R.id.image_view1)
        tv = findViewById(R.id.tv_type)
        if (OpenCVLoader.initDebug()) {
            Log.e("CppActivity", "initDebug成功")
        } else {
            Log.e("CppActivity", "initDebug失败")
        }


    }


    /**
     * 切割
     */
    fun onRect(view: View) {
        srcMat = Utils.loadResource(this, R.drawable.icon_demo2)
        val rect = Rect(40, 50, 750, 450)
        dstMat = Mat(srcMat, rect)
        resultBitmap = Bitmap.createBitmap(dstMat.width(), dstMat.height(), Bitmap.Config.ARGB_8888)
        Imgproc.cvtColor(dstMat, dstMat, Imgproc.COLOR_BGR2RGB)
        Utils.matToBitmap(dstMat, resultBitmap)
        image.setImageBitmap(resultBitmap)
    }


    /**
     * 颜色分隔
     */
    fun colorChange(view: View) {
        hsvMat = Mat()
        Imgproc.cvtColor(dstMat, hsvMat, Imgproc.COLOR_RGB2HSV)
        Core.inRange(hsvMat, Scalar(160.0, 90.0, 90.0), Scalar(179.0, 255.0, 255.0), hsvMat)

        //开运算
        val kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(3.0, 3.0))
        Imgproc.morphologyEx(hsvMat, hsvMat, Imgproc.MORPH_OPEN, kernel)
        //闭运算
        Imgproc.morphologyEx(hsvMat, hsvMat, Imgproc.MORPH_CLOSE, kernel)

        Utils.matToBitmap(hsvMat, resultBitmap)
        image.setImageBitmap(resultBitmap)
    }


    /**
     * 轮廓
     */
    fun findContours(view: View) {
        val outMat = Mat()
        Imgproc.findContours(
            hsvMat,
            contours,
            outMat,
            Imgproc.RETR_EXTERNAL,
            Imgproc.CHAIN_APPROX_SIMPLE
        )
        contoursCount = contours.size

        Log.e(TAG, "轮廓数量$contoursCount")


        if (contoursCount > 0) {

            //绘制轮廓
            Imgproc.drawContours(dstMat, contours, -1, Scalar(0.0, 0.0, 250.0), 4)

        }

        Utils.matToBitmap(dstMat, resultBitmap)
        image.setImageBitmap(resultBitmap)

    }


    /**
     * 获取轮廓的类型
     */

    fun onApproxPolyDP(view: View) {

        var c = 0
        var s = 0
        var j = 0

        contours.forEach {
            var contour2f = MatOfPoint2f(*it.toArray())
            var epsilon = 0.04 * Imgproc.arcLength(contour2f, true)
            var approxCurve = MatOfPoint2f()
            Imgproc.approxPolyDP(contour2f, approxCurve, epsilon, true)
            Log.e(TAG, "approxCurve.size=${approxCurve.total()}")

            if (approxCurve.rows() == 3) {
                s++
            }else if (approxCurve.rows() == 4){
                j++
            }else{
                c++
            }
        }

        tv.text = "轮廓=$contoursCount\n圆形=$c\n三角形=$s\n矩形=$j"

    }


    override fun onDestroy() {
        super.onDestroy()
        srcMat.release()
        dstMat.release()
        hsvMat.release()
        resultBitmap.recycle()
    }



}