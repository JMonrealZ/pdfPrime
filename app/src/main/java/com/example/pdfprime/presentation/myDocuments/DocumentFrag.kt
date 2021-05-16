package com.example.pdfprime.presentation.myDocuments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pdfprime.App
//import androidx.lifecycle.ViewModelProviders
import com.example.pdfprime.R
import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.databinding.FragmentDocumentBinding
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetNewDoc
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetSelectedDoc
import com.example.pdfprime.presentation.bottomSheetMenus.DocOperationInterface
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.dialogs.Dialogs
import com.example.pdfprime.presentation.dialogs.NameDocDialogInterface
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.Constants.*
import com.example.pdfprime.presentation.utils.RendererCoroutines
import com.example.pdfprime.presentation.utils.Utilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * This fragment is used to show a pdf summary in a list to edit them
 */
class DocumentFrag : Fragment() ,  NameDocDialogInterface, DocOperationInterface{
    @Inject lateinit var factory : MyDocumentsViewModelFactory
    lateinit var binding : FragmentDocumentBinding
    lateinit var adapter : DocumentRecyclerViewAdapter
    lateinit var myDocumentsViewModel : MyDocumentsViewModel
    lateinit var bottomSheetNewDoc : BottomSheetNewDoc
    lateinit var bottomSheetSelectedDoc: BottomSheetSelectedDoc

