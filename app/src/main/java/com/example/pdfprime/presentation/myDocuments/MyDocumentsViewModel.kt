package com.example.pdfprime.presentation.myDocuments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.data.model.Document
import com.example.pdfprime.domain.usecase.*

class MyDocumentsViewModel(
    private val getPdfsUseCase: GetPdfsUseCase,
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase,
    private val insertPdfUseCase: InsertPdfUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel(){

    var pdfs : MutableLiveData<List<Document>> = MutableLiveData()

    fun getObservers() : MutableLiveData<List<Document>>{
        return pdfs
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
}