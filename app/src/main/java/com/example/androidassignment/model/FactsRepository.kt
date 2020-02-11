package com.example.androidassignment.model

import android.util.Log
import com.example.androidassignment.data.ApiClient
import com.example.androidassignment.data.FactsResponse
import com.example.androidassignment.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG="CONSOLE"


class FactsRepository:FactsDataSource {

    private var call:Call<FactsResponse>?=null


    override fun retrievefacts(callback: OperationCallback) {
        call=ApiClient.build()?.facts()
        call?.enqueue(object :Callback<FactsResponse>{
            override fun onFailure(call: Call<FactsResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<FactsResponse>, response: Response<FactsResponse>) {
                response?.body()?.let {
                    if(response.isSuccessful ){
                        Log.v(TAG, "data ${it.rows}")

                        callback.onSuccess(it.rows, it.title!!)
                    }else{
                        callback.onError(it.title)
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}