package com.example.pdfprime.presentation.utils

import android.graphics.pdf.PdfDocument
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.presentation.creatorCam.Page
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader
import java.io.File

object PdfCreator {
    fun createPdf(direc : File,oldDocName : String,list : MutableList<Page>,progressUpdater: ProgressUpdater) : PDDocument{
        progressUpdater.onProgressUpdate(App.appContext.getString(R.string.txtCreatingDocument))
        PDFBoxResourceLoader.init(App.appContext)
        var oldDoc = PDDocument.load(File(direc,oldDocName))
        var newDoc = PDDocument();
        list.forEach{
            newDoc.addPage(oldDoc.getPage(it.originalPage))
            progressUpdater.onProgressUpdate(
                String.format(
                    App.appContext.getString(R.string.txtPreviewCreating),
                    it.pageNumber,
                    list.size
                )
            )
        }
//        newDoc.save(File(direc,"testPages.pdf"))
        return newDoc
    }
}