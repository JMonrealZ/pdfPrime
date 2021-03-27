package com.example.pdfprime.presentation.utils

import com.example.pdfprime.App
import com.example.pdfprime.presentation.creatorCam.Page
import com.tom_roush.pdfbox.pdmodel.PDDocument
import java.io.File

object PdfCreator2 {
    fun createPdf(list : MutableList<Page>) : PDDocument{
        var direc = File(App.appContext.filesDir,App.storagePdf)
        var newDoc = PDDocument()

        //Initialize and creating catalog of documents
        var documentos = mutableMapOf(
            Pair(list[0].owner,PDDocument.load(File(direc,list[0].owner)))
        )
        list.forEach{
            if(!documentos.containsKey(it.owner))
                documentos[it.owner] = PDDocument.load(File(direc,it.owner))
        }

        //Adding pages to new document from catalog
        list.forEach{
            newDoc.addPage(documentos[it.owner]?.getPage(it.originalPage))
        }

        return newDoc
    }
}