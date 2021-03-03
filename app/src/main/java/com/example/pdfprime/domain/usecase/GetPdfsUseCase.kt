package com.example.pdfprime.domain.usecase

import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.domain.repository.DocumentRepository

class GetPdfsUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute() : List<Document> = documentRepository.getPdfs()
}