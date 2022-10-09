package com.jimzmx.pdfprime.presentation.settings

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.domain.usecase.SetLenguageUseCase
import com.jimzmx.pdfprime.presentation.utils.Constants
import com.jimzmx.pdfprime.presentation.utils.Constants.*
import com.jimzmx.pdfprime.presentation.utils.Utilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val setLenguageUseCase: SetLenguageUseCase
) : ViewModel() {
    private var pagesSizes : MutableLiveData<MutableList<PageSize>> = MutableLiveData(App.pageSizes)
    var pageSizeDef : MutableLiveData<PageSize> = MutableLiveData(
        App.pageSizes.first {
            it.pdRectangle.toString() == Utilities.Shp.getString(PAGE_SIZE_K, PAGE_SIZE_DEF)
        }
    )
    var imagesPagesQuality : MutableLiveData<Float> = MutableLiveData(Utilities.Shp.getFloat(IMAGE_QUA_K,IMAGE_QUA_DEF) * 100F)
    private var askBiometricFlag : MutableLiveData<Boolean> = MutableLiveData()
    private var hasBiometricReaderFlag : MutableLiveData<Boolean> =  MutableLiveData(false)
    private var nonBiometricEnrollFlag : MutableLiveData<Boolean> =  MutableLiveData(false)

    fun getPagesSizes() : MutableLiveData<MutableList<PageSize>> = pagesSizes
    //fun getImagesPagesQuality() : MutableLiveData<Float> = imagesPagesQuality
    fun getAskBiometricFlag() : MutableLiveData<Boolean> = askBiometricFlag //Configure ask for validation or not
    fun getBiometricReaderFlag() : MutableLiveData<Boolean> = hasBiometricReaderFlag //Device has a reader?
    fun getBiometricEnrollFlag() : MutableLiveData<Boolean> = nonBiometricEnrollFlag //Device has a enrolled finger/face?

    fun updatePageSizeDefault(position : Int){
        val newPagesSize = pagesSizes.value
        newPagesSize!!.forEach { it.isSelected = false }
        newPagesSize[position].isSelected = true
        Utilities.Shp.setString(Constants.PAGE_SIZE_K,newPagesSize[position].pdRectangleName)
        pagesSizes.postValue(newPagesSize)
    }

    fun updatePageQualityDefault(value : Float){
        Utilities.Shp.setFloat(IMAGE_QUA_K,value)
        imagesPagesQuality.postValue(value)
    }

    fun updateBiometricReaderFlag(hasBiometric : Boolean) = hasBiometricReaderFlag.postValue(hasBiometric)
    fun updateAskBiometricFlag(requestFp : Boolean) = askBiometricFlag.postValue(requestFp)
    fun updateBiometricEnrollFlag(hasEnrolledFp : Boolean) = nonBiometricEnrollFlag.postValue(hasEnrolledFp)

    fun setLaguage(context: Context,lan : String){
        //TODO: check current language, if it's the same do nothing
        CoroutineScope(Dispatchers.IO).launch {
            setLenguageUseCase.execute(context,lan)
        }
    }

    init {
        //Loading custom page sizes and setting default
        val pageSizeDefault = Utilities.Shp.getString(PAGE_SIZE_K)
        val newPagesSizes = pagesSizes.value
        if(pageSizeDefault == "")
            newPagesSizes!![0].isSelected = true
        else
            newPagesSizes?.forEach {
                it.isSelected = it.pdRectangleName == pageSizeDefault
            }
        pagesSizes.postValue(newPagesSizes)

        imagesPagesQuality.postValue(Utilities.Shp.getFloat(IMAGE_QUA_K,IMAGE_QUA_DEF))
        askBiometricFlag.postValue(Utilities.Shp.getBoolean(ASK_FP_K,ASK_FP_DEF))
    }
}