package com.example.pdfprime

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfprime.databinding.FragmentDocumentBinding
import kotlinx.android.synthetic.main.fragment_document.*

/**
 * This fragment is used to show a pdf summary in a list to edit them
 */
class DocumentFrag : Fragment() {
    private lateinit var binding : FragmentDocumentBinding
//    private lateinit var adapter : RecyclerViewAdapter

//    val fruitList = listOf<Fruit>(
//        Fruit("mango","mamá"),
//        Fruit("pera","papá"),
//        Fruit("guayaba","Iván"),
//        Fruit("platano","Ivonne"),
//        Fruit("fresa","Memin"),
//        Fruit("papaya","tío"),
//        Fruit("papa","Tía"),
//        Fruit("guanabaana","yo")
//    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_document,container,false)
        setListeners()
//        testOfCollapsingBar()
//        ViewCompat.setNestedScrollingEnabled(binding.rvDocuments,true)
        return binding.root
    }

//    private fun testOfCollapsingBar() {
//        adapter = RecyclerViewAdapter(fruitList,{selectedFruitItem:Fruit->listItemClicked(selectedFruitItem)})
//
//        binding.rvDocuments.setBackgroundColor(Color.YELLOW)
//        binding.rvDocuments.layoutManager = LinearLayoutManager(context)
//        binding.rvDocuments.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//        binding.rvDocuments.adapter = adapter
//        adapter.notifyDataSetChanged()
//    }

    //funcion que toma el seleccionado item de la lista como parametro y lo muestra como toast
//    private fun listItemClicked(fruit: Fruit){
//        Toast.makeText(context, "Supplier name is ${fruit.supplier}",Toast.LENGTH_LONG).show()
//    }

    private fun setListeners(){
//        binding.apply {
//            btnCreCam.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_creatorCamFrag) }
//            btnCreTxt.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_creatorTxtFrag) }
//            btnView.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_viewerFrag) }
//            dcMenu.setOnClickListener{ dcMenu.changeState()}
            /*Setting values to drawer layout*/
//            dcMenu.setDrawerLayout()
//        }
    }

    companion object {}
}