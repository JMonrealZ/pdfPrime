package com.example.pdfprime.presentation.bottomSheetMenus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfprime.App
import com.example.pdfprime.R
import kotlinx.android.synthetic.main.bottom_sheet_item_list.view.*
import kotlinx.android.synthetic.main.bottom_sheet_item_list_rounded.view.*

class OptionBSRecyclerViewAdapter(private var options : List<BottomSheetOption>,
                                  private val clickListener : (BottomSheetOption)->Unit)
    : RecyclerView.Adapter<OptionBSViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionBSViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.bottom_sheet_item_list_rounded,parent,false)
        return OptionBSViewHolder(listItem, clickListener)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: OptionBSViewHolder, position: Int) {
        holder.bind(options[position])
    }

}

class OptionBSViewHolder(val view : View, private val clickListener:(BottomSheetOption)->Unit/*private val newDocInterface: NewDocInterface*/)
    : RecyclerView.ViewHolder(view){
    fun bind(option : BottomSheetOption){
        view.apply {
            sivOption.setImageResource(option.idIcon)
            tvOptionRounded.text = context.getString(option.idTxtOption)

        }
        view.setOnClickListener{
            clickListener(option)
        }
    }
}