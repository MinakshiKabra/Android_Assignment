package com.example.androidassignment.model

import com.example.androidassignment.data.OperationCallback

interface FactsDataSource {

    fun retrievefacts(callback: OperationCallback)
    fun cancel()
}