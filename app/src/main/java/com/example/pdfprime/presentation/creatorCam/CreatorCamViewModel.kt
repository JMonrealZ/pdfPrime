package com.example.pdfprime.presentation.creatorCam

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.domain.usecase.InsertPdfUseCase
import com.example.pdfprime.presentation.utils.ProgressUpdater
import com.example.pdfprime.presentation.utils.RendererCoroutines
import java.io.File

class CreatorCamViewModel(
    private val insertPdfUseCase: InsertPdfUseCase
) : ViewModel(),ProgressUpdater{
    private var pages : MutableLiveData<List<Page>> = MutableLiveData()
    private var isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private var isLoadingMessage : MutableLiveData<String> = MutableLiveData()

    fun pagesObserver() : MutableLiveData<List<Page>>{
        return pages
    }

    fun isLoadingObserver() : MutableLiveData<Boolean>{
        return isLoading
    }

    fun isLoadingMessageObserver() : MutableLiveData<String>{
        return isLoadingMessage
    }

    fun renderPages(docName : String, context : Context){
        isLoading.postValue(true)
        var direc = File(context.filesDir,App.storagePdf)
        var images = RendererCoroutines.renderPages(direc,docName,this)
        var newPages = ArrayList<Page>()
        for(image in 0 until images.size) {
            newPages.add(Page(images[image], image.toString(), image, image, false))
        }
        pages.postValue(newPages)
        isLoading.postValue(false)
    }

    override fun onProgressUpdate(message: String) {
        isLoadingMessage.postValue(message)
    }
}