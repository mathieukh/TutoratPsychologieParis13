package com.mathieukh.tutopsychop13.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RelativeLayout
import com.mathieukh.tutopsychop13.R
import com.mathieukh.tutopsychop13.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*
    * Fonction onCreate appelée lors de l'instanciation de l'activité
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // On instancie le layout de l'activité
        setContentView(R.layout.activity_main)
        // Paramétrage de l'activité
        setup()
    }

    /*
    * Fonction setup appelée uniquement lorsque l'on démarre l'application
    * Elle permet d'initialiser les paramétrages de l'activité
     */
    private fun setup() {
        // On définit la toolbar comme l'action bar
        setSupportActionBar(toolbar)
        // On demande à l'action bar de retirer son titre
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        // On réajuste le margin de la toolbar qui déborde pour l'instant (MAJ bottombar à surveiller)
        (toolbar.layoutParams as RelativeLayout.LayoutParams).setMargins(0, Util.getStatusBarHeight(this), 0, 0)
        // On instancie un listener sur la bottombar pour déclencher des actions
        bottomNavigationView.setOnTabSelectListener { itemId ->
            // Pour l'instant, on affiche simplement l'id de l'onglet dans la console
            Log.d("itemId", itemId.toString())
        }
    }
}
