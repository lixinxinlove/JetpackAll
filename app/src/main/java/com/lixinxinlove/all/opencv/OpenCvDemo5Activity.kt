package com.lixinxinlove.all.opencv

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.JavaCameraView
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.core.MatOfRect
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.jar.Manifest

/**
 * 视频人脸检测
 */
class OpenCvDemo5Activity : BaseActivity() {

    private lateinit var javaCameraView: JavaCameraView
    private var isFrontCamera = false
    private lateinit var mRgba: Mat
    private lateinit var classifier: CascadeClassifier


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_cv_demo5)

        initOpenCv()

        javaCameraView = findViewById(R.id.java_camera_view)

        javaCameraView.setCvCameraViewListener(Lee())

        checkPermission()

        initClassifier()
    }


    fun onCamera(view: View) {

    }

    fun onSwich(view: View) {


        if (isFrontCamera) {
            javaCameraView.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_BACK)
            isFrontCamera = false
        } else {
            javaCameraView.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_FRONT)
            isFrontCamera = true
        }


        if (javaCameraView != null) {
            javaCameraView.disableView()
        }
        javaCameraView.enableView()

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


    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 1)
        } else {
            openCamera()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            openCamera()
        }

    }


    private fun openCamera() {
        javaCameraView.setCameraPermissionGranted()
    }


    private inner class Lee : CameraBridgeViewBase.CvCameraViewListener2 {

        override fun onCameraViewStarted(width: Int, height: Int) {
            mRgba = Mat()
        }

        override fun onCameraViewStopped() {
            mRgba.release()
        }

        override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
            mRgba = inputFrame!!.rgba()

            var mAbsoluteFaceSize = 0.0

            //人脸
            val mRelativeFaceSize = 0.2
            if (mAbsoluteFaceSize == 0.0) {

                val height = mRgba.rows()
                if (Math.round(height * mRelativeFaceSize) > 0) {
                    mAbsoluteFaceSize = Math.round(height * mRelativeFaceSize).toDouble()
                }

            }


            val faces = MatOfRect()
            if (classifier != null) {
                classifier.detectMultiScale(
                    mRgba, faces, 1.1, 2, 2,
                    Size(mRelativeFaceSize, mAbsoluteFaceSize)
                )
            }

            val faceArray = faces.toArray()

            val faceRectColor = Scalar(0.0, 255.0, 0.0, 255.0)


            for (i in 0 until faceArray.size) {
                Imgproc.rectangle(mRgba, faceArray[i].tl(), faceArray[i].br(), faceRectColor, 4)
            }


            return mRgba
        }


    }


}