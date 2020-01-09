package com.lemon.wanandroid.ui.detailsweb
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import androidx.navigation.*
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import com.lemon.wanandroid.view.DetailsWebView
import kotlinx.android.synthetic.main.fragment_details_web.*

class DetailsWebFragment :BaseFragment(){
    lateinit var url : String
    lateinit var title : String
    lateinit var author : String
    override fun getContentViewId(): Int {
       return R.layout.fragment_details_web
    }

    override fun initView() {
        arguments?.let {
            url  = DetailsWebFragmentArgs.fromBundle(it).url
            title = DetailsWebFragmentArgs.fromBundle(it).title
            author = DetailsWebFragmentArgs.fromBundle(it).author
            tool_bar.title = title
            tool_bar.setNavigationOnClickListener { 
                view?.let { it ->
                    Navigation.findNavController(it).popBackStack() 
                }
            }
            web_view.setWebViewCallback(object : DetailsWebView.OnWebViewCallback{
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    tool_bar?.title = title
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                }

                override fun onLoadResource(view: WebView?, url: String) {
                }

                override fun onReceivedError(view: WebView?, message: String) {
                }

            })
            val navController = NavHostController(context!!)
            navController.enableOnBackPressed(false)
            Navigation.setViewNavController(view!!,navController)
            web_view.loadUrl(url)
            view?.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN  && keyCode == KeyEvent.KEYCODE_BACK){
                    if (web_view.canGoBack()){
                        web_view.goBack()
                        return@setOnKeyListener true
                    }
                    return@setOnKeyListener false
                }
                false
            }
        }
    }

    override fun initData() {

    }


}