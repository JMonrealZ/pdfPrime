package com.example.pdfprime.data.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "DOCUMENTS")
data class Document(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "INDEX_DOC") var id: Int,
    @ColumnInfo(name = "NAME_DOC") var name : String,
    @ColumnInfo(name = "SIZE_DOC") var size : Long,
    @Ignore var firstPage : Bitmap? = null,
    @Ignore var isSelected : Boolean = false
    ){
    constructor():this(id=0,name = "",size = 0,firstPage = null,isSelected = false)
}
