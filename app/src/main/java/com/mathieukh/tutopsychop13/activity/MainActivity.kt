package com.mathieukh.tutopsychop13.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mathieukh.tutopsychop13.R
import com.mathieukh.tutopsychop13.Util.replaceFragment
import com.mathieukh.tutopsychop13.activity.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

/*
* Activité principale de l'application
 */
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
        // On instancie un listener sur la bottombar pour déclencher des actions
        bottomNavigationView.setOnTabSelectListener { itemId ->
            // Pour l'instant, on affiche seulement le fragment NewsFragment
            when (itemId) {
                R.id.news_tab -> replaceFragment(fragment = NewsFragment.newInstance(), frameId = R.id.contentFrame)
            }
        }
    }
}
