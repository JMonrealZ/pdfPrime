package com.jimzmx.pdfprime.presentation.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.presentation.entities.Page
import kotlinx.android.synthetic.main.list_item_page.view.*

class CreatorCamRecyclerViewAdapter(private var pages : MutableList<Page>,
                                    private var iPageOperation: IPageOperation) : RecyclerView.Adapter<PageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_page,parent,false)
        return PageViewHolder(listItem,
            {pageSelected : Int -> onDeletePageClickListener(pageSelected)},
            {pageSelected : Page? -> onClickPageListener(pageSelected)}
        )
    }

    private var deletedPage : Int = 0   //Backuping deleted page after updating ViewModel
    private var from : Int = 0      //Backuping from in moving action
    private var to : Int = 0        //Backuping to in moving action

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    fun setList(newList : MutableList<Page>){
        when(pages.size - newList.size){
            1 ->{ //A page has been deleted
                pages.removeAt(deletedPage)
                notifyItemRemoved(deletedPage)
            }
            0 ->{ //A page has been cropped or moved
                pages = newList
                if(from == to)
                    notifyDataSetChanged()
                else
                    notifyItemMoved(from,to)
            }
            -1 -> { //A page has been added
                pages = newList
                notifyDataSetChanged()
            }
            else -> {
                pages = newList
                notifyDataSetChanged()
            }

        }

//        if(newList.size == (pages.size - 1)){
//            //A page has been deleted
//            pages.removeAt(deletedPage)
//            notifyItemRemoved(deletedPage)
//        }
//        else {
//            //A page has been added
//            pages = newList
//            notifyDataSetChanged()
//        }
    }

    private fun onDeletePageClickListener(pageNumber : Int){
        deletedPage = pageNumber
        iPageOperation.onDeletePage(pageNumber)
    }

    private fun onClickPageListener(page : Page?){
        iPageOperation.onClickPage(page)
    }

    fun getPages() : MutableList<Page>{
        return pages
    }

    fun moveItem(from : Int, to : Int){
        this.from = from
        this.to = to
        iPageOperation.onMovePage(from,to)
    }
}

class PageViewHolder(val view : View,
                     private val clickListenerDelete : (Int)->Unit,
                     private val clickListener : (Page?)->Unit
) : RecyclerView.ViewHolder(view){
    fun bind(page: Page){
        view.apply {
            Glide.with(this).load(
                if(page.image != null) page.image
                else page.imageUri
            ).into(ivImagePage)
            tvDeletePage.setOnClickListener { clickListenerDelete(/*page*/adapterPosition)}
            ivImagePage.setOnClickListener{clickListener(page)} //(click listener to the complete view)
        }
    }
}