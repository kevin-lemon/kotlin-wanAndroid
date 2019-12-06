package com.lemon.wanandroid.api

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(-1,error.message ?: "unknown error")
        }
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        data = body,
                        errorCode = 0,
                        errorMessage = "success"
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorCode = response.code()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorCode,errorMsg ?: "unknown error")
            }
        }
    }
}
class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiErrorResponse<T>(val errorCode: Int,val errorMessage: String) : ApiResponse<T>()
data class ApiSuccessResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMessage: String
)    : ApiResponse<T>()