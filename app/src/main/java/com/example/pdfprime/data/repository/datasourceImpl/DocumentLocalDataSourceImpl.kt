package com.example.pdfprime.data.repository.datasourceImpl

import com.example.pdfprime.data.db.DocumentsDao
import com.example.pdfprime.data.model.Document
import com.example.pdfprime.data.repository.datasource.DocumentLocalDataSource

class DocumentLocalDataSourceImpl(private val documentsDao: DocumentsDao) : DocumentLocalDataSource{
    override suspend fun getPdfsFromDatabase(): List<Document> {
        return documentsDao.getDocuments()
    }

    override suspend fun getPdfFromDatabase(idDoc: Int): Document {
        return documentsDao.getDocument(idDoc)
    }

    override suspend fun deletePdfFromDatabase(idDoc: Int) {
        return documentsDao.deleteDocument(idDoc)
    }

    override suspend fun updateNamePdfFromDatabase(newName: String, idDoc: Int) {
        return documentsDao.updateNameDocument(newName,idDoc)
    }

    override suspend fun insertPdfToDatabase(document: Document) {
        return documentsDao.insertNewDocument(document)
    }

    override suspend fun deleteAll() {
        return documentsDao.deleteAll()
    }
}