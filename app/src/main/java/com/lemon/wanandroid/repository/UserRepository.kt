package com.lemon.wanandroid.repository

import Resource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.wanandroid.bean.User

/**
 * Created by Lemon on 2019/11/29.
 */
class UserRepository {

    fun getUser(): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User,User>(){
            override fun createCall(): LiveData<User> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun saveCallResult(item: User) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: User?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<User> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.asLiveData()
    }
}