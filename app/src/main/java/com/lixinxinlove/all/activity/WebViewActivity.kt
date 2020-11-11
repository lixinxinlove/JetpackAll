package com.lixinxinlove.all.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.lixinxinlove.all.R
import com.lixinxinlove.all.base.BaseActivity
import com.lixinxinlove.all.http.MyJavascriptInterface
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : BaseActivity() {

    val url = "https://booking.jinmao88.com//my/score?from=app"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setWebView()

        CookieSyncManager.createInstance(this)
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.removeSessionCookie() //移除
        cookieManager.removeAllCookie()

        cookieManager.setCookie(
            getDomain(url),
            "cookie:MTAyODU4LDRhZDA2N2FkOTExN2FhNTc4ZTgzYTM4ZTdmYTI0Y2Y5"
        ) //cookies是在HttpClient中获得的cookie

        CookieSyncManager.getInstance().sync()


        web_view.loadUrl(url)

    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        web_view.settings.allowFileAccess = true
        // 如果访问的页面中有Javascript，则webview必须设置支持Javascript
        web_view.settings.javaScriptEnabled = true
        web_view.settings.cacheMode = WebSettings.LOAD_NO_CACHE;
        // 添加调用js获取分享内容
        web_view.addJavascriptInterface( MyJavascriptInterface(), "local_obj")
        web_view.settings.allowFileAccess = true
        web_view.settings.setAppCacheEnabled(true)
        web_view.settings.domStorageEnabled = true
        web_view.settings.databaseEnabled = true
        web_view.settings.loadsImagesAutomatically = true
        web_view.settings.savePassword = true
        web_view.settings.builtInZoomControls=true
        web_view.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                Log.e("请求链接", request.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                getCookies()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

            }

        }


        web_view.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
            }
        }
        registerForContextMenu(web_view)
    }


    /**
     * 获取URL的域名
     */
    private fun getDomain(url1: String): String? {
        var url = url1
        url = url.replace("http://", "").replace("https://", "")
        if (url.contains("/")) {
            url = url.substring(0, url.indexOf('/'))
        }

        Log.e("getDomain", url)

        return url
    }




    private fun getCookies(){
        CookieSyncManager.createInstance(this)
        val cookieManager = CookieManager.getInstance()
        val tempcookie = cookieManager.getCookie(getDomain(url))

        Log.e("getCookies",tempcookie)
    }

}