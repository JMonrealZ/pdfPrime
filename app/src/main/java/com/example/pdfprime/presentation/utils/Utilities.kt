package com.example.pdfprime.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Environment
import com.example.pdfprime.App
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    object Camera{
        lateinit var currentPhotoPath: String
        @Throws(IOException::class)
        fun createImageFile(): File {
            val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
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

        fun firstPage() : File{
            return createDirec(App.storageFirstPagePdf)
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

        fun saveOnDisk(uri : Uri, name : String, folder : String){
            val pdfBytes = App.appContext.contentResolver?.openInputStream(uri)?.buffered().use {
                it?.readBytes()
            }

            val path = App.appContext.filesDir

            val directory = File(path,folder)
            directory.mkdir()
            val file = File(directory,name)
            FileOutputStream(file).use {
                it.write(pdfBytes)
            }
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
         * Returns a float based on a key supplied, if doesn't exists returns defValue
         */
        fun getFloat(key : String) : Float {
            return getFloat(key,0f)
        }

        /**
         * Returns a string based on a key supplied,if it doesn't exists returns defValue
         */
        fun getFloat(key : String, defValue : Float) : Float{
            return getFile().getFloat(key,defValue)
        }

        /**
         * Stores a Float in shared preferences based on key value
         */
        fun setFloat(key : String, value : Float){
            with(getFile().edit()){
                putFloat(key,value)
                commit()
            }
        }

        /**
         * Returns a boolean based on a key supplied, if doesn't exists returns defValue
         */
        fun getBoolean(key : String) : Boolean{
            return getBoolean(key, false)
        }

        /**
         * Returns a boolean based on a key supplied, if doesn't exists returns defValue
         */
        fun getBoolean(key : String, defValue : Boolean) : Boolean{
            return getFile().getBoolean(key, defValue)
        }

        /**
         * Stores a Boolean in shared preferences based on a key value
         */
        fun setBoolean(key : String, value : Boolean){
            with(getFile().edit()){
                putBoolean(key,value)
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