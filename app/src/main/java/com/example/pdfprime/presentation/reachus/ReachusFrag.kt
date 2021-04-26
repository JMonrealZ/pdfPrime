package com.example.pdfprime.presentation.reachus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentReachusBinding

/**
 * This class is used to display info about me
 */
class ReachusFrag : Fragment(){
    //inject
    private lateinit var binding : FragmentReachusBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVaiables(inflater,container)
        setListeners()
        return binding.root
    }

    private fun initVaiables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reachus,container,false)
    }
    private fun setListeners() {
        binding.apply {
            tvEmail.setOnClickListener { sendEmail()}
        }
    }

    private fun sendEmail(){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayListOf(getString(R.string.txtEmail)))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.txtSubjectEmail))
        }
        if(intent.resolveActivity(requireActivity().packageManager) != null)
            startActivity(intent)
    }
}