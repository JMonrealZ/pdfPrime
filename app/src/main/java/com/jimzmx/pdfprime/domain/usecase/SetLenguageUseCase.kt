package com.jimzmx.pdfprime.domain.usecase

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.*

/***
 * Class to hold logic to change language in UI.
 * We tried to insert context with dagger but it inserted App context and in this case we need
 * activity context. So we let a context parameter on execute method
 */
class SetLenguageUseCase {
    /***
     * It changes language in application
     */
    fun execute(context : Context, languageToLoad : String){
        val locale = Locale(languageToLoad)
        //Locale.setDefault(locale) TODO: This line has been commented. is it requiered? It's global, global for all apps?
        val config = context.resources.configuration;   //We retrieve current configuration to not override other configs
        config.locale = locale
        context.resources
            .updateConfiguration(config, context.resources.displayMetrics)
    }
}