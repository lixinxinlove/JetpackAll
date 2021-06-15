package com.lixinxinlove.all.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import kotlinx.android.synthetic.main.activity_permission.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PermissionActivity : BaseActivity() {


    val PERMISSION_REQUEST_CODE = 1;
    val REQUEST_TAKE_PHOTO = 1


    lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        tackPhone.setOnClickListener {
            checkCameraPermission()
        }

    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this, "com.lixinxinlove.all.fileProvider", it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            // val imageBitmap = data!!.extras!!.get("data") as Bitmap
            // imagePhone.setImageBitmap(imageBitmap)

            setPic()

        }
    }


    private fun setPic() {
        // Get the dimensions of the View
        val targetW: Int = imagePhone.width
        val targetH: Int = imagePhone.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }


        // 竖屏拍照的照片，直接使用的话，会旋转90度，下面代码把角度旋转过来
        val rotation: Int = getExifOrientation(currentPhotoPath)
        val m = Matrix()
        m.setRotate(rotation.toFloat())

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            val bitmapTemp =
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
            imagePhone.setImageBitmap(bitmapTemp)
        }
    }

    /**
     * 查询图片旋转角度
     *
     * @param filepath
     * @return
     */
    fun getExifOrientation(filepath: String?): Int { // YOUR MEDIA PATH AS
        // STRING
        var degree = 0
        var exif: ExifInterface? = null
        try {
            exif = ExifInterface(filepath!!)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        if (exif != null) {
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
            if (orientation != -1) {
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                    ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                    ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
                }
            }
        }
        return degree
    }


    private fun checkCameraPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    //performAction()
                    tackCamera()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    showInContextUI()
                }
                else -> {
                    // You can directly ask for the permission.
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CODE)
                }
            }
        } else {
            tackCamera()
        }
    }


    private fun showInContextUI() {
        Toast.makeText(this, "showInContextUI", Toast.LENGTH_LONG).show()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    tackCamera()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    Toast.makeText(this, "用户拒绝权限........", Toast.LENGTH_LONG).show()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
                Toast.makeText(this, "Ignore all other requests", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun tackCamera() {
        Toast.makeText(this, "马上打开相机", Toast.LENGTH_LONG).show()
        dispatchTakePictureIntent();
    }


}