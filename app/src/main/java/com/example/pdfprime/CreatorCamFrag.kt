package com.example.pdfprime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pdfprime.databinding.FragmentCreatorCamBinding
import com.example.pdfprime.databinding.FragmentDocumentBinding

class CreatorCamFrag : Fragment() {
    private lateinit var binding : FragmentCreatorCamBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_creator_cam,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners(){

    }

    companion object {}
}