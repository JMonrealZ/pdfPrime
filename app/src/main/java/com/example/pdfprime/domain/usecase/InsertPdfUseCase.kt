package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class InsertPdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(document : Document) = documentRepository.insertPdf(document)
}