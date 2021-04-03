package com.example.pdfprime.presentation.creatorCam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pdfprime.R
import kotlinx.android.synthetic.main.list_item_page.view.*

class CreatorCamRecyclerViewAdapter(private var pages : MutableList<Page>,
                                    private var pageOperationInterface: PageOperationInterface) : RecyclerView.Adapter<PageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_page,parent,false)
        return PageViewHolder(listItem,
            {pageSelected : Page -> onDeletePageClickListener(pageSelected)}
        )
    }

    private lateinit var deletedPage : Page

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    fun setList(newList : MutableList<Page>){
        if(newList.size == (pages.size - 1)){
            //A page has been deleted
            pages.remove(deletedPage)
            notifyItemRemoved(deletedPage.pageNumber)
            pages = newList
        }
        else {
            //A page has been added
            pages = newList
            notifyDataSetChanged()
        }
    }

    private fun onDeletePageClickListener(page : Page){
        deletedPage = page
        pageOperationInterface.onDeletePage(page)
    }

//    fun deletePage(page : Page){
//        pages.remove(page)
//        for(pageNumber in 0 until pages.size){    //updating pageNumber
//            pages[pageNumber].pageNumber = pageNumber
//        }
//        notifyItemRemoved(page.pageNumber)
//    }

    fun getPages() : MutableList<Page>{
        return pages
    }

    fun moveItem(from : Int, to : Int){
        notifyItemMoved(from,to)
    }
}

class PageViewHolder(val view : View,private val clickListener : (Page)->Unit) : RecyclerView.ViewHolder(view){
    fun bind(page: Page){
        view.apply {
            Glide.with(this).load(page.image).into(ivImage)
//                ivImage.setImageBitmap(page.image)
//                tvPageNumber.text = (page.pageNumber + 1).toString()
                tvDeletePage.setOnClickListener { clickListener(page)}
        }
    }
}