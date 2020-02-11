package com.example.androidassignment.data

interface OperationCallback {
    fun onSuccess(obj:Any?,title:String)
    fun onError(obj:Any?)
}