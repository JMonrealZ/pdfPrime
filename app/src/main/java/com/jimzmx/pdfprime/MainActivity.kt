package com.jimzmx.pdfprime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jimzmx.pdfprime.databinding.ActivityMainBinding
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
}