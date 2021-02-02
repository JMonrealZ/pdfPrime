package com.example.pdfprime.domain.usecase

import com.example.pdfprime.domain.repository.DocumentRepository

class DeleteAllUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute() = documentRepository.deleteAll()
}