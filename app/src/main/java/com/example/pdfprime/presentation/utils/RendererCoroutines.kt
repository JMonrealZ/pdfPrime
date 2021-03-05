package com.example.pdfprime.presentation.utils

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

object RendererCoroutines {
    private lateinit var pdfRenderer : PdfRenderer
    private lateinit var pages : ArrayList<Bitmap>

    fun renderPages(direc : File, fileName : String) : ArrayList<Bitmap>{
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
            currentPage.close()
        }
        pdfRenderer.close()
        return pages
    }
}