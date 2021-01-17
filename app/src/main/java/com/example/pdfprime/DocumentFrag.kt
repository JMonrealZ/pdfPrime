package com.example.pdfprime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pdfprime.databinding.FragmentDocumentBinding

class DocumentFrag : Fragment() {
    private lateinit var binding : FragmentDocumentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_document,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners(){
        binding.apply {
            btnCreCam.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_creatorCamFrag) }
            btnCreTxt.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_creatorTxtFrag) }
            btnView.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_viewerFrag) }
        }
    }

    companion object {}
}