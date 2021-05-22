package com.jimzmx.pdfprime.presentation.myDocuments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.presentation.utils.Constants.DELIMITER
import com.jimzmx.pdfprime.presentation.utils.Multiselection
import kotlinx.android.synthetic.main.list_item_document.view.*
import java.io.File

class DocumentRecyclerViewAdapter(private var documents : List<Document>,
                                  private val clickListener : (Document)->Unit,
                                  private val multiselection: Multiselection) : RecyclerView.Adapter<DocumentViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_document,parent,false)
        return DocumentViewHolder(listItem,multiselection)
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(documents[position],clickListener)
    }

    fun setList(newList : List<Document>,context : Context){
        documents = newList
//        documents.forEach{
//            it.firstPage = Renderer.renderPage(context,it.name,0)
//        }
        notifyDataSetChanged()
    }

    fun getDocsSelected() : String{
        var docs = ""
        documents.forEach {
            if(it.isSelected)
//                docs += it.name + ","
                docs += it.name + DELIMITER
        }
//        docs = docs.substring(0,docs.length - 1)    //deleting final comma
        docs = docs.substring(0,docs.length - DELIMITER.length)    //deleting final delimiter
        return docs
    }


}

class DocumentViewHolder(val view : View, val multiselection: Multiselection) : RecyclerView.ViewHolder(view){
    fun bind(document : Document, clickListener: (Document) -> Unit){
        view.apply {
            tvDocName.text = document.name
            tvDocSize.text = if(document.size > 1024) (document.size / 1024).toString() + " MB" else  document.size.toString() + " KB"
            //ivFirstPage.setImageBitmap(document.firstPage)
            val directory = File(App.appContext.filesDir,App.storageFirstPagePdf)
            Glide.with(this)
                .load(File(directory,document.name.substring(0,document.name.length - 3) + "png"))
                .centerInside()
                .into(ivFirstPage)
            setOnClickListener{clickListener(document)}
            setOnLongClickListener(onLongClickListener)
            if(multiselection.isMultiselection()){
                ivSelected.visibility = View.VISIBLE
                var resource : Int = 0
                resource = if(document.isSelected)
                    R.drawable.ic_selected
                else
                    R.drawable.bg_unselected
                ivSelected.setImageResource(resource)
            }else{
                ivSelected.visibility = View.GONE
            }

        }
//        view.
//        view.
        //todo: Hay que pasar este fomrato string a xml strings
        //view.tvDateCre.text = document.datecre.toString()

    }

    private val onLongClickListener = View.OnLongClickListener() {
        multiselection.onMultiselection(adapterPosition)
        true
    }
}