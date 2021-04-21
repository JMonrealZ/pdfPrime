package com.example.pdfprime.presentation.utils

import android.net.Uri
import com.example.pdfprime.App
import com.example.pdfprime.presentation.creatorCam.Page
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPage
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle
import com.tom_roush.pdfbox.pdmodel.graphics.image.JPEGFactory
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.File
import java.io.InputStream

object PdfCreator2 {
    fun createPdf(list : MutableList<Page>) : PDDocument{
        var direc = File(App.appContext.filesDir,App.storagePdf)
        var newDoc = PDDocument()

        //Initialize and creating catalog of documents
        val documentos = mutableMapOf<String,PDDocument>()

        list.forEach{
            if(!documentos.containsKey(it.owner) && !it.owner.equals(""))
                documentos[it.owner] = PDDocument.load(File(direc,it.owner))
        }

        //Adding pages to new document from catalog
        list.forEach{
            if(documentos[it.owner] != null)
                newDoc.addPage(documentos[it.owner]?.getPage(it.originalPage))
            else
                newDoc = newPageFromUri(newDoc,it.imageUri)
        }

        return newDoc
    }

    private fun newPageFromUri(document : PDDocument, pageUri : Uri?) : PDDocument{
        var page = PDPage(getDefaultPDRectangle())
        document.addPage(page)


        //Object to add elements to page
        var contentStream = PDPageContentStream(document,page)

        //Load images(input Stream)
        var inStream = pageUri?.let {
            App.appContext.contentResolver.openInputStream(it)?.buffered().use {
                it?.readBytes()?.inputStream()
            }
        }
//        var inStream = InputStream()

        //Drawing image
        var pageImage = JPEGFactory.createFromStream(document,inStream)
        pageImage.height = 2208
        pageImage.width = 2208

        contentStream.drawImage(pageImage,0f,0f,PDRectangle.A0.width,PDRectangle.A0.height)
        page.rotation = 90

//        contentStream.addRect(5F,500F,100F,100F)
//        contentStream.setNonStrokingColor(0,255,125)
//        contentStream.fill()

        contentStream.close()

        return document
    }

    private fun getDefaultPDRectangle() : PDRectangle{
        val defaultPageSize = Utilities.Shp.getString(Constants.PAGE_SIZE,PDRectangle.A0.toString())
        var pageSize : PDRectangle = PDRectangle.A0
        App.pageSizes.forEach {
            if(defaultPageSize == it.pdRectangleName)
                pageSize = it.pdRectangle
        }
        return pageSize
    }
}