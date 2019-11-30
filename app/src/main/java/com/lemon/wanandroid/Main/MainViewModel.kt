package com.lemon.wanandroid.Main

import androidx.lifecycle.*
import com.lemon.wanandroid.Repository.UserRepository
import com.lemon.wanandroid.bean.User

/**
 * Created by Lemon on 2019/11/29.
 */
class MainViewModel constructor(savedStateHandle: SavedStateHandle,userRepository: UserRepository):ViewModel(){
    val userId: String = savedStateHandle["uid"]?:throw IllegalArgumentException("missing user id")
    val user : LiveData<User> = userRepository.getUser()
}