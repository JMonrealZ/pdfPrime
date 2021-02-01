package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.model.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class UpdateNamePdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(newName : String,idDoc : Int) = documentRepository.updateNamePdf(newName,idDoc)

}