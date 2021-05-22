package com.jimzmx.pdfprime.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.presentation.utils.Constants
import com.jimzmx.pdfprime.presentation.utils.Constants.IMAGE_QUA_K
import com.jimzmx.pdfprime.presentation.utils.Utilities

class SettingsViewModel : ViewModel() {
    private var pagesSizes : MutableLiveData<MutableList<PageSize>> = MutableLiveData(App.pageSizes)
    private var imagesPagesQuality : MutableLiveData<Float> = MutableLiveData()
    private var askBiometricFlag : MutableLiveData<Boolean> = MutableLiveData()
    private var hasBiometricReaderFlag : MutableLiveData<Boolean> =  MutableLiveData(false)
    private var nonBiometricEnrollFlag : MutableLiveData<Boolean> =  MutableLiveData(false)

    fun getPagesSizes() : MutableLiveData<MutableList<PageSize>> = pagesSizes
    fun getImagesPagesQuality() : MutableLiveData<Float> = imagesPagesQuality
    fun getAskBiometricFlag() : MutableLiveData<Boolean> = askBiometricFlag //Configure ask for validation or not
    fun getBiometricReaderFlag() : MutableLiveData<Boolean> = hasBiometricReaderFlag //Device has a reader?
    fun getBiometricEnrollFlag() : MutableLiveData<Boolean> = nonBiometricEnrollFlag //Device has a enrolled finger/face?

    fun updatePageSizeDefault(position : Int){
        val newPagesSize = pagesSizes.value
        newPagesSize!!.forEach { it.isSelected = false }
        newPagesSize[position].isSelected = true
        Utilities.Shp.setString(Constants.PAGE_SIZE,newPagesSize[position].pdRectangleName)
        pagesSizes.postValue(newPagesSize)
    }

    fun updatePageQualityDefault(value : Float){
        Utilities.Shp.setFloat(IMAGE_QUA_K,value)
        imagesPagesQuality.postValue(value)
    }

    fun updateBiometricReaderFlag(hasBiometric : Boolean) = hasBiometricReaderFlag.postValue(hasBiometric)
    fun updateAskBiometricFlag(requestFp : Boolean) = askBiometricFlag.postValue(requestFp)
    fun updateBiometricEnrollFlag(hasEnrolledFp : Boolean) = nonBiometricEnrollFlag.postValue(hasEnrolledFp)

    init {
        //Loading custom page sizes and setting default
        val pageSizeDefault = Utilities.Shp.getString(Constants.PAGE_SIZE)
        val newPagesSizes = pagesSizes.value
        if(pageSizeDefault == "")
            newPagesSizes!![0].isSelected = true
        else
            newPagesSizes?.forEach {
                it.isSelected = it.pdRectangleName == pageSizeDefault
            }
        pagesSizes.postValue(newPagesSizes)

        imagesPagesQuality.postValue(Utilities.Shp.getFloat(Constants.IMAGE_QUA_K,Constants.IMAGE_QUA_DEF))
        askBiometricFlag.postValue(Utilities.Shp.getBoolean(Constants.ASK_FP_K,Constants.ASK_FP_DEF))
    }
}