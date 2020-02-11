package com.example.androidassignment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
import com.example.androidassignment.model.Facts

import com.squareup.picasso.Picasso



class FactsAdapter(private var factss:List<Facts>,private var context: Context):RecyclerView.Adapter<FactsAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_facts, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {


        val facts= factss[position]
        vh.layout_main.visibility= View.VISIBLE
        if(facts.imageHref==null&&facts.description==null&&facts.title==null)
        {
            vh.layout_main.visibility = View.GONE
        }


        //render
        vh.textViewName.text= facts.title
        vh.tv1desc.text= facts.description


        if(URLUtil.isValidUrl(facts.imageHref)) {
            try {

                if(facts.imageHref!=null) {

                    Picasso.with(context).load(facts.imageHref).error(R.drawable.noimage).into(vh.imageView)
                    vh.progressbar.visibility = View.GONE


                }
                else
                {
                    vh.progressbar.visibility = View.GONE

                }
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
        }

    }



    override fun getItemCount(): Int {
        return factss.size
    }

    fun update(data:List<Facts>){
        factss= data
        notifyDataSetChanged()
    }

    class MViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val imageView: ImageView = view.findViewById(R.id.image)
        val tv1desc:TextView= view.findViewById(R.id.tv_desc)
        val progressbar:ProgressBar= view.findViewById(R.id.progressbar)
        val layout_main:LinearLayout= view.findViewById(R.id.layout_main)
    }
}