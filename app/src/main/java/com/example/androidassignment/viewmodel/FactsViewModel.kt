package com.example.androidassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidassignment.data.OperationCallback
import com.example.androidassignment.model.Facts
import com.example.androidassignment.model.FactsDataSource

class FactsViewModel(private val repository: FactsDataSource):ViewModel() {

    private val _facts = MutableLiveData<List<Facts>>().apply { value = emptyList() }
    private var _maintitle = MutableLiveData<String>().apply { value = "" }
    val museums: LiveData<List<Facts>> = _facts
    val maintitle: LiveData<String> = _maintitle

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList



    fun loadFacts(){
        _isViewLoading.postValue(true)
        repository.retrievefacts(object:OperationCallback{
            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( obj)
            }

            override fun onSuccess(obj: Any?,title:String) {
                _isViewLoading.postValue(false)
           _maintitle.value= title

                if(obj!=null && obj is List<*>){
                    if(obj.isEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _facts.value= obj as List<Facts>
                    }
                }
            }
        })
    }

}