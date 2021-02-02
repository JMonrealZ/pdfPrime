package com.example.pdfprime.domain.repository

import com.example.pdfprime.data.model.Document

interface DocumentRepository{
    suspend fun getPdfs() : List<Document>
    suspend fun getPdf(idDoc : Int) : Document
    suspend fun deletePdf(idDoc : Int)
    suspend fun updateNamePdf(newName : String, idDoc : Int)
    suspend fun insertPdf(document: Document)
    suspend fun deleteAll()
}