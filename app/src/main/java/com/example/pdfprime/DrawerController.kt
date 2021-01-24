package com.example.pdfprime

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.drawerlayout.widget.DrawerLayout

/***
 * This class is used to show/hide DrawerLayout(NavigationView) inside from activity_main.xml
 */
class DrawerController: AppCompatImageButton,DrawerLayout.DrawerListener{
    /*Overriding 3 constructors*/
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr : Int) : super(context,attributeSet,defStyleAttr)

    private lateinit var drawerLayout : DrawerLayout
    private val side = Gravity.LEFT

    fun changeState(){
        if(drawerLayout.isDrawerOpen(side))
            drawerLayout.closeDrawer(side)
        else
            drawerLayout.openDrawer(side)
    }

    fun setDrawerLayout(drawerLayout: DrawerLayout) : DrawerController{
        this.drawerLayout = drawerLayout
        return this
    }

    fun getDrawerLayout() : DrawerLayout{
        return this.drawerLayout
    }

    override fun onDrawerStateChanged(newState: Int) {
        Log.e("BUTTOM DRAWER: ", "onDrawerStateChanged")
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        Log.e("BUTTOM DRAWER: ", "onDrawerSlide")
    }

    override fun onDrawerClosed(drawerView: View) {
        Log.e("BUTTOM DRAWER: ", "onDrawerClosed")
    }

    override fun onDrawerOpened(drawerView: View) {
        Log.e("BUTTOM DRAWER: ", "onDrawerOpened")
    }
}