package com.jimzmx.pdfprime.presentation.creatorCam

import android.content.ClipData
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.domain.usecase.InsertPdfUseCase
import com.jimzmx.pdfprime.presentation.dialogs.Dialogs
import com.jimzmx.pdfprime.presentation.utils.*
import com.jimzmx.pdfprime.presentation.utils.Constants.DELIMITER
import com.jimzmx.pdfprime.presentation.utils.Constants.NSAF_NOT_EDITABLE_PAGE_K
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
    private var finished : MutableLiveData<Boolean> = MutableLiveData(false)
    private var pageToCrop : MutableLiveData<Page?> = MutableLiveData(null)
//    private var layoutInflater : LayoutInflater? = null
    private var context : FragmentActivity? = null

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

    fun finishFlag() : MutableLiveData<Boolean>{
        return finished
    }

//    fun setLayoutInflater(layoutInflater : LayoutInflater){
//        this.layoutInflater = layoutInflater
//    }

    fun setContext(ctx : FragmentActivity){
        context = ctx
    }

    fun pageToCropOberver() : MutableLiveData<Page?> = pageToCrop

    fun renderPages(docsName : String, context : Context){
        if(pages.value != null)
            return

        isLoading.postValue(true)
        var newPages = ArrayList<Page>()
        var direc = File(context.filesDir,App.storagePdf)

//        var documentsToEditArray = if(docsName.contains(",")) docsName.split(",") else listOf(docsName)
        var documentsToEditArray =
            if(docsName.contains(DELIMITER)) docsName.split(DELIMITER)
            else listOf(docsName)

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

    fun renderPages(uris : ClipData?){
        isLoading.postValue(true)
        val newPages = if(pages.value == null)
            ArrayList()
        else
            pages.value as ArrayList<Page>

        //-2 means that uri comes from gallery
        for(index in 0 until uris!!.itemCount)
            newPages.add(Page("",null, uris.getItemAt(index).uri,-2))

        pages.postValue(newPages)
        isLoading.postValue(false)
    }

    fun renderPage(uri : Uri){
        isLoading.postValue(true)
        val newPages = if(pages.value == null)
            ArrayList()
        else
            pages.value as ArrayList<Page>

        //-2 means that uri comes from gallery
        newPages.add(Page("",null, uri /*uris.getItemAt(index).uri*/,-2))

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
        //-1 means that uri comes from camera
        val newPage = Page("",null,imageUri,-1)
        val newPages =
            if(pages.value == null) mutableListOf()
            else pages!!.value!!.toMutableList()
        newPages.add(newPage)
        pages.postValue(newPages)
    }

    fun newCroppedImage(newUri : Uri?){
        val newPages = pages.value
        val index = newPages!!.indexOfFirst { it.imageUri == pageToCrop.value?.imageUri }
        newPages!![index].imageUri = newUri
        pages.postValue(newPages)
    }

    suspend fun savePdf(docName : String){
        val file = File(Utilities.Direc.pdfs(),docName)
        newDocument.value?.save(file)
        insertPdfUseCase.execute(Document(0,docName, (file.length() / 1024L),null))
        finished.postValue(true)
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

    override fun onClickPage(page: Page?) {
        if(page!!.imageUri == null){
            //We show message according to saved info
            if(!Utilities.Shp.getBoolean(NSAF_NOT_EDITABLE_PAGE_K))
                Dialogs.createMessageDialog(context,
                    context!!.layoutInflater, R.drawable.ic_info_24,R.string.txtNotEditablePage)
            else
                Toast.makeText(App.appContext,R.string.txtNotEditablePage,Toast.LENGTH_LONG).show()

            return
        }


        pageToCrop.postValue(page)
    }
}