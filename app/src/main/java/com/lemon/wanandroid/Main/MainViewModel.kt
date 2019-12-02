package com.lemon.wanandroid.Main

import android.app.Application
import androidx.lifecycle.*
import com.lemon.wanandroid.Repository.UserRepository
import com.lemon.wanandroid.bean.User

/**
 * Created by Lemon on 2019/11/29.
 */
open class MainViewModel constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    val userId: String = savedStateHandle["uid"]?:throw IllegalArgumentException("missing user id")
    val user : LiveData<User> = UserRepository().getUser()
}