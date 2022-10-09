package com.jimzmx.pdfprime.presentation.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.R
import kotlinx.android.synthetic.main.list_item_language.view.*

class LanguageRecyclerViewAdapter(
    private var languages : List<Language>,
    private val clickListener : (Language)->Unit
) : RecyclerView.Adapter<LanguageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_language,parent,false)
        return LanguageViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(languages[position], clickListener)
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    fun setList(newList : List<Language>){
        languages = newList
        notifyDataSetChanged()
    }

}

class LanguageViewHolder(
    val view : View
) : RecyclerView.ViewHolder(view)
{
    fun bind(language : Language, clickListener: (Language) -> Unit){
        view.apply {
            ivLanImg.setImageResource(language.img)
            ivLanSelected.visibility = if(language.selected) View.VISIBLE else View.GONE
            setOnClickListener{ clickListener(language)}
        }
    }
}