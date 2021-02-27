package com.example.pdfprime.presentation.myDocuments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfprime.R
import com.example.pdfprime.data.model.Document
import com.example.pdfprime.presentation.utils.Renderer
import kotlinx.android.synthetic.main.list_item_document.view.*

class DocumentRecyclerViewAdapter(private var documents : List<Document>,
                                  private val clickListener : (Document)->Unit) : RecyclerView.Adapter<DocumentViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_document,parent,false)
        return DocumentViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(documents[position],clickListener)
    }

    fun setList(newList : List<Document>,context : Context){
        documents = newList
        documents.forEach{
            it.firstPage = Renderer.renderPage(context,it.name,0)
        }
        notifyDataSetChanged()
    }

}

class DocumentViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun bind(document : Document, clickListener: (Document) -> Unit){
        view.apply {
            tvDocName.text = document.name
            tvDocSize.text = if(document.size > 1024) (document.size / 1024).toString() + " MB" else  document.size.toString() + " KB"
            ivFirstPage.setImageBitmap(document.firstPage)
            setOnClickListener{clickListener(document)}
        }
//        view.
//        view.
        //todo: Hay que pasar este fomrato string a xml strings
        //view.tvDateCre.text = document.datecre.toString()

    }
}