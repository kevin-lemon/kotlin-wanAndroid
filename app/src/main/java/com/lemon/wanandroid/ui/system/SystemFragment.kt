package com.lemon.wanandroid.ui.system
import androidx.navigation.Navigation
import com.lemon.wanandroid.BaseFragment
import com.lemon.wanandroid.R
import kotlinx.android.synthetic.main.fragment_system.*

class SystemFragment : BaseFragment(){
    override fun getContentViewId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        btn.setOnClickListener {
            val action = SystemFragmentDirections.actionSystemFragmentToSystemDetailsFragment(1)
            Navigation.findNavController(view!!).navigate(action)
        }
    }

    override fun initData() {
    }

}