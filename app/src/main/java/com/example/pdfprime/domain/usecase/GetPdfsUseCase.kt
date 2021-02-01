package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.model.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class GetPdfsUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute() : List<Document> = documentRepository.getPdfs()
}