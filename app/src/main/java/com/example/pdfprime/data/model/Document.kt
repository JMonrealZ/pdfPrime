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

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "INDEX_DOC") var id: Int,
    @ColumnInfo(name = "NAME_DOC") var name : String,
    @ColumnInfo(name = "SIZE_DOC") var size : Int,
    @Ignore var firstPage : Bitmap? = null

    ){
    constructor():this(id=0,name = "",size = 0,firstPage = null)
}
