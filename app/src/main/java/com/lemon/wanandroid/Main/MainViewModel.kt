package com.lemon.wanandroid.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lemon.wanandroid.bean.User
import java.util.*

/**
 * Created by Lemon on 2019/11/29.
 */
class MainViewModel(savedStateHandle: SavedStateHandle):ViewModel(){
    val userId: String = savedStateHandle["uid"]?:throw IllegalArgumentException("missing user id")
    val user : LiveData<User> = TODO()



object MainVMFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
}