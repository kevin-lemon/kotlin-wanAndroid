package com.lemon.wanandroid.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.bean.User

/**
 * Created by Lemon on 2019/11/29.
 */
class UserRepository {

    fun getUser(): LiveData<User>{
        var data = MutableLiveData<User>()
        data.value = User("Wxk",24)
        Log.d("MainActivity","getUser")
        return data
    }
}