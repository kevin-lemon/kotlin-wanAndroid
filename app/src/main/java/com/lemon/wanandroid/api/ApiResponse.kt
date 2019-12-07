package com.lemon.wanandroid.api

/**
 * Created by Lemon on 2019/12/7.
 */
data class ApiResponse<T>(
    val data: T?,
    val errorCode: Int,
    val errorMsg: String
)