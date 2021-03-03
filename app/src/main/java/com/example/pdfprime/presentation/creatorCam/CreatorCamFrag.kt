package com.example.pdfprime.presentation.creatorCam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentCreatorCamBinding
import com.example.pdfprime.presentation.di.Injector
import javax.inject.Inject

/**
 * This fragment is used to create a pdf from camera
 */
class CreatorCamFrag : Fragment() {
    @Inject lateinit var factory : CreatorCamViewModelFactory
    private lateinit var binding : FragmentCreatorCamBinding
    private lateinit var adapter : CreatorCamRecyclerViewAdapter
    private lateinit var creatorCamViewModel: CreatorCamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        setListeners()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_creator_cam,container,false)

        (activity?.application as Injector).createCreatorCamSubComponent().inject(this)

        creatorCamViewModel = ViewModelProvider(this,factory).get(CreatorCamViewModel::class.java)

        adapter = CreatorCamRecyclerViewAdapter(listOf<Page>())
    }

    private fun setListeners(){

    }

    companion object {}
}