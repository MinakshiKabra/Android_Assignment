package com.example.androidassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassignment.R
import com.example.androidassignment.di.Injection
import com.example.androidassignment.model.Facts
import com.example.androidassignment.viewmodel.FactsViewModel
import com.example.androidassignment.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_facts.*
import kotlinx.android.synthetic.main.layout_error.*

class FactsActivity : AppCompatActivity() {

    private lateinit var viewModel: FactsViewModel
    private lateinit var adapter: FactsAdapter

    companion object {
        const val TAG= "CONSOLE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_facts)

        setupViewModel()
        setupUI()

        refresh.setOnClickListener {

            viewModel.loadFacts()


        }
        viewModel.loadFacts()
    }

    //ui
    private fun setupUI(){
        adapter= FactsAdapter(viewModel.museums.value?: emptyList(),this)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter


    }



    private fun setupViewModel(){


        viewModel = ViewModelProviders.of(this,ViewModelFactory(Injection.providerRepository())).get(FactsViewModel::class.java)
        viewModel.museums.observe(this,renderMuseums)
viewModel.maintitle.observe(this,renderTitle)
        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)

    }

    //observers
    private val renderMuseums= Observer<List<Facts>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility=View.GONE
        layoutEmpty.visibility=View.GONE
        adapter.update(it)
        main_title
    }

    private val renderTitle= Observer<String> {
        main_title.setText(it.toString())
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility=View.VISIBLE
        layoutEmpty.visibility=View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility=View.VISIBLE
        layoutError.visibility=View.GONE
    }



}
