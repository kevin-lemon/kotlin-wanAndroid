package com.lemon.wanandroid.UI.main

import androidx.lifecycle.*
import com.lemon.wanandroid.bean.Resource
import com.lemon.wanandroid.repository.UserRepository
import com.lemon.wanandroid.bean.User

/**
 * Created by Lemon on 2019/11/29.
 */
open class MainViewModel constructor(savedStateHandle: SavedStateHandle): ViewModel() {
    val userId: String = savedStateHandle["uid"]?:""
    val resourceUser : LiveData<Resource<User>> = UserRepository().getUser()
}