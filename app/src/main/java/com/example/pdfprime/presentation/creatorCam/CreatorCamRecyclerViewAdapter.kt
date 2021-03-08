package com.example.pdfprime.presentation.creatorCam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfprime.R
import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.presentation.myDocuments.DocumentViewHolder
import kotlinx.android.synthetic.main.list_item_page.view.*

class CreatorCamRecyclerViewAdapter(private var pages : MutableList<Page>) : RecyclerView.Adapter<PageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_page,parent,false)
        return PageViewHolder(listItem,
            {pageSelected : Page -> onDeletePageClickListener(pageSelected)}
        )
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    fun setList(newList : MutableList<Page>){
        pages = newList
        notifyDataSetChanged()
    }

    fun onDeletePageClickListener(page : Page){
        pages.remove(page)
        for(pageNumber in 0 until pages.size){    //updating pageNumber
            pages[pageNumber].pageNumber = pageNumber
        }
        notifyDataSetChanged()
    }

    fun getPages() : MutableList<Page>{
        return pages
    }
}

class PageViewHolder(val view : View,private val clickListener : (Page)->Unit) : RecyclerView.ViewHolder(view){
    fun bind(page: Page){
        view.apply {
                ivImage.setImageBitmap(page.image)
                tvPageNumber.text = (page.pageNumber + 1).toString()
                tvDeletePage.setOnClickListener { clickListener(page)}
        }
    }
}