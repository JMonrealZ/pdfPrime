package com.example.pdfprime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pdfprime.databinding.FragmentDocumentBinding

/**
 * This fragment is used to show a pdf summary in a list to edit them
 */
class DocumentFrag : Fragment() {
    private lateinit var binding : FragmentDocumentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_document,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners(){
    }

    companion object {}
}