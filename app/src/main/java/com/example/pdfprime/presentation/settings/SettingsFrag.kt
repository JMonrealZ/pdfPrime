package com.example.pdfprime.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentSettingsBinding

/**
 *  This class is used to set general settings like language, page size...
 */
class SettingsFrag : Fragment(){
    //@Inject viewmodelfactory
    private lateinit var binding : FragmentSettingsBinding
    private lateinit var settingsViewModel : SettingsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        setListeners()
        setObservers()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings,container,false)

        //() subcomponente with dagger

        //viewmodel


    }

    private fun setListeners(){

    }

    private fun setObservers(){

    }
}