package com.example.pdfprime.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentSettingsBinding
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.myDocuments.MyDocumentsViewModel
import com.example.pdfprime.presentation.myDocuments.MyDocumentsViewModelFactory
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.Utilities
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

/**
 *  This class is used to set general settings like language, page size...
 */
class SettingsFrag : Fragment(){
    @Inject lateinit var factory : SettingsViewModelFactory
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

        (activity?.application as Injector).createSettingsSubComponent()
            .inject(this)

        settingsViewModel = ViewModelProvider(this,factory).get(SettingsViewModel::class.java)

        val adapter = PageSizeAdapter(requireContext(),R.layout.list_item_page_size,App.pageSizes)
        binding.spPageSize.adapter = adapter
    }

    private fun setListeners(){

    }

    private fun setObservers(){

    }
}