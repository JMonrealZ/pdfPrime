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

class CreatorCamRecyclerViewAdapter(private var pages : List<Page>) : RecyclerView.Adapter<PageViewHolder>(){
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

    fun setList(newList : List<Page>){
        pages = newList
        notifyDataSetChanged()
    }

    fun onDeletePageClickListener(page : Page){
        pages[page.pageNumber].delete = true
        notifyDataSetChanged()
    }
}

class PageViewHolder(val view : View,private val clickListener : (Page)->Unit) : RecyclerView.ViewHolder(view){
    fun bind(page: Page){
        view.apply {
            when(page.delete) {
                true -> ivImage.setImageBitmap(null)
                false -> ivImage.setImageBitmap(page.image)
            }
            tvPageNumber.text = (page.pageNumber + 1).toString()
            tvDeletePage.setOnClickListener{clickListener(page)}
        }
    }
}