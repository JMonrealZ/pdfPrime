package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.model.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class InsertPdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(document : Document) = documentRepository.insertPdf(document)
}