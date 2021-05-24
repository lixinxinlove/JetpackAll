package com.lixinxinlove.all.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity

class FlutterActivity : BaseActivity() {

    // private lateinit var mFlutter1Engine: FlutterEngine
    // private lateinit var mFlutter1View: FlutterView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter)


//        mFlutter1Engine = FlutterEngineCache.getInstance().get("my_engine_id")!!
//        mFlutter1View = createFlutterView()
//        mFlutter1View.attachToFlutterEngine(mFlutter1Engine)


    }


    override fun onResume() {
        super.onResume()
       //  mFlutter1Engine.getLifecycleChannel().appIsResumed()
    }

    override fun onPause() {
        super.onPause()
        //mFlutter1Engine.getLifecycleChannel().appIsInactive()
    }

    override fun onStop() {
        super.onStop()
        //mFlutter1Engine.getLifecycleChannel().appIsPaused()
    }


//    private fun createFlutterView(): FlutterView {
//        val flutterView = FlutterView(this)
//        val lp = FrameLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        val flContainer: FrameLayout = findViewById(R.id.fl_flutter)
//        flContainer.addView(flutterView, lp)
//        flContainer.visibility = View.INVISIBLE
//        val listener: FlutterUiDisplayListener = object : FlutterUiDisplayListener {
//            override fun onFlutterUiDisplayed() {
//                flContainer.visibility = View.VISIBLE
//            }
//
//            override fun onFlutterUiNoLongerDisplayed() {
//
//            }
//
//        }
//        flutterView.addOnFirstFrameRenderedListener(listener)
//        return flutterView
//    }


}