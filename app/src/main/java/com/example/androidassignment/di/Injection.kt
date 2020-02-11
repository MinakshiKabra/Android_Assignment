package com.example.androidassignment.di

import com.example.androidassignment.model.FactsDataSource
import com.example.androidassignment.model.FactsRepository

object Injection {

    //FactsRepository could be a singleton
    fun providerRepository():FactsDataSource{
        return FactsRepository()
    }
}