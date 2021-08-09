package com.lixinxinlove.all.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.permissionx.guolindev.PermissionX

class Camera2Activity : BaseActivity() {

    lateinit var mAppCompatImageView: AppCompatImageView
    lateinit var mTextureView: TextureView


    lateinit var cameraDevice: CameraDevice
    lateinit var textureViewSurface: Surface
    lateinit var imageReaderSurface: Surface
    lateinit var imageReader: ImageReader
    lateinit var cameraCaptureSession: CameraCaptureSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)

        mAppCompatImageView = findViewById(R.id.pr_image)
        mTextureView = findViewById(R.id.textureView)


        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "核心基础都是基于这些权限", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {

                } else {
                    Toast.makeText(this, "这些权限被拒绝: $deniedList", Toast.LENGTH_LONG).show()
                }
            }


        mTextureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                textureViewSurface = Surface(mTextureView.surfaceTexture)
                openCamera()
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
            }

        }

    }


    @SuppressLint("MissingPermission")
    private fun openCamera() {
        imageReader = ImageReader.newInstance(mTextureView.width, mTextureView.height, ImageFormat.JPEG, 2)
        imageReader.setOnImageAvailableListener(imageAvailable, null)
        imageReaderSurface = imageReader.surface
        val cameraManage = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val ids = cameraManage.cameraIdList
        cameraManage.openCamera(ids[0], cameraStateCallback, null)
    }


    private val imageAvailable = ImageReader.OnImageAvailableListener {
        Log.e("imageAvailable", it.toString())
        val image = it.acquireLatestImage()
        val buffer = image.planes[0].buffer
        val bytesN = buffer.remaining()
        var bytes = ByteArray(bytesN)
        buffer.get(bytes)
        image.close()
        val bi=BitmapFactory.decodeByteArray(bytes,0,bytesN)
        mAppCompatImageView.setImageBitmap(bi)

    }


    private val cameraStateCallback: CameraDevice.StateCallback =
        object : CameraDevice.StateCallback() {
            override fun onOpened(camera: CameraDevice) {
                cameraDevice = camera
                cameraDevice.createCaptureSession(listOf(imageReaderSurface, textureViewSurface), sessionStateCallback, null)

            }

            override fun onDisconnected(p0: CameraDevice) {
            }

            override fun onError(p0: CameraDevice, p1: Int) {
            }
        }


    private val sessionStateCallback: CameraCaptureSession.StateCallback =
        object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                cameraCaptureSession = session

                val requestBuilder =
                    cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                requestBuilder.addTarget(textureViewSurface)

                val request = requestBuilder.build()
                cameraCaptureSession.setRepeatingRequest(request, null, null)
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {


            }
        }


    fun onTake(view: View) {

        val requestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
        requestBuilder.addTarget(imageReaderSurface)
        requestBuilder.set(CaptureRequest.JPEG_ORIENTATION,90)
        val request = requestBuilder.build()
        cameraCaptureSession.capture(request, null, null)
    }

}