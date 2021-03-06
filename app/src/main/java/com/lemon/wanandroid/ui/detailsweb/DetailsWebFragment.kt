package com.lemon.wanandroid.ui.detailsweb

import android.webkit.WebSettings
import android.widget.LinearLayout
import androidx.navigation.Navigation
import com.just.agentweb.AgentWeb
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import kotlinx.android.synthetic.main.fragment_details_web.*


class DetailsWebFragment : BaseFragment() {
    lateinit var url: String
    lateinit var title: String
    lateinit var author: String
    lateinit var mAgentWeb: AgentWeb
    override fun getContentViewId(): Int {
        return R.layout.fragment_details_web
    }

    override fun initView() {
        arguments?.let {
            url = DetailsWebFragmentArgs.fromBundle(it).url
            title = DetailsWebFragmentArgs.fromBundle(it).title
            author = DetailsWebFragmentArgs.fromBundle(it).author
        }
        tool_bar.title = title
        tool_bar.setNavigationOnClickListener {
            if (!mAgentWeb.back()) {
                Navigation.findNavController(view!!).popBackStack()
            }
        }
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(web_view, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setMainFrameErrorView(R.layout.layout_web_load_error, R.id.error_retry)
            .createAgentWeb()
            .ready()
            .go(url)

        val mWebView = mAgentWeb.webCreator.webView
        val mSettings = mWebView.settings
        mSettings.blockNetworkImage = false
        mSettings.setAppCacheEnabled(false)
        mSettings.domStorageEnabled = false
        mSettings.databaseEnabled = false
        mSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        mSettings.javaScriptEnabled = true
        mSettings.setSupportZoom(true)
        mSettings.builtInZoomControls = true
        mSettings.displayZoomControls = false
        mSettings.useWideViewPort = true
        mSettings.loadWithOverviewMode = true
        mSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
    }

    override fun initData() {

    }


}