package com.jimzmx.pdfprime.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.databinding.FragmentReachusBinding
import com.jimzmx.pdfprime.presentation.entities.Acknowledgement
import com.jimzmx.pdfprime.presentation.reachus.AcknowledgementRecyclerViewAdapter

/**
 * This class is used to display info about me
 */
class ReachusFrag : Fragment(){
    //inject
    private lateinit var binding : FragmentReachusBinding
    lateinit var adapter : AcknowledgementRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVaiables(inflater,container)
        initRecyclerView()
        setListeners()
        return binding.root
    }

    private fun initVaiables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reachus,container,false)

        adapter = AcknowledgementRecyclerViewAdapter(App.acknowledgements,{ ackSelected : Acknowledgement -> ackClickListener(ackSelected)})
    }

    private fun initRecyclerView() {
        binding.rvAcknowlements.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ReachusFrag.adapter
        }
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

    private fun ackClickListener(ack : Acknowledgement){

    }
}