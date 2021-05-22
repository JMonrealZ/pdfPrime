package com.jimzmx.pdfprime.presentation.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.jimzmx.pdfprime.R

class PageSizeAdapter(context: Context, resource: Int, objects: MutableList<PageSize>) :
    ArrayAdapter<PageSize>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent,true)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent,false)
    }

    private fun initView(position : Int, convertView : View?, parent : ViewGroup, singleView : Boolean) : View{
        //We store
        var convertView2 = convertView
        if(convertView2 == null){
            convertView2 = LayoutInflater.from(context).inflate(
                R.layout.list_item_page_size,parent,false
            )
        }
        val ivPageSizeSelected = convertView2!!.findViewById<ImageView>(R.id.ivPageSizeSelected)
        val tvPageSizeName = convertView2.findViewById<TextView>(R.id.tvPageSizeName)
        val tvPageSizeDimen = convertView2.findViewById<TextView>(R.id.tvPageSizeDimen)
        val ivPopup = convertView2.findViewById<ImageView>(R.id.ivPopup)

        val pageSizeSelected = getItem(position)

        var checkedVisibility = if(singleView) View.GONE else if(pageSizeSelected!!.isSelected) View.VISIBLE else View.INVISIBLE
        ivPageSizeSelected.visibility = checkedVisibility
        tvPageSizeName.text = pageSizeSelected!!.visibleName
        tvPageSizeDimen.text = "prueba"
        ivPopup.visibility = if(singleView) View.VISIBLE else View.INVISIBLE

        return convertView2
    }
}