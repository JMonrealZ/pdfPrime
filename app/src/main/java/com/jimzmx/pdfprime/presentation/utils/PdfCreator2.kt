package com.jimzmx.pdfprime.presentation.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.presentation.creatorCam.Page
import com.jimzmx.pdfprime.presentation.utils.Constants.IMAGE_QUA_DEF
import com.jimzmx.pdfprime.presentation.utils.Constants.IMAGE_QUA_K
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDPage
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle
import com.tom_roush.pdfbox.pdmodel.graphics.image.JPEGFactory
import java.io.File

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
                newDoc = newPageFromUri(newDoc,it.imageUri, it.originalPage)
        }

        return newDoc
    }

    private fun newPageFromUri(document : PDDocument, pageUri : Uri?, source : Int) : PDDocument{
        val defPDRectangle = getDefaultPDRectangle()
        var page = PDPage(defPDRectangle)
        document.addPage(page)

        //Object to add elements to page
        var contentStream = PDPageContentStream(document,page)

        val parcelFileDescriptor = pageUri?.let { App.appContext.contentResolver.openFileDescriptor(it,"r") }
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()

        //Resizing image
        val resizedImage = Bitmap.createScaledBitmap(image,defPDRectangle.width.toInt(),defPDRectangle.height.toInt(),true)

        val pageImage = JPEGFactory.createFromImage(
            document,
            resizedImage,
            Utilities.Shp.getFloat(IMAGE_QUA_K,IMAGE_QUA_DEF)
        )

        contentStream.drawImage(pageImage,0f,0f,defPDRectangle.width,defPDRectangle.height)

//        if(source == -1)
//            page.rotation = 90
        page.rotation = pageUri!!.fixRotation()

        contentStream.close()

        return document
    }

    private fun getDefaultPDRectangle() : PDRectangle{
        val defaultPageSize = Utilities.Shp.getString(Constants.PAGE_SIZE_K,PDRectangle.A0.toString())
        var pageSize : PDRectangle = PDRectangle.A0
        App.pageSizes.forEach {
            if(defaultPageSize == it.pdRectangleName)
                pageSize = it.pdRectangle
        }
        return pageSize
    }
}