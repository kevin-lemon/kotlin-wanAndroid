/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lemon.wanandroid.utils


import androidx.lifecycle.LiveData
import com.lemon.wanandroid.api.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<T>(private val responseType: Type) :
    CallAdapter<T, LiveData<T>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): LiveData<T> {
        return object : LiveData<T>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<T> {
                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            if (response.isSuccessful) {
                                val body = response.body()
                                if (body == null || response.code() == 204) {
                                    val value = ApiResponse<T>(
                                        null,
                                        -1,
                                        response.errorBody()?.toString() ?: ""
                                    ) as T
                                    postValue(value)
                                } else {
                                    postValue(response.body())
                                }
                            } else {
                                val value = ApiResponse<T>(
                                    null,
                                    -1,
                                    response.errorBody()?.toString() ?: ""
                                ) as T
                                postValue(value)
                            }
                        }
                        override fun onFailure(call: Call<T>, throwable: Throwable) {
                            val value = ApiResponse<T>(null, -1, throwable.message ?: "") as T
                            postValue(value)
                        }
                    })
                }
            }
        }
    }
}
