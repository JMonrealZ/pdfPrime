package com.example.pdfprime.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.App
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.Utilities
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle

class SettingsViewModel : ViewModel() {
    var pagesSizes : MutableLiveData<MutableList<PageSize>> = MutableLiveData(App.pageSizes)

    fun updatePageSizeDefault(position : Int){
        val newPagesSize = pagesSizes.value
        newPagesSize!!.forEach { it.isSelected = false }
        newPagesSize[position].isSelected = true
        Utilities.Shp.setString(Constants.PAGE_SIZE,newPagesSize[position].pdRectangleName)
        pagesSizes.postValue(newPagesSize)
    }

    init {
        //Loading custom page sizes and setting default
        val pageSizeDefault = Utilities.Shp.getString(Constants.PAGE_SIZE)
        val newPagesSizes = pagesSizes.value
        if(pageSizeDefault == "")
            newPagesSizes!![0].isSelected = true
        else
            newPagesSizes?.forEach {
                if(it.pdRectangleName == pageSizeDefault)
                    it.isSelected = true
                else
                    it.isSelected = false
            }
        pagesSizes.postValue(newPagesSizes)
    }
}