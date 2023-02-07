package com.tunahan.calculatorappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.calculatorappkotlin.R
import com.tunahan.calculatorappkotlin.model.History

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    var historyList= ArrayList<History>()

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
            )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}