    //For biometrict requests
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        initRecyclerView()
        setListeners()
        //insertNewDocTest()
        setObservers()
        initBiometrics()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_document,container,false)

        (activity?.application as Injector).createMyDocumentsSubComponent()
            .inject(this)

        myDocumentsViewModel = ViewModelProvider(this,factory).get(MyDocumentsViewModel::class.java)

        adapter = DocumentRecyclerViewAdapter(listOf<Document>(),{docSelected : Document -> documentClickListener(docSelected)},myDocumentsViewModel.getInterface())

        bottomSheetSelectedDoc = BottomSheetSelectedDoc(Document(),this)

        binding.llMultiselection.visibility = View.GONE
    }

    private fun initRecyclerView() {
        binding.rvDocuments.apply {
            layoutManager = GridLayoutManager(context,2)//LinearLayoutManager(context)
            adapter = this@DocumentFrag.adapter
        }
    }

    private fun setListeners(){
        binding.apply {
            btnDeleteAll.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    myDocumentsViewModel.deleteAll()
                }
            }
            fabNewDoc.setOnClickListener{
                bottomSheetNewDoc = BottomSheetNewDoc()//App.newDocBottomSheetOptions,
                fragmentManager?.let { it1 -> bottomSheetNewDoc.show(it1,"BOTTOM_SHEET_NEWDOC") }
                bottomSheetNewDoc.setParent(this@DocumentFrag)
            }
            ibCancelMultiselection.setOnClickListener{
                myDocumentsViewModel.cancelMultiselection()
            }
            ibDeleteMultiselection.setOnClickListener{

            }
            ibEditMultiselection.setOnClickListener{
                val bundle = Bundle()
                bundle.putString(Constants.DOCUMENT,adapter.getDocsSelected())
                NavHostFragment.findNavController(this@DocumentFrag).navigate(R.id.action_documentFrag_to_creatorCamFrag,bundle)
                myDocumentsViewModel.cancelMultiselection()
            }
        }
    }

    private fun setObservers() {
        //Init list of pdfs on viewmodel
        CoroutineScope(Dispatchers.IO).launch{
            myDocumentsViewModel.getPdfs()
        }
        myDocumentsViewModel.getPdfObserver().observe(viewLifecycleOwner, Observer {
            if(it != null){
                context?.let { it1 -> adapter.setList(it, it1) }
            }
            else
                Toast.makeText(context,"nothing found",Toast.LENGTH_LONG).show()
        })
        myDocumentsViewModel.getIsmultiselectionObserver().observe(viewLifecycleOwner, Observer{
            if(it != null){
                adapter.notifyDataSetChanged()
                binding.apply {
                    //llMultiselection.visibility = if (it) View.VISIBLE else View.GONE
                    //Animation
                    if(it){
                        llMultiselection.visibility = View.VISIBLE
                        llMultiselection.animate()
                            .alpha(1f)
                            .setDuration(100L)
                            .setListener(object : AnimatorListenerAdapter(){
                                override fun onAnimationEnd(animation: Animator?) {
                                    llMultiselection.visibility = View.VISIBLE
                                }
                            })
                    }else{
                        llMultiselection.animate()
                            .alpha(0f)
                            .setDuration(100L)
                            .setListener(object : AnimatorListenerAdapter(){
                                override fun onAnimationEnd(animation: Animator?) {
                                    llMultiselection.visibility = View.GONE
                                }
                            })
                    }
                    fabNewDoc.visibility = if (it) View.GONE else View.VISIBLE
                }

            }
        })
    }

    private fun insertNewDocTest() {
        CoroutineScope(Dispatchers.IO).launch{
            myDocumentsViewModel.insertPdf(Document(0,"test1",85))
            myDocumentsViewModel.insertPdf(Document(2,"test2",85))
            myDocumentsViewModel.insertPdf(Document(3,"test3",85))
            myDocumentsViewModel.insertPdf(Document(5,"test4",85))
            myDocumentsViewModel.insertPdf(Document(6,"test5",85))
            myDocumentsViewModel.insertPdf(Document(7,"test6",85))
            myDocumentsViewModel.insertPdf(Document(8,"test7",85))
            myDocumentsViewModel.insertPdf(Document(9,"test8",85))
            myDocumentsViewModel.insertPdf(Document(10,"test9",85))
            myDocumentsViewModel.insertPdf(Document(11,"test10",85))
            myDocumentsViewModel.insertPdf(Document(12,"test11",85))
            myDocumentsViewModel.insertPdf(Document(11,"test1",85))
            myDocumentsViewModel.insertPdf(Document(12,"test2",85))
            myDocumentsViewModel.insertPdf(Document(13,"test3",85))
            myDocumentsViewModel.insertPdf(Document(15,"test4",85))
            myDocumentsViewModel.insertPdf(Document(16,"test5",85))
            myDocumentsViewModel.insertPdf(Document(17,"test6",85))
            myDocumentsViewModel.insertPdf(Document(18,"test7",85))
            myDocumentsViewModel.insertPdf(Document(19,"test8",85))
            myDocumentsViewModel.insertPdf(Document(110,"test9",85))
            myDocumentsViewModel.insertPdf(Document(111,"test10",85))
            myDocumentsViewModel.insertPdf(Document(112,"test11",85))

        }
    }

    private fun documentClickListener(document: Document){
        if(myDocumentsViewModel.isMultiselection())
            myDocumentsViewModel.newElementDocumentQueue(document)
        else {
            bottomSheetSelectedDoc.setDocument(document)
            fragmentManager?.let { it1 ->
                bottomSheetSelectedDoc.show(
                    it1,
                    "BOTTOM_SHEET_DOCSELECTED"
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(/*requestCode == 1 && */resultCode == Activity.RESULT_OK){
//            data?.data?.also {
//                    it -> Dialogs.createSelectNameDoc(this@DocumentFrag,context,layoutInflater,it)
//            }

            when(requestCode){
                NEWDOC_DIS ->{
                    data?.data?.also {
                        it -> Dialogs.createSelectNameDoc(this@DocumentFrag,context,layoutInflater,it)
                    }
                }

                NEWDOC_GAL ->{
                    val bundle = Bundle()
                    bundle.putParcelable(IMAGES,data!!.clipData)
                    NavHostFragment.findNavController(this@DocumentFrag).navigate(R.id.action_documentFrag_to_creatorCamFrag,bundle)

                }
            }


        }//else{
        //}
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
    }

    override fun onNameDocSelected(name: String, uri: Uri?, size: Int) {
        if (uri != null) {
            Utilities.saveOnDisk(uri,name,App.storagePdf)
            RendererCoroutines.createFistPage(name)
        }
        val newDoc = Document(0,name,size)
        CoroutineScope(Dispatchers.IO).launch{
            myDocumentsViewModel.insertPdf(newDoc)
        }
        Dialogs.dissmis()
    }

    override fun onEditDoc(document: Document) {
        val bundle = Bundle()
        bundle.putString(Constants.DOCUMENT,document.name)
        NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorCamFrag,bundle)
    }

    override fun onShareDoc(document: Document) {

    }

    override fun onDeleteDoc(document: Document) {
        CoroutineScope(Dispatchers.IO).launch{
            myDocumentsViewModel.deletePdf(document.id)
        }
    }

    override fun onOpenDoc(document: Document) {
        val bundle = Bundle()
        bundle.putString(Constants.DOCUMENT,document.name)
        NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_viewerFrag,bundle)
    }

    private fun initBiometrics(){
        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.titleVerifyId))
            .setSubtitle(getString(R.string.txtVerifyId))
            .setNegativeButtonText("Use account password")
            .build()

        if(false)    //Todo: It's needed to check shp to check if there is a config
            biometricPrompt.authenticate(promptInfo)

    }
}