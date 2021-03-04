package com.example.pdfprime.presentation.creatorCam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfprime.R
import com.example.pdfprime.presentation.myDocuments.DocumentViewHolder
import kotlinx.android.synthetic.main.list_item_page.view.*

class CreatorCamRecyclerViewAdapter(private var pages : List<Page>) : RecyclerView.Adapter<PageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_page,parent,false)
        return PageViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    fun setList(newList : List<Page>){
        pages = newList
        notifyDataSetChanged()
    }

}

class PageViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun bind(page: Page){
        view.apply {
            ivImage.setImageBitmap(page.image)
        }
    }
}