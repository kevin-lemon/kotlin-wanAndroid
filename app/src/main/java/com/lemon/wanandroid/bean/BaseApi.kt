package com.lemon.wanandroid.bean

/**
 * Created by Lemon on 2019/12/7.
 */
data class BaseApi<T>(
    val data: T,
    val errorCode: Int,
    val errorMessage: String
)