package com.example.pdfprime.presentation.creatorTxt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentCreatorTxtBinding

/**
 * This fragment is used to create a pdf from scratch (inserting text, images and singatures)
 */
class CreatorTxtFrag : Fragment() {
    private lateinit var binding : FragmentCreatorTxtBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_creator_txt,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners(){

    }

    companion object {}
}