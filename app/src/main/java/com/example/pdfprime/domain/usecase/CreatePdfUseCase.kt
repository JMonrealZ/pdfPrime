package com.example.pdfprime.domain.usecase

import android.graphics.pdf.PdfDocument
import com.example.pdfprime.App
import com.example.pdfprime.presentation.creatorCam.Page
import com.example.pdfprime.presentation.utils.ProgressUpdater
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader
import java.io.File

class CreatePdfUseCase(
    private val direc : File,
    private val oldDocName : String,
    private val list : MutableList<Page>,
    private val progressUpdater: ProgressUpdater) {
    suspend fun execute() : PDDocument{
        PDFBoxResourceLoader.init(App.appContext);
        val oldDoc = PDDocument.load(File(direc,oldDocName))
        val newDoc = PDDocument();
        list.forEach{
            newDoc.addPage(oldDoc.getPage(it.originalPage))
        }
        return newDoc
    }
}