package com.example.pdfprime.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
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

    /**
     * For directories used in project
     */
    object Direc{
        fun pdfs() : File{
            return createDirec(App.storagePdf)
        }

        fun img() : File{
            return createDirec(App.storagePagPdf)
        }

        private fun createDirec(folder : String) : File{
            return File(App.appContext.filesDir,folder)
        }
    }

    companion object Files{
        fun exist(fileName : String) : Boolean{
            val direc = Direc.pdfs()
            if(!direc.exists()) //It means directory no exists and it hasn't been added any pdf document
                direc.mkdir()
            val file = File(direc,fileName)
            return file.exists()
        }
    }

    /**
     * Shared preferences
     */
    object Shp{
        /**
         * Returns a int based on a key supplied, if it doesn't exists returns a 0
         */
        fun getInt(key : String) : Int{
            return getInt(key,0)
        }

        /**
         * Returns a int based on a key supplied,if it doesn't exists returns defValue
         */
        fun getInt(key : String, defValue : Int) : Int{
            return getFile().getInt(key,defValue)
        }

        /**
         * Stores a int in shared preferences based on key value
         */
        fun setInt(key : String,value : Int) {
            with(getFile().edit()){
                putInt(key,value)
                commit()
            }
        }

        /**
         * Returns a string based on a key supplied, if it doesn't exists returns a ""
         */
        fun getString(key : String) : String?{
            return getString(key,"")
        }

        /**
         * Returns a string based on a key supplied,if it doesn't exists returns defValue
         */
        fun getString(key : String, defValue : String) : String?{
            return getFile().getString(key,defValue)
        }

        /**
         * Stores a String in shared preferences based on key value
         */
        fun setString(key : String,value : String){
            with(getFile().edit()){
                putString(key,value)
                commit()
            }
        }

        /**
         * Provides shared preferences file
         */
        private fun getFile() : SharedPreferences{
            return App.appContext.getSharedPreferences(App.appContext.packageName,Context.MODE_PRIVATE)
        }

    }
}