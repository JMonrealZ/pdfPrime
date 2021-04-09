package com.example.pdfprime.presentation.utils

import android.os.Environment
import com.example.pdfprime.App
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    object Camera{
        lateinit var currentPhotoPath: String
        @Throws(IOException::class)
        fun createImageFile(): File {
            val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//            val storageDir : File? = App.appContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val storageDir : File? = File(App.appContext.filesDir,App.storagePagPdf)

            if(!storageDir!!.exists())
                storageDir.mkdir()

            return File.createTempFile(
                "JPEG_${timeStamp}_", //Name
                ".jpg", //Ext
                storageDir
            ).apply {
                currentPhotoPath = absolutePath
            }
        }
    }

    object Directories{

    }
}