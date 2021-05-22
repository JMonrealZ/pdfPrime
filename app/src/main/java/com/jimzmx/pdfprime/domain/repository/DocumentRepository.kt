package com.jimzmx.pdfprime.domain.repository

import com.jimzmx.pdfprime.data.entities.Document

interface DocumentRepository{
    suspend fun getPdfs() : List<Document>
    suspend fun getPdf(idDoc : Int) : Document
    suspend fun deletePdf(idDoc : Int)
    suspend fun updateNamePdf(newName : String, idDoc : Int)
    suspend fun insertPdf(document: Document)
    suspend fun deleteAll()
}