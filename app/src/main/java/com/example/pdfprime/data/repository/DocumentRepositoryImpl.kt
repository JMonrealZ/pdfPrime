package com.example.pdfprime.data.repository

import com.example.pdfprime.data.model.Document
import com.example.pdfprime.data.repository.datasource.DocumentLocalDataSource
import com.example.pdfprime.domain.repository.DocumentRepository

class DocumentRepositoryImpl(private val documentLocalDataSource: DocumentLocalDataSource) : DocumentRepository{
    //TODO: HERE WOULD BE ALL LOGIC TO CONNECT TO DIFFERENT DATA SOURCES... IN THIS WE CASE WE ONLY HAVE ONE DATA SOURCE
    override suspend fun getPdfs(): List<Document> {
        return documentLocalDataSource.getPdfsFromDatabase()
    }

    override suspend fun getPdf(idDoc: Int): Document {
        return documentLocalDataSource.getPdfFromDatabase(idDoc)
    }

    override suspend fun deletePdf(idDoc: Int) {
        documentLocalDataSource.deletePdfFromDatabase(idDoc)
    }

    override suspend fun updateNamePdf(newName: String, idDoc: Int) {
        documentLocalDataSource.updateNamePdfFromDatabase(newName,idDoc)
    }

    override suspend fun insertPdf(document: Document) {
        documentLocalDataSource.insertPdfToDatabase(document)
    }

    override suspend fun deleteAll() {
        documentLocalDataSource.deleteAll()
    }


}