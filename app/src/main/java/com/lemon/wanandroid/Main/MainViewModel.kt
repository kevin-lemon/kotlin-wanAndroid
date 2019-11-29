package com.lemon.wanandroid.Main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lemon.wanandroid.bean.User
import java.util.*

/**
 * Created by wxk on 2019/11/29.
 */
class MainViewModel(savedStateHandle: SavedStateHandle):ViewModel(){

    val userId: String = savedStateHandle["uid"]?:throw IllegalArgumentException("missing user id")
    val user : User = TODO()
}