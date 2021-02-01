package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.model.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class DeletePdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(idDoc : Int) = documentRepository.deletePdf(idDoc)

}