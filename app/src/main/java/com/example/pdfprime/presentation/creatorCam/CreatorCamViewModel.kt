package com.example.pdfprime.presentation.creatorCam

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.App
import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.domain.usecase.InsertPdfUseCase
import com.example.pdfprime.presentation.utils.*
import com.tom_roush.pdfbox.pdmodel.PDDocument
import java.io.File

class CreatorCamViewModel(
    private val insertPdfUseCase: InsertPdfUseCase/*,
    private val createPdfUseCase: CreatePdfUseCase*/
) : ViewModel(),ProgressUpdater,PageOperationInterface{
    private var pages : MutableLiveData<List<Page>> = MutableLiveData()
    private var isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private var isLoadingMessage : MutableLiveData<String> = MutableLiveData()
    private var newDocument : MutableLiveData<PDDocument> = MutableLiveData()

    fun pagesObserver() : MutableLiveData<List<Page>>{
        return pages
    }

    fun isLoadingObserver() : MutableLiveData<Boolean>{
        return isLoading
    }

    fun isLoadingMessageObserver() : MutableLiveData<String>{
        return isLoadingMessage
    }

    fun newDocumentObserver() : MutableLiveData<PDDocument>{
        return newDocument
    }

    fun getInterface() : PageOperationInterface {
        return this
    }

    fun renderPages(docsName : String, context : Context){
        if(pages.value != null)
            return

        isLoading.postValue(true)
        var newPages = ArrayList<Page>()
        var direc = File(context.filesDir,App.storagePdf)

        var documentsToEditArray = if(docsName.contains(",")) docsName.split(",") else listOf(docsName)

        //var pageNumberNewDoc = 0
        for(docName in documentsToEditArray) {
            var images = RendererCoroutines.renderPages(direc, docName, this)
            for (originalPage in 0 until images.size) {
                newPages.add(Page(docName, images[originalPage], null, originalPage))
                //pageNumberNewDoc++
            }
        }

        pages.postValue(newPages)
        isLoading.postValue(false)
    }

    fun createPdf(){
        isLoading.postValue(true)
        val newDocume = PdfCreator2.createPdf(pages.value!!.toMutableList())
        isLoading.postValue(false)
        newDocument.postValue(newDocume)
    }

    fun newPageFromCamera(/*bitmap : Bitmap,*/ imageUri : Uri?){
        val newPage = Page("",null,imageUri,-1)
        val newPages = pages!!.value!!.toMutableList()
        newPages.add(newPage)
        pages.postValue(newPages)
    }

    suspend fun savePdf(docName : String){
        newDocument.value?.save(File(File(App.appContext.filesDir,App.storagePdf),docName))
        insertPdfUseCase.execute(Document(0,docName,50,null))
    }

    override fun onProgressUpdate(message: String) {
        isLoadingMessage.postValue(message)
    }

    override fun onDeletePage(pageNumber: Int) {
        val newPages = pages.value?.toMutableList()
        newPages?.removeAt(pageNumber)
        pages.postValue(newPages)
    }

    override fun onMovePage(from: Int, to: Int) {
        val newPages = pages.value?.toMutableList()
        val pageToMove = newPages!![from]
        newPages.removeAt(from)
        newPages.add(to,pageToMove)
        pages.postValue(newPages)
    }
}