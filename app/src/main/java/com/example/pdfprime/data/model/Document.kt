package com.example.pdfprime.data.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
import java.sql.Date

@Entity(tableName = "DOCUMENTS")
data class Document(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "INDEX_DOC") val id: Int,
    @ColumnInfo(name = "NAME_DOC") val name : String,
    @ColumnInfo(name = "SIZE_DOC") val size : Int
    //@ColumnInfo(name = "DATECRE_DOC") val datecre : Date,

    //@Ignore val pageOneDoc : Bitmap?     //This field and below are to recycle this object in adapters
//    @Ignore val realPage : Int,
//    @Ignore val newPage : Int
    )