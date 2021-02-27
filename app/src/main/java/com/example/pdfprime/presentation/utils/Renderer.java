package com.example.pdfprime.presentation.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;

import com.example.pdfprime.App;

import java.io.File;
import java.io.IOException;

import kotlin.coroutines.CoroutineContext;

public class Renderer {
    public static PdfRenderer pdfRenderer;
    public static PdfRenderer.Page page;
    public static ParcelFileDescriptor parcelFileDescriptor;

    public static Bitmap renderPage(Context context, String nameDoc, int pageNumber) throws IOException {
        File directory = new File(context.getFilesDir(), App.direcStoragePdf);
        directory.mkdir();
        File file = new File(directory,nameDoc);
        parcelFileDescriptor = ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_ONLY);
        if(parcelFileDescriptor != null){
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);
        }
        if(pdfRenderer.getPageCount() <= pageNumber){
            return null;
        }
        page = pdfRenderer.openPage(pageNumber);
        Bitmap bitmap = Bitmap.createBitmap(page.getWidth(),page.getHeight(),Bitmap.Config.ARGB_8888);
        page.render(bitmap,null,null,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();
        pdfRenderer.close();
        return bitmap;
    }
}
