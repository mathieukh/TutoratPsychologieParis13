package com.mathieukh.tutopsychop13

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

    // Fonction qui permet de simplifier la transaction des fragments via le FragmentManager
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    // Fonction permettant d'ajouter un fragment donné sur un container à l'id donnée
    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    // Fonction permettant de remplacer un fragment donné sur un container à l'id donnée
    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }

    // Fonction permettant inflate via un ViewGroup directement
    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}