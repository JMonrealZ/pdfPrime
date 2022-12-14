package com.jimzmx.pdfprime.data.repository.datasource

import com.jimzmx.pdfprime.data.entities.Document

interface DocumentLocalDataSource {
    suspend fun getPdfsFromDatabase() : List<Document>
    suspend fun getPdfFromDatabase(idDoc : Int) : Document
    suspend fun deletePdfFromDatabase(idDoc : Int)
    suspend fun updateNamePdfFromDatabase(newName : String, idDoc : Int)
    suspend fun insertPdfToDatabase(document: Document)
    suspend fun deleteAll()
}