package com.example.pdfprime.presentation.utils

import com.example.pdfprime.App
import com.tom_roush.pdfbox.pdmodel.PDDocument
import java.io.File

object PdfCreator2 {
    //region working merging code
//    fun createPdf() : PDDocument{
//        var direc = File(App.appContext.filesDir,App.storagePdf)
//        var newDoc = PDDocument()
//        var documentos = mutableMapOf(Pair("A.pdf",PDDocument.load(File(direc,"A.pdf"))),
//                                      Pair("B.pdf",PDDocument.load(File(direc,"B.pdf"))))
//
//        newDoc.addPage(documentos["A.pdf"]?.getPage(0))
//        newDoc.addPage(documentos["B.pdf"]?.getPage(0))
//
//
//        return newDoc
//    }
    //endregion
    fun createPdf() : PDDocument{
        var direc = File(App.appContext.filesDir,App.storagePdf)
        var newDoc = PDDocument()
        var documentos = mutableMapOf(Pair("A.pdf",PDDocument.load(File(direc,"A.pdf"))),
                                      Pair("B.pdf",PDDocument.load(File(direc,"B.pdf"))))

        newDoc.addPage(documentos["A.pdf"]?.getPage(0))
        newDoc.addPage(documentos["B.pdf"]?.getPage(0))


        return newDoc
    }
}