package com.example.pdfprime.presentation.myDocuments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.domain.usecase.*
import com.example.pdfprime.presentation.utils.Multiselection

class MyDocumentsViewModel(
    private val getPdfsUseCase: GetPdfsUseCase,
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase,
    private val insertPdfUseCase: InsertPdfUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel(), Multiselection{

    var pdfs : MutableLiveData<List<Document>> = MutableLiveData()
    var isMultiselection : MutableLiveData<Boolean> = MutableLiveData(false)

    fun getPdfObserver() : MutableLiveData<List<Document>>{
        return pdfs
    }

    fun getIsmultiselectionObserver() : MutableLiveData<Boolean>{
        return isMultiselection
    }

    suspend fun getPdfs(){
        val docs = getPdfsUseCase.execute()
        pdfs.postValue(docs)
    }

    suspend fun updateNamePdf(newName : String, idDoc : Int){
        updateNamePdfUseCase.execute(newName, idDoc)
        getPdfs()
    }

    suspend fun deletePdf(idDoc: Int){
        deletePdfUseCase.execute(idDoc)
        getPdfs()
    }

    suspend fun insertPdf(documet : Document){
        insertPdfUseCase.execute(documet)
        getPdfs()
    }

    suspend fun deleteAll(){
        deleteAllUseCase.execute()
        getPdfs()
    }

    override fun onMultiselection(position: Int) {
//        pdfs.value?.get(position)!!.isSelected = true
        val newPdfs = pdfs.value
        newPdfs?.get(position)!!.isSelected = true
        isMultiselection.postValue(true)
        pdfs.postValue(newPdfs)
    }

    override fun isMultiselection(): Boolean {
        return isMultiselection.value!!
    }

    fun cancelMultiselection(){
        val newPdfs = pdfs.value
        newPdfs?.forEach {
            it.isSelected = false
        }
        pdfs.postValue(newPdfs)
        isMultiselection.postValue(false)
    }

    fun newElementDocumentQueue(document: Document){
//        val newPdfs = pdfs.value
//        newPdfs?.get(value)!!.isSelected = true
//        pdfs.postValue(newPdfs)
        val newPdfs = pdfs.value
        newPdfs?.forEach {
            if(it == document)
                it.isSelected = !it.isSelected
        }
        pdfs.postValue(newPdfs)
    }

    fun getInterface() : Multiselection{
        return this
    }
}