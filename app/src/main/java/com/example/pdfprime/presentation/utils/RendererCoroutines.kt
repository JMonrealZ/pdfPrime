package com.example.pdfprime.presentation.utils

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.presentation.di.core.AppModule
import com.example.pdfprime.presentation.di.core.AppModule_ProvideApplicationContextFactory
import com.example.pdfprime.presentation.di.core.DaggerAppComponent
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList

object RendererCoroutines {
    private lateinit var pdfRenderer : PdfRenderer
    private lateinit var pages : ArrayList<Bitmap>

    fun renderPages(direc : File, fileName : String, progressUpdater: ProgressUpdater) : ArrayList<Bitmap>{
        val file = File(direc,fileName)
        val parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        if (parcelFileDescriptor != null)
            pdfRenderer = PdfRenderer(parcelFileDescriptor)
        pages = ArrayList()
        val totalPages = pdfRenderer.pageCount
        for (i in 0 until totalPages) {
            val currentPage = pdfRenderer.openPage(i)
            val bitmap = Bitmap.createBitmap(currentPage.width, currentPage.height, Bitmap.Config.ARGB_8888)
            currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            pages.add(bitmap)

            progressUpdater.onProgressUpdate(
                String.format(
                    App.appContext.resources.getString(R.string.txtPreviewRenderingDocument),
                    fileName,
                    i,
                    totalPages
                )
            )
            currentPage.close()
        }
        pdfRenderer.close()
        return pages
    }

    fun renderFirstPage(direc : File, fileName : String) : Bitmap{
        val file = File(direc,fileName)
        val parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        if (parcelFileDescriptor != null)
            pdfRenderer = PdfRenderer(parcelFileDescriptor)
        val currentPage = pdfRenderer.openPage(0)
        val bitmap = Bitmap.createBitmap(currentPage.width, currentPage.height, Bitmap.Config.ARGB_8888)
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        currentPage.close()
        pdfRenderer.close()
        return bitmap
    }

    fun createFistPage(name : String){
        val baos = ByteArrayOutputStream()
        renderFirstPage(Utilities.Direc.pdfs(),name)
            .compress(Bitmap.CompressFormat.PNG,0,baos)
        val directory = Utilities.Direc.firstPage()
        if(!directory.exists())
            directory.mkdir()
        val firstPage = File(directory,name.substring(0,name.length-3) + "png")
        FileOutputStream(firstPage).use {
            it.write(baos.toByteArray())
        }
    }
}