package com.example.pdfprime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.pdfprime.databinding.ActivityMainBinding
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetNewDoc
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*

/**
 * This class is used to hold current fragment
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var bottomSheetNewDoc : BottomSheetNewDoc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.btnAboutUs -> Toast.makeText(applicationContext,"btnAboutUs",Toast.LENGTH_LONG).show()
                R.id.btnRateUs -> Toast.makeText(applicationContext,"btnRateUs",Toast.LENGTH_LONG).show()
                R.id.btnReachUs -> Toast.makeText(applicationContext,"btnReachUs",Toast.LENGTH_LONG).show()
            }
            true
        }

        /*Settings for drawer layout*/
        dcMenu.setDrawerLayout(drawerLayout)
        dcMenu.getDrawerLayout().addDrawerListener(dcMenu)
        dcMenu.setOnClickListener{ dcMenu.changeState() }

        /*New document - floating action button*/
        binding.appBarMain.fabNewDoc.setOnClickListener{
            bottomSheetNewDoc = BottomSheetNewDoc()
            bottomSheetNewDoc.show(supportFragmentManager, "MY_BOTTOM_SHEET")
        }
    }
}