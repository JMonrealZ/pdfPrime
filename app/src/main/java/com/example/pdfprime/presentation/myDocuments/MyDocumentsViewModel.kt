package com.example.pdfprime.presentation.myDocuments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pdfprime.data.model.Document
import com.example.pdfprime.domain.usecase.*

class MyDocumentsViewModel(
    private val getPdfsUseCase: GetPdfsUseCase,
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase,
    private val insertPdfUseCase: InsertPdfUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel(){
    fun getPdfs() = liveData{
        val documents = getPdfsUseCase.execute()
        emit(documents)
    }

    suspend fun updateNamePdf(newName : String, idDoc : Int){
        updateNamePdfUseCase.execute(newName, idDoc)
    }

    suspend fun deletePdf(idDoc: Int){
        deletePdfUseCase.execute(idDoc)
    }

    suspend fun insertPdf(documet : Document){
        insertPdfUseCase.execute(documet)
    }

    suspend fun deleteAll(){
        deleteAllUseCase.execute()
    }
}