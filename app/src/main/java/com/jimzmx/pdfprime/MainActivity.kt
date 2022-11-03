package com.jimzmx.pdfprime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jimzmx.pdfprime.databinding.ActivityMainBinding
import com.jimzmx.pdfprime.presentation.fragments.DocumentFrag
import com.jimzmx.pdfprime.presentation.utils.currentNavigationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*

/**
 * This class is used to hold current fragment
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var pressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(binding.appBarMain.toolbar)
        setListeners()
        binding.navView.tvVersion.text = packageManager.getPackageInfo(packageName,0).versionName
    }
    private fun setListeners(){
        /*Settings for controller of drawer layout*/
        dcMenu.setDrawerLayout(drawerLayout)
        dcMenu.getDrawerLayout().addDrawerListener(dcMenu)
        dcMenu.setOnClickListener{ dcMenu.changeState() }

        val navController = findNavController(R.id.hostFragmentContainer)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.documentFrag),binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        //Press again to exit will only be available when you're in main fragment
        if(supportFragmentManager.currentNavigationFragment is DocumentFrag){
            if(pressedTime + 2000 > System.currentTimeMillis())
                finish()
            else {
                pressedTime = System.currentTimeMillis()
                Toast.makeText(this, R.string.txtBackAgainToExit, Toast.LENGTH_SHORT).show()
            }
        }else
            super.onBackPressed()   //Any other fragment behaves normal
    }
}