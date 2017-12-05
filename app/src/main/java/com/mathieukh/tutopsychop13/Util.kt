package com.mathieukh.tutopsychop13

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Classe permettant de stocker des fonctions utilitaires qui pourraient être adaptés
 * dans plusieurs classes
 */
object Util {

    fun getStatusBarHeight(cxt: Context): Int {
        var result = 0
        val resourceId = cxt.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0)
            result = cxt.resources.getDimensionPixelSize(resourceId)
        return result
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}