package com.lixinxinlove.all.activity

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import okio.*
import java.io.File
import java.nio.charset.Charset


/**
 * okio 使用
 */
class OKIOActivity : BaseActivity() {

    private lateinit var text: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_k_i_o)

        text = findViewById(R.id.text)

        var file: File = File(filesDir.path.toString() + "/test.txt")
        writeLines(file)
        text.setText( readLines(file))

    }


    fun readLines(file: File) :String{
        //读文件
        val fileContent = file.source().buffer().readString(Charset.forName("utf-8"))
        return fileContent
    }

    fun writeLines(file: File) {
        //写文件
        file.sink().buffer().writeString("write sth", Charset.forName("utf-8")).close()
    }


}