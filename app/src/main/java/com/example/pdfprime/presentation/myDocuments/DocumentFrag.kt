package com.example.pdfprime.presentation.myDocuments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.lifecycle.ViewModelProviders
import com.example.pdfprime.R
import com.example.pdfprime.data.model.Document
import com.example.pdfprime.databinding.FragmentDocumentBinding
import com.example.pdfprime.presentation.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This fragment is used to show a pdf summary in a list to edit them
 */
class DocumentFrag : Fragment() {
    @Inject lateinit var factory : MyDocumentsViewModelFactory
    lateinit var binding : FragmentDocumentBinding
    lateinit var adapter : DocumentRecyclerViewAdapter
//    private lateinit var documents : List<Document>
    lateinit var myDocumentsViewModel : MyDocumentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        insertNewDocTest()
        initVariables(inflater,container)
        initRecyclerView()
        setListeners()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        val responseLiveData = myDocumentsViewModel.getPdfs()
        responseLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null){
                adapter.setList(it)
            }
            else
                Toast.makeText(context,"nothing found",Toast.LENGTH_LONG).show()
        })
    }

    private fun initRecyclerView() {
        binding.rvDocuments.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@DocumentFrag.adapter
        }
//        binding.rvDocuments.

    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_document,container,false)

        (activity?.application as Injector).createMyDocumentsSubComponent()
            .inject(this)

        myDocumentsViewModel = ViewModelProvider(this,factory).get(MyDocumentsViewModel::class.java)

       adapter = DocumentRecyclerViewAdapter(listOf<Document>(),{docSelected : Document -> documentClickListener(docSelected)})
    }

    private fun insertNewDocTest() {
        CoroutineScope(Dispatchers.IO).launch{
            myDocumentsViewModel.insertPdf(Document(1,"test",85))
        }
    }

    private fun setListeners(){
    }

    private fun documentClickListener(document: Document){
        Toast.makeText(context,"test con ${document.name}",Toast.LENGTH_LONG).show()
    }

    companion object {}
}