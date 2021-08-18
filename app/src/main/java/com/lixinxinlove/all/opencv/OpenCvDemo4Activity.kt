package com.lixinxinlove.all.opencv

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.core.Point
import org.opencv.core.Rect
import org.opencv.imgproc.Imgproc
import org.opencv.imgproc.Imgproc.COLOR_BGRA2GRAY
import org.opencv.objdetect.CascadeClassifier
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

import android.graphics.Bitmap





/**
 * 图片人像识别
 */
class OpenCvDemo4Activity : BaseActivity() {

    lateinit var faceImage: AppCompatImageView

    lateinit var classifier: CascadeClassifier


    var tlPoint: Point = Point(0.0, 0.0)
    var brPoint: Point = Point(0.0, 0.0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo4)
        faceImage = findViewById(R.id.face_image)
        initOpenCv()
        initClassifier()
    }


    fun onFindFace(view: View) {
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_demo04)
        val dstBitmap = faceDetect(bitmap)
        faceImage.setImageBitmap(dstBitmap)


//        val canvas = Canvas(dstBitmap)
//        canvas.drawColor(Color.TRANSPARENT)
//        val bitmapinner: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon_modify)
//        // icon_modify.png
//        // 定义矩阵对象
//        val matrix = Matrix()
//        // 缩放图像
//        matrix.postScale(6.4f, 6.4f)
//        val bitmapd = Bitmap.createBitmap(bitmapinner, 0, 0, bitmapinner.width, bitmapinner.height, matrix, true)
//        canvas.drawBitmap(bitmapd, tlPoint.x.toFloat(), tlPoint.y.toFloat(), null)
//
//
//        faceImage.setImageBitmap(dstBitmap)

    }


    fun faceDetect(bitmap: Bitmap): Bitmap {

        val matSrc = Mat()
        val matDst = Mat()
        val matGray = Mat()


        Utils.bitmapToMat(bitmap, matSrc)
        Imgproc.cvtColor(matSrc, matGray, COLOR_BGRA2GRAY)
        val faces = MatOfRect()

        classifier.detectMultiScale(matGray, faces, 1.05, 3, 0, Size(30.0, 30.0), Size())


        val faceList: MutableList<Rect> = faces.toList()


        matSrc.copyTo(matDst)

        if (faceList.size > 0) {
            for (rect in faceList) {
                Imgproc.rectangle(
                    matDst,
                    rect.tl(),
                    rect.br(),
                    Scalar(255.0, 0.0, 0.0, 255.0),
                    4,
                    0
                )


                tlPoint = rect.tl()
                brPoint = rect.br()

                Log.e("faceDetect-mat", "${matDst.width()}------${matDst.height()}")
                Log.e("faceDetect-rect", "${rect.tl()}------${rect.br()}")
            }
        }


        val dstBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

        Utils.matToBitmap(matDst, dstBitmap)
        matSrc.release()
        matDst.release()
        matGray.release()


        Log.e("faceDetect-dstBitmap", "${dstBitmap.width}------${dstBitmap.height}")


        return dstBitmap
    }


    fun initOpenCv() {
        if (OpenCVLoader.initDebug()) {
            Log.e("CppActivity", "initDebug成功")
        } else {
            Log.e("CppActivity", "initDebug失败")
        }
    }


    fun initClassifier() {
        val inputStream: InputStream =
            resources.openRawResource(R.raw.lbpcascade_frontalface_improved)
        val cascadeDir = getDir("cascade", Context.MODE_PRIVATE)
        val cascadeFile = File(cascadeDir, "lbpcascade_frontalface_improved.xml")
        val os = FileOutputStream(cascadeFile)
        inputStream.copyTo(os)

        inputStream.close()
        os.close()
        classifier = CascadeClassifier(cascadeFile.absolutePath)
    }

}

