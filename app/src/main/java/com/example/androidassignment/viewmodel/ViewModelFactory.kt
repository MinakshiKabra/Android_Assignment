package com.example.androidassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidassignment.model.FactsDataSource

class ViewModelFactory(private val repository:FactsDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FactsViewModel(repository) as T
    }
}