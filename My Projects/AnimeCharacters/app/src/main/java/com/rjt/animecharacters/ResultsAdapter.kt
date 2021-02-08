package com.rjt.animecharacters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_results.view.*

class ResultsAdapter(val context: Context, var mList: List<Result>): RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_results, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = mList.get(position)


        holder.bindView(category, position)

    }

    fun setData(list: ArrayList<Result>) {
        mList = list

        notifyDataSetChanged()

    }



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(result: Result, position: Int){

            itemView.title.text = result.title
            itemView.synopsis.text = result.synopsis
            Picasso.get().load(result.image_url).into(itemView.image)
        }

    }
}
