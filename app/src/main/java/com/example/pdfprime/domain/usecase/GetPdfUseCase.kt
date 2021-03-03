package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class GetPdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(idDoc : Int) : Document = documentRepository.getPdf(idDoc)
}