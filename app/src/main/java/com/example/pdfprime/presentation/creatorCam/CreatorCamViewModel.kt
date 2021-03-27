package com.example.pdfprime.presentation.creatorCam

import android.content.Context
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

        var pageNumberNewDoc = 0
        for(docName in documentsToEditArray) {
            var images = RendererCoroutines.renderPages(direc, docName, this)
            for (originalPage in 0 until images.size) {
                newPages.add(Page(docName, images[originalPage], "", pageNumberNewDoc, originalPage))
                pageNumberNewDoc++
            }
        }

        pages.postValue(newPages)
        isLoading.postValue(false)
    }

    /**
     * This method is old method
     */
    fun createPdf(direc : File,oldDocName : String,list : MutableList<Page>){
        isLoading.postValue(true)
        val newDocume = PdfCreator.createPdf(direc,oldDocName,list,this)
        isLoading.postValue(false)
        newDocument.postValue(newDocume)
    }

    fun createPdf(){
        isLoading.postValue(true)
        val newDocume = PdfCreator2.createPdf(pages.value!!.toMutableList())
        isLoading.postValue(false)
        newDocument.postValue(newDocume)
    }

    suspend fun savePdf(docName : String){
        newDocument.value?.save(File(File(App.appContext.filesDir,App.storagePdf),docName))
        insertPdfUseCase.execute(Document(0,docName,50,null))
    }

    override fun onProgressUpdate(message: String) {
        isLoadingMessage.postValue(message)
    }

    override fun onDeletePage(page: Page) {
        val newPages = pages.value?.toMutableList()
        newPages?.remove(page)
        //Updating pageNumber for UI
        for(newPageNumber in 0 until newPages!!.size){
            newPages[newPageNumber].pageNumber = newPageNumber
        }
        pages.postValue(newPages)
    }
}