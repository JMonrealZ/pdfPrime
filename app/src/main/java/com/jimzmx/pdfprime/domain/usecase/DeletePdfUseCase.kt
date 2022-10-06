package com.jimzmx.pdfprime.domain.usecase

import android.content.Context
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.domain.repository.DocumentRepository
import java.io.File

class DeletePdfUseCase(private val documentRepository: DocumentRepository,
                       private val context: Context) {
    suspend fun execute(idDoc : Int) : Int{

        //Deleting file from memory
        val doc = documentRepository.getPdf(idDoc)  //Getting file info to delete
        val direc = File(context.filesDir, App.storagePdf)  //directory/folder
        val file = File(direc, doc.name)
        file.delete()
        return if(!file.exists()) {
            documentRepository.deletePdf(idDoc) //Deleting file from data base
            1;
        } else
            0;
    }

}