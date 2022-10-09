package com.jimzmx.pdfprime.domain.usecase

import android.content.Context

class GetLenguageUseCase {
    fun execute(context : Context) : String{
        return context.resources.configuration.locales.toString()
    }
